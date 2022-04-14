<#macro navigator n isAdmin s1 s2 s3 s4>
    <nav class="menu">
        <input class="menu-toggler" type="checkbox">
        <label for="menu-toggler"></label>
        <ul>
            <li class="menu-item">
                <a class ="fas fa-film" href="/films"></a>
                <p class="prompt">${s1}</p>
            </li>
            <li class="menu-item">
                <a class="fas fa-couch" href="/sessions"></a>
                <p class="prompt">${s2}</p>
            </li>
            <li class="menu-item">
                <a class="fas fa-user" href="/profile"></a>
                <p class="prompt">${s3}</p>
            </li>
            <li class="menu-item">
                <a class="fas fa-lock" href="/admin/panel"></a>
                <p class="prompt">${s4}</p>
            </li>
        </ul>
    </nav>
    <style>
        .menu-toggler:checked ~ ul li:nth-child(${n}) a {
            opacity: 0.5;
            pointer-events: none;
        }
    </style>
    <script>
        <#if isAdmin == false>
        $('.menu ul').children().eq(2).remove();
        $('.menu ul').children().eq(2).remove();
        </#if>
    </script>
</#macro>

<#macro imports title css1 css2="" css3="">
    <meta charset="utf-8">
    <title>${title}</title>
    <link rel="stylesheet" href=${css1}>
    <link rel="stylesheet" href=${css2}>
    <link rel="stylesheet" href=${css3}>
    <link rel="icon" href="https://img.icons8.com/external-icongeek26-linear-colour-icongeek26/344/external-movie-reel-cinema-icongeek26-linear-colour-icongeek26.png">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
</#macro>

<#macro logout>
    <style>
        .fa-sign-out-alt {
            position: fixed;
            font-size: 30px;
            line-height: 0.92 !important;
            top: 4%;
            right: 2%;
            cursor: pointer;
            color: rgba(255, 255, 255, 0.7);
        }
    </style>
    <form action="/logout" method="post" class="hide-submit">
        <input type="hidden" name="${_csrf.parameterName}", value="${_csrf.token}"/>
        <label>
            <input type="submit"/>
            <svg xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="white" class="bi bi-box-arrow-left" viewBox="0 0 16 16">
                <path fill-rule="evenodd" d="M6 12.5a.5.5 0 0 0 .5.5h8a.5.5 0 0 0 .5-.5v-9a.5.5 0 0 0-.5-.5h-8a.5.5 0 0 0-.5.5v2a.5.5 0 0 1-1 0v-2A1.5 1.5 0 0 1 6.5 2h8A1.5 1.5 0 0 1 16 3.5v9a1.5 1.5 0 0 1-1.5 1.5h-8A1.5 1.5 0 0 1 5 12.5v-2a.5.5 0 0 1 1 0v2z"></path>
                <path fill-rule="evenodd" d="M.146 8.354a.5.5 0 0 1 0-.708l3-3a.5.5 0 1 1 .708.708L1.707 7.5H10.5a.5.5 0 0 1 0 1H1.707l2.147 2.146a.5.5 0 0 1-.708.708l-3-3z"></path>
            </svg>
        </label>
    </form>
</#macro>

<#macro languages>
    <style>
        .choose-language {
            display: flex;
            gap: 6px;
            justify-content: center;
        }

        .choose-language img {
            min-width: 20px;
            min-height: 20px;
            width: 20px !important;
            height: 20px !important;
        }
    </style>
    <div class="choose-language">
        <a href="?lang=en"><img src="/img/united-states.png" alt=""/></a>
        <a href="?lang=ru"><img src="/img/russia.png" alt=""/></a>
    </div>
</#macro>

<#macro search>
    <header class="header">
        <form class="search-form">
            <input type="search" value="" placeholder="Search" class="search-input" id="txt-search">
        </form>
    </header>
</#macro>
