<#import "/spring.ftl" as spring>
<#import "macros/macro-elements.ftlh" as e>
<#import "macros/macro-admin.ftlh" as a>
<!doctype html>
<html lang="en">
<head>
    <title>New Hall</title>
    <@a.imports />
</head>
<body>
<script type="text/javascript" src="/js/admin-panel.js"></script>

<#assign nav_films><@spring.message "navigator.films"/></#assign>
<#assign nav_sessions><@spring.message "navigator.sessions"/></#assign>
<#assign nav_profile><@spring.message "navigator.profile"/></#assign>
<#assign nav_admin><@spring.message "navigator.admin"/></#assign>

<@e.navigator 4 true nav_films nav_sessions nav_profile nav_admin/>

<@a.header />

<@e.logout/>

<div class="panel-menu">
    <form name="halls" action="" method="POST">
        <input type="hidden" name="${_csrf.parameterName}", value="${_csrf.token}"/>
        <h1>CREATE HALL</h1>
        <@spring.formInput 'hall.seats' "placeholder=\"Number of seats\" min=\"0\" max=\"1000\" autocomplete=\"off\""  'number'/>
        <button class="btn" type="submit">Create</button>
    </form>
</div>

<script type="text/javascript" src="/js/navigator.js"></script>

</body>
</html>