<#import "/spring.ftl" as spring/>
<#import "macros/macro-elements.ftlh" as e>
<!doctype html>
<html lang="en">
<head>
    <@e.imports "Films" "/css/main.css" "/css/films.css" "/css/navigator-dark.css"/>
</head>
<body>

<#assign nav_films><@spring.message "navigator.films"/></#assign>
<#assign nav_sessions><@spring.message "navigator.sessions"/></#assign>
<#assign nav_profile><@spring.message "navigator.profile"/></#assign>
<#assign nav_admin><@spring.message "navigator.admin"/></#assign>

<@e.navigator 1 isAdmin nav_films nav_sessions nav_profile nav_admin/>

<@e.search/>

<@e.logout/>

<div class="container" id="container">

    <#list films as film>

        <div class="movie-card">
            <a href="films/${film.title?lower_case}/chat">
                <div class="movie-header">
                    <img rel="preload" class="poster" src="/images/posters/${film.image}"  alt=""/>
                </div>
            </a>
            <div class="movie-content">
                <div class="movie-content-header">
                        <h3 id="movie-title" class="movie-title">${film.title}</h3>
                    <div class="movie-about">
                        <p class="year-info">${film.year}</p>
                    </div>
                </div>
            </div>
        </div>

    </#list>
</div>

<script type="text/javascript" src="/js/films-search.js"></script>
<script type="text/javascript" src="/js/navigator.js"></script>

</body>
</html>