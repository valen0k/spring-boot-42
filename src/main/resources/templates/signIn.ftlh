<#import "/spring.ftl" as spring/>
<#import "macros/macro-elements.ftlh" as e>
<html lang="en">
<head>
    <title><@spring.message "sign-in.title"/></title>
    <link rel="stylesheet" type="text/css" href="css/sign.css">
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.5.0/css/all.css" integrity="sha384-B4dIYHKNBt8Bc12p+WXckhzcICo0wtJAoU8YZTY5qE0Id1GSseTk6S+L3BlXeVIU" crossorigin="anonymous">
    <script src="http://code.jquery.com/jquery-2.2.4.js" type="text/javascript"></script>
</head>
<body>

<style>
    .failure:before { Content: "<@spring.message "notify.wrong"/>"; }
    .logout:before { Content: "<@spring.message "notify.logout"/>"; }
    .confirm:before { Content: "<@spring.message "notify.verify"/>"; }
</style>

<#if error?? && error == true>
    <div class="notifyF"><span id="notifyTypeF" class=""></span></div>
    <#assign auth = false>

<#elseif logout?? && logout == true>
    <div class="notifyL"><span id="notifyTypeL" class=""></span></div>
    <#assign auth = false>

<#elseif (not_confirmed?? && not_confirmed == true) || (registered?? && registered == true)>
    <div class="notifyX"><span id="notifyTypeX" class=""></span></div>
    <#assign auth = false>
</#if>

<script type="text/javascript" src="/js/sign-in.js"></script>

<form action="signIn" method="post">
    <input type="hidden" name="${_csrf.parameterName}", value="${_csrf.token}"/>
    <input type="text" id="email" name="email" placeholder="<@spring.message "sign-in.email-placeholder"/>" autocomplete="off" required/>
    <input type="password" id="password" name="password" placeholder="<@spring.message "sign-in.password-placeholder"/>" autocomplete="off" required/>
    <div class="remember-me">
        <label class="label-container"><@spring.message "sign-in.remember-me"/>
            <input type="checkbox" name="remember-me">
            <span class="checkmark"></span>
        </label>
    </div>
    <button class="btn"><@spring.message "sign-in.login"/></button>
    <a href="/signUp"><h6><@spring.message "sign-in.create"/></h6></a>
</form>

<footer>
    <@e.languages/>
    <h5>By elaronda & heusebio</h5>
</footer>



</body>
</html>