<#import "/spring.ftl" as spring/>
<#import "macros/macro-elements.ftlh" as e>
<!doctype html>
<html lang="en">
<head>
    <@e.imports "Sessions" "/css/main.css" "/css/sessions.css" "/css/navigator-dark.css"/>
</head>
<body>

<#assign nav_films><@spring.message "navigator.films"/></#assign>
<#assign nav_sessions><@spring.message "navigator.sessions"/></#assign>
<#assign nav_profile><@spring.message "navigator.profile"/></#assign>
<#assign nav_admin><@spring.message "navigator.admin"/></#assign>

<@e.navigator 2 isAdmin nav_films nav_sessions nav_profile nav_admin/>

<@e.logout/>

    <@e.search/>

    <div class="wrapper" id="wrapper">

    <#list sessions as session>

        <div class="main_card">
            <div class="card_left">
                <div class="card_datails">
                    <div class="card_title">
                        <h1>${session.film.title}</h1>
                        <h4 class="year">${session.film.year}</h4>
                    </div>
                    <div class="card_cat">
                        <p class="price">$${session.price}</p>
                        <p class="date">${session.date}</p>
                        <p class="time">${session.time}</p>
                        <p class="limit">${session.film.ageLimit}+</p>
                    </div>
                    <div class="disc-block">
                        <p class="disc">${session.film.description}</p>
                    </div>
                    <div class="social-btn">
                        <button>Buy</button>
                        <a href="sessions/${session.film.title?lower_case}"><button class="more-btn">More</button></a>
                    </div>
                </div>
            </div>
            <div class="card_right">
                <div class="img_container">
                    <img src="/images/posters/${session.film.image}" alt="">
                </div>
            </div>
        </div>
    </#list>

    </div>

<script type="text/javascript" src="/js/sessions-search.js"></script>
<script type="text/javascript" src="/js/navigator.js"></script>

</body>
</html>