<#import "/spring.ftl" as spring/>
<#import "macros/macro-elements.ftlh" as e>
<html lang="en">
<head>
    <title><@spring.message "sign-up.title"/></title>
    <link rel="stylesheet" href="css/sign.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
</head>


<body>
<#assign email><@spring.message "sign-in.email-placeholder"/></#assign>
<#assign firstName><@spring.message "sign-up.firstname-placeholder"/></#assign>
<#assign lastName><@spring.message "sign-up.lastname-placeholder"/></#assign>
<#assign phone><@spring.message "sign-up.phone-placeholder"/></#assign>
<#assign password><@spring.message "sign-in.password-placeholder"/></#assign>

<form action="signUp" method="post" class="form">
    <input type="hidden" name="${_csrf.parameterName}", value="${_csrf.token}"/>
    <@spring.formInput 'user.email' "placeholder=${email} autocomplete=off"  'email'/>
    <@spring.showErrors "" "fail-prompt" />
    <@spring.formInput 'user.firstName' "placeholder=${firstName} autocomplete=off" 'text'/>
    <@spring.showErrors "" "fail-prompt" />
    <@spring.formInput 'user.lastName' "placeholder=${lastName} autocomplete=off" 'text'/>
    <@spring.showErrors "" "fail-prompt" />
    <@spring.formInput 'user.phoneNumber' "placeholder=${phone} maxlength=16 autocomplete=off" 'tel'/>
    <@spring.showErrors "" "fail-prompt" />
    <@spring.formInput 'user.password' "placeholder=${password} autocomplete=off" 'password'/>
    <@spring.showErrors "" "fail-prompt" />
    <button id="regBtn" class="btn"><@spring.message "sign-up.register"/></button>
    <a href="/signIn"><h6><@spring.message "sign-up.exist"/></h6></a>
</form>

<footer>
    <@e.languages/>
    <h5>By elaronda & heusebio</h5>
</footer>

<script type="text/javascript" src="js/sign-up.js"></script>
</body>
</html>