<#import "/spring.ftl" as spring/>
<#import "macros/macro-elements.ftlh" as e>
<#import "macros/macro-admin.ftlh" as a>
<!doctype html>
<html lang="en">
<head>
    <title>Admin Panel</title>
    <@a.imports />
</head>
<body>

<#assign nav_films><@spring.message "navigator.films"/></#assign>
<#assign nav_sessions><@spring.message "navigator.sessions"/></#assign>
<#assign nav_profile><@spring.message "navigator.profile"/></#assign>
<#assign nav_admin><@spring.message "navigator.admin"/></#assign>

<@e.navigator 4 true nav_films nav_sessions nav_profile nav_admin/>

<@a.header />

<@e.logout/>

<div class="panel-menu">
    <a href="/admin/panel/halls"><button class="btn"><span>Create hall</span></button></a>
    <a href="/admin/panel/films"><button class="btn">Create film</button></a>
    <a href="/admin/panel/sessions"><button class="btn">Create session</button></a>
</div>

<script type="text/javascript" src="/js/navigator.js"></script>

</body>
</html>