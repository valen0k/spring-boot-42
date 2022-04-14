$(document).ready(function() {
    $('#txt-search').keyup(function () {
        let output = '';
        let searchField = $(this).val();

        $.getJSON('films/search', {"filmName": searchField}, function (data) {
            $.each(data, function (key, val) {
                output += '<div class="movie-card">';
                output += '<a href="films/' + val.title.toLowerCase() + '/chat">';
                output += '<div class="movie-header">';
                output += '<img class="poster" src="/images/posters/' + val.image + '"  alt=""/>';
                output += '</div>';
                output += '</a>';
                output += '<div class="movie-content">';
                output += '<div class="movie-content-header">';
                output += '<h3 class="movie-title">' + val.title + '</h3>';
                output += '<div class="movie-about">';
                output += '<p class="year-info">' + val.year + '</p>';
                output += '</div>';
                output += '</div>';
                output += '</div>';
                output += '</div>';
            });
            $('#container').html(output);
        });
    });
});