<#import "macros/macro-elements.ftlh" as e>
<!doctype html>
<html lang="en">
<head>
    <title>Film Name</title>
    <meta charset="utf-8">
    <link rel="icon" href="https://img.icons8.com/external-icongeek26-linear-colour-icongeek26/344/external-movie-reel-cinema-icongeek26-linear-colour-icongeek26.png">
    <link rel="stylesheet" href="/css/session-view.css">
</head>
<body>

<@e.logout/>

<a class="back-btn" href="/sessions">

    <svg xmlns="http://www.w3.org/2000/svg" width="30" height="30" fill="currentColor" class="bi bi-chevron-left" viewBox="0 0 16 16">
        <path fill-rule="evenodd" d="M11.354 1.646a.5.5 0 0 1 0 .708L5.707 8l5.647 5.646a.5.5 0 0 1-.708.708l-6-6a.5.5 0 0 1 0-.708l6-6a.5.5 0 0 1 .708 0z"/>
    </svg>

    <p>Sessions</p>
</a>

    <div class="general">

        <div class="block0">
            <img src="/images/posters/${film.image}" alt="s" id="poster_img">
        </div>

        <div class="block1" id="avers">
            <div class="title">
                <h1>${film.title} (${film.year})</h1>
            </div>
            <div class="age-limit">
                <p>${film.ageLimit}+</p>
            </div>
            <div class="desc">
                <p>${film.description}</p>
            </div>
        </div>

    </div>

</body>
</html>