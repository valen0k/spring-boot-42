<#import "/spring.ftl" as spring/>
<#import "macros/macro-elements.ftlh" as e>
<html lang="en">
<head>
    <@e.imports "Profile" "/css/profile.css" "/css/navigator.css"/>
</head>
<body>

<#assign nav_films><@spring.message "navigator.films"/></#assign>
<#assign nav_sessions><@spring.message "navigator.sessions"/></#assign>
<#assign nav_profile><@spring.message "navigator.profile"/></#assign>
<#assign nav_admin><@spring.message "navigator.admin"/></#assign>


<div class="profile">
    <header class="header">
        <@e.navigator 3 true nav_films nav_sessions nav_profile nav_admin />
        <@e.logout/>
        <div class="details">
            <form action="upload-avatar" method="post" class="avatar" id="avatar" enctype="multipart/form-data">
                <input type="hidden" name="${_csrf.parameterName}", value="${_csrf.token}"/>
                <div id="preview">
                    <img src="/images/avatars/${avatar}" id="avatar-image" class="avatar_img" alt="">
                </div>
                <div class="avatar_upload" >
                    <label class="upload_label">
                        <svg class="camera-icon" xmlns="http://www.w3.org/2000/svg" width="32" height="32" fill="currentColor" viewBox="0 0 16 16">
                            <path d="M10.5 8.5a2.5 2.5 0 1 1-5 0 2.5 2.5 0 0 1 5 0z"></path>
                            <path d="M2 4a2 2 0 0 0-2 2v6a2 2 0 0 0 2 2h12a2 2 0 0 0 2-2V6a2 2 0 0 0-2-2h-1.172a2 2 0 0 1-1.414-.586l-.828-.828A2 2 0 0 0 9.172 2H6.828a2 2 0 0 0-1.414.586l-.828.828A2 2 0 0 1 3.172 4H2zm.5 2a.5.5 0 1 1 0-1 .5.5 0 0 1 0 1zm9 2.5a3.5 3.5 0 1 1-7 0 3.5 3.5 0 0 1 7 0z"></path>
                        </svg>
                        <input type="file" name="file" size="100" id="upload">
                        <input type="submit" value="Upload File" id="formsubmit"/>
                    </label>
                </div>
            </form>
            <script type="text/javascript">
                let file= document.getElementById('upload');
                let submit= document.getElementById('formsubmit');
                submit.parentNode.removeChild(submit);
                file.onchange= function() {
                    if (this.value!=='')
                        this.form.submit();
                };
            </script>
            <h1 class="heading">${user.firstName} ${user.lastName}</h1>
            <div class="location">
                <svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-envelope" viewBox="0 0 20 20">
                    <path d="M0 4a2 2 0 0 1 2-2h12a2 2 0 0 1 2 2v8a2 2 0 0 1-2 2H2a2 2 0 0 1-2-2V4Zm2-1a1 1 0 0 0-1 1v.217l7 4.2 7-4.2V4a1 1 0 0 0-1-1H2Zm13 2.383-4.708 2.825L15 11.105V5.383Zm-.034 6.876-5.64-3.471L8 9.583l-1.326-.795-5.64 3.47A1 1 0 0 0 2 13h12a1 1 0 0 0 .966-.741ZM1 11.105l4.708-2.897L1 5.383v5.722Z"></path>
                </svg>
                <p>${user.email}</p>
            </div>
        </div>
    </header>

    <div class="generalTable">

        <div>
            <h1 align="center"><@spring.message "profile.uploads"/></h1>
            <div class="tbl-header" style="float: left;margin-right:10px">
                <table>
                    <thead>
                    <tr>
                        <th><@spring.message "profile.filename"/></th>
                        <th><@spring.message "profile.size"/></th>
                        <th><@spring.message "profile.mime"/></th>
                    </tr>
                    </thead>
                </table>
            </div>

            <div class="tbl-content" style="float: left;margin-right:10px">
                <table>
                    <tbody>
                    <#list fileInfo as file>
                        <tr>
                            <td>
                                <a target="_blank" href="/images/avatars/${(file[0])!}">${(file[0])!}</a>
                            </td>
                            <td>${(file[1])!}</td>
                            <td>${(file[2])!}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>

        <div>
            <h1 align="center"><@spring.message "profile.logtime"/></h1>
            <div class="tbl-header" style="float: right;">
                <table style="float: left">
                    <thead>
                    <tr>
                        <th><@spring.message "profile.date"/></th>
                        <th><@spring.message "profile.time"/></th>
                        <th><@spring.message "profile.ip"/></th>
                    </tr>
                    </thead>
                </table>
            </div>

            <div class="tbl-content" style="float: right;">
                <table>
                    <tbody>
                    <#list logInfo as log>
                        <tr>
                            <td>${(log[0])!}</td>
                            <td>${(log[1])!}</td>
                            <td>${(log[2])!}</td>
                        </tr>
                    </#list>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

<@e.languages/>

<script type="text/javascript" src="/js/navigator.js"></script>
</body>
</html>