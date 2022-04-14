<#import "macros/macro-elements.ftlh" as e>
<!doctype html>
<html lang="ru">
<head>
    <@e.imports "${film.title} - Chat" "/css/main.css" "/css/chat.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1/dist/sockjs.min.js"></script>
    <script src="/js/stomp.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/@joeattardi/emoji-button@3.0.3/dist/index.min.js"></script>
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/4.6.1/css/bootstrap.min.css" integrity="sha512-T584yQ/tdRR5QwOpfvDfVQUidzfgc2339Lc8uBDtcp/wYu80d7jwBgAxbyMh0a9YM9F8N3tdErpFI8iaGx6x5g==" crossorigin="anonymous" referrerpolicy="no-referrer">

</head>

<body onload="connect()">

<@e.logout/>

<div id="body">
    <a class="back-btn" href="/films">

        <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
            <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
        </svg>

        <p>Movies</p>
    </a>

    <div class="chat-box">
        <div class="chat-box-header">${film.title}</div>
        <div class="chat-box-body">
            <div class="chat-box-overlay"></div>
            <div class="chat-area-main" id="chat-area-main">

                <#assign is_last_owner = false>
                <#assign last_owner_message_id = -1>
                <#assign last_user_id = -1>
                <#assign last_message_id = -1>

                <#list messages as message>

                <script type="text/javascript">
                    <#if is_last_owner == true && message.from.id == user.id>
                        $("#chat-id-" + ${last_owner_message_id}).find('.chat-msg-content').append('<div class="chat-msg-text">${message.text}</div>');
                        </script>
                        <#continue>
                    </#if>
                </script>

                <script type="text/javascript">
                    <#if last_user_id == message.from.id>
                        $("#chat-id-" + ${last_message_id}).find('.chat-msg-content').append('<div class="chat-msg-text">${message.text}</div>');
                </script>
                <#continue>
                    </#if>

                </script>



                    <#if message.from.id == user.id>
                        <div id="chat-id-${message.id}" class="chat-msg owner">
                        <#assign is_last_owner = true>
                        <#assign last_owner_message_id = message.id>
                    <#else>
                        <div id="chat-id-${message.id}" class="chat-msg">
                        <#assign is_last_owner = false>
                    </#if>
                    <#assign last_user_id = message.from.id>
                    <#assign last_message_id = message.id>

                        <div class="chat-msg-profile">
                            <img class="chat-msg-img" src="/images/avatars/${message.from.picName}" alt="" />
                            <div class="chat-msg-date">${message.from.firstName} #${message.from.id}</div>
                        </div>
                        <div class="chat-msg-content">
                            <div class="chat-msg-text">${message.text}</div>
                        </div>
                    </div>

                </#list>

            </div>
        </div>
        <div class="chat-input">
            <form>
                <input type="hidden" name="${_csrf.parameterName}", value="${_csrf.token}"/>


                <input type="text" id="chat-input" placeholder="Send a message..." tabindex="-1" autocomplete="off"/>

                <button type="button" id="emoji-button">
                    <svg xmlns="http://www.w3.org/2000/svg" width="26" height="26" fill="grey" class="bi bi-emoji-smile" viewBox="0 0 16 16">
                        <path d="M8 15A7 7 0 1 1 8 1a7 7 0 0 1 0 14zm0 1A8 8 0 1 0 8 0a8 8 0 0 0 0 16z"/>
                        <path d="M4.285 9.567a.5.5 0 0 1 .683.183A3.498 3.498 0 0 0 8 11.5a3.498 3.498 0 0 0 3.032-1.75.5.5 0 1 1 .866.5A4.498 4.498 0 0 1 8 12.5a4.498 4.498 0 0 1-3.898-2.25.5.5 0 0 1 .183-.683zM7 6.5C7 7.328 6.552 8 6 8s-1-.672-1-1.5S5.448 5 6 5s1 .672 1 1.5zm4 0c0 .828-.448 1.5-1 1.5s-1-.672-1-1.5S9.448 5 10 5s1 .672 1 1.5z"/>
                    </svg>
                </button>

                <button type="submit" class="chat-submit" id="chat-submit">
                    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-arrow-right-circle-fill" viewBox="0 0 16 16">
                        <path d="M8 0a8 8 0 1 1 0 16A8 8 0 0 1 8 0zM4.5 7.5a.5.5 0 0 0 0 1h5.793l-2.147 2.146a.5.5 0 0 0 .708.708l3-3a.5.5 0 0 0 0-.708l-3-3a.5.5 0 1 0-.708.708L10.293 7.5H4.5z"/>
                    </svg>
                </button>

            </form>

            <script type="text/javascript">
                const button = document.querySelector('#emoji-button');

                const picker = new EmojiButton();

                button.addEventListener('click', () => {
                    picker.togglePicker(button);

                });

                picker.on('emoji', emoji => {
                    let text = $('#chat-input');
                    text.val(text.val() + emoji);
                });
            </script>
        </div>
    </div>



    </div>
