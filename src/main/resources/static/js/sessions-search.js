$(document).ready(function() {
    $('#txt-search').keyup(function () {
        let output = '';
        let searchField = $(this).val();

        $.getJSON('sessions/search', {"filmName": searchField}, function (data) {
            $.each(data, function (key, val) {
                output += '<div class="main_card">';
                output += '<div class="card_left">';
                output += '<div class="card_datails">';
                output += '<div>';
                output += '<h1>' + val.film.title + '</h1>';
                output += '<h4 class="year">' + val.film.year + '</h4>';
                output += '</div>';
                output += '<div class="card_cat">';
                output += '<p class="price">$' + val.price + '</p>';
                output += '<p class="date">' + val.date + '</p>';
                output += '<p class="time">' + val.time + '</p>';
                output += '<p class="limit">' + val.film.ageLimit + '+</p>';
                output += '</div>';
                output += '<div class="disc-block">';
                output += '<p class="disc">' + val.film.description + '</p>';
                output += '</div>';
                output += '<div class="social-btn">';
                output += '<button>Buy</button>';
                output += '<a href="sessions/' + val.film.title.toLowerCase() + '"><button class="more-btn">More</button></a>';
                output += '</div></div></div>';
                output += '<div class="card_right">';
                output += '<div class="img_container">';
                output += '<img src="/images/posters/' + val.film.posterUrl + '" alt="">';
                output += '</div></div></div>';
            });
            $('#wrapper').html(output);
        });
    });
});