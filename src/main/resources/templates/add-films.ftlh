<#import "/spring.ftl" as spring/>
<#import "macros/macro-elements.ftlh" as e>
<#import "macros/macro-admin.ftlh" as a>
<!doctype html>
<html lang="en">
<head>
    <title>New Film</title>
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

    <form name="films" method="post" action="/admin/panel/films" enctype="multipart/form-data">
        <input type="hidden" name="${_csrf.parameterName}", value="${_csrf.token}"/>
        <h1>CREATE FILM</h1>
        <@spring.formInput "film.title" "autocomplete=\"off\" placeholder=\"Title\"" "text"/>
        <@spring.formInput "film.year" "min=\"1700\" max=\"3000\" autocomplete=\"off\" placeholder=\"Year\"" "number"/>
        <@spring.formInput "film.ageLimit" "min=\"0\" max=\"18\" autocomplete=\"off\" placeholder=\"Age limit\"" "number"/>
        <@spring.formTextarea "film.description" "placeholder=\"Description\""/>
        <div class="upload-btn">
            <span id="filename">Select movie poster</span>
            <label for="file-upload">Browse<input name="file" id="file-upload" type="file" /></label>
        </div>
        <button class="btn" type="submit">Create</button>
    </form>

    <script type="text/javascript" src="/js/upload.js"></script>

</div>

<script type="text/javascript" src="/js/navigator.js"></script>

</body>
</html>