</div>

<script type="text/javascript">
    let stompClient = null;
    let INDEX = 0;
    let isLastOwner = false;
    let lastMessageId = '';
    let lastUserId = '';

    $(function() {

        $("#chat-submit").click(function(e) {
            e.preventDefault();
            let msg = $("#chat-input").val();
            if (msg.trim() === '') {
                return false;
            }
            sendMessage();
        })
    })

    function connect() {
        let socket = new SockJS('/chat');
        stompClient = Stomp.over(socket);
        stompClient.connect({}, function(frame) {
            console.log('Connected: ' + frame);
            stompClient.subscribe('/film/${film.title?lower_case}/chat/messages', function(messageOutput) {
                showMessageOutput(JSON.parse(messageOutput.body));
            });
        });
        let element = $(".chat-area-main");
        element.stop().animate({ scrollTop: element[0].scrollHeight}, 1300);
    }

    function sendMessage() {
        let text = document.getElementById('chat-input').value;
        stompClient.send("/send/${film.title?lower_case}/chat", {},
            JSON.stringify({
                'from':
                    {
                        'id': ${user.id},
                        'email': "${user.email}",
                        'firstName': "${user.firstName}",
                        'lastName': "${user.lastName}",
                        'phoneNumber': "${user.phoneNumber}",
                        'password': "${user.password}",
                        'picName': "${user.picName}"
                    },
                'text':text,
                'film':
                    {
                        'id': ${film.id},
                        'title': "${film.title}",
                        'year': "${film.year}",
                        'ageLimit': "${film.ageLimit}",
                        'description': "${film.description}",
                        'image': "${film.image}"
                    }
            }));
    }

    function showMessageOutput(messageOutput) {

        let block = $(".chat-area-main");
        if (isLastOwner) {
            if (${user.id} === messageOutput.from.id) {
                $("#chat-input").val('');
                $("#chat-id-" + lastMessageId).find('.chat-msg-content').append('<div class="chat-msg-text">' + messageOutput.text + '</div>');
                block.stop().animate({ scrollTop: block[0].scrollHeight}, 1000);
                return;
            } else {
                isLastOwner = false;
            }
        } else {
            if (lastUserId == messageOutput.from.id) {
                $("#chat-id-" + INDEX).find('.chat-msg-content').append('<div class="chat-msg-text">' + messageOutput.text + '</div>');
                block.stop().animate({ scrollTop: block[0].scrollHeight}, 1000);
                return;
            }
        }
        lastUserId = messageOutput.from.id;
        INDEX++;
        let str = "";
        str += '<div id="chat-id-' + INDEX + '" class="chat-msg ';
        if (${user.id} === messageOutput.from.id) {
            str += 'owner';
            $("#chat-input").val('');
            isLastOwner = true;
            lastMessageId = INDEX;
        }
        str += '">';
        str += '<div class="chat-msg-profile">';
        console.log('image name: ' + messageOutput.from.picName);
        str += '<img class="chat-msg-img" src="/images/avatars/' + messageOutput.from.picName + '" alt="" />';
        str += '<div class="chat-msg-date">' + messageOutput.from.firstName + ' #' + messageOutput.from.id + '</div>';
        str += '</div>';
        str += '<div class="chat-msg-content">';
        str += '<div class="chat-msg-text">' + messageOutput.text + '</div>';
        str += '</div></div>';

        block.append(str);
        $("#chat-id-" + INDEX).hide().fadeIn(300);
        block.stop().animate({ scrollTop: block[0].scrollHeight}, 1000);
    }

</script>

</body>
</html>