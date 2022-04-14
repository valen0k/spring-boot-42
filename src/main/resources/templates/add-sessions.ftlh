<#import "/spring.ftl" as spring/>
<#import "macros/macro-elements.ftlh" as e>
<#import "macros/macro-admin.ftlh" as a>
<!doctype html>
<html lang="en">
<head>
    <title>New Session</title>
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
    <form name="halls" action="/admin/panel/sessions" method="POST">
    <input type="hidden" name="${_csrf.parameterName}", value="${_csrf.token}"/>
        <h1>CREATE SESSION</h1>

    <select name="selectedHall" class="select-css" id="select1">
        <option value="" disabled selected>Choose hall number</option>
        <#list halls as hall>
            <option>${hall.id} (${hall.seats} seats)</option>
        </#list>
    </select>

    <select name="selectedFilm" class="select-css" id="select2">
        <option value="" disabled selected>Choose a film</option>
        <#list films as film>-->
            <option>${film.title}</option>
        </#list>
    </select>

    <input id="date" name="selectedDate" type="datetime-local"/>
    <input id="price" name="selectedPrice" type="number" placeholder="Price"/>
    <button class="btn" type="submit">Create</button>
    </form>
</div>

<script type="text/javascript" src="/js/navigator.js"></script>

</body>
</html>