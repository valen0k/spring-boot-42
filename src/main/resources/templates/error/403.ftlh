<html>

<head>
    <title>403 Forbidden</title>
    <link rel="stylesheet" href="/css/403.css">
</head>

<body>

<div class="Content">
    <svg height="80" width="200" class="Content-item">
        <defs>
            <clipPath id="eye-iris-clip">
                <path d="M 25 40 q 75 -60 150 0 M 25 40 q 75 60 150 0" />
            </clipPath>
        </defs>
        <path id="eye-back" d="M 25 40 q 75 -60 150 0 M 25 40 q 75 60 150 0" />
        <circle cx="100" cy="40" r="23" id="eye-iris" clip-path="url(#eye-iris-clip)"/>

        <circle cx="100" cy="40" r="5" id="eye-pupil" clip-path="url(#eye-iris-clip)"/>
    </svg>

    <div class="Content-item">
        <span class="title">403</span>
        <br>
        <span class="desc">It looks like the resource you are trying to access is forbidden.</span>
        <a href="/"><button class="btn">Home</button></a>
    </div>
</div>

</body>

</html>