$( "#phoneNumber" ).keyup(function() {
    var cleaned = ('' + $(this).val()).replace(/\D/g, '');
    if (cleaned.length === 1) {
        if ($(this).val().includes('+7')) {
            $(this).val('+7 ()');
            $(this)[0].setSelectionRange(cleaned.length + 3, cleaned.length + 3);
        }
        else {
            $(this).val('+7 (' + cleaned + ') ');
            $(this)[0].setSelectionRange(cleaned.length + 4, cleaned.length + 4);
        }
    }
    else if (cleaned.length < 4) {
        console.log('less than 4, ' + cleaned)
        $(this).val('+7 (' + cleaned.substring(1) + ') ');
        $(this)[0].setSelectionRange(cleaned.length + 3, cleaned.length + 3);
    }
    else if (cleaned.length === 4) {
        console.log('equal 4')
        $(this)[0].setSelectionRange(cleaned.length + 5, cleaned.length + 5);
    } else {
        console.log('more than 4')
        let match = cleaned.match(/^(\d)(\d{3})(\d{0,7})$/);
        if (match) {
            console.log('matching');
            $(this).val('+' + match[1] + ' (' + match[2] + ') ' + match[3]);

        }
    }

});