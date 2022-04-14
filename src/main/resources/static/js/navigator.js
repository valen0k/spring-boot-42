const $menu = $('.menu-toggler');

$(document).mouseup(e => {
    if (!$menu.is(e.target) && $menu.has(e.target).length === 0 && $menu.is(":checked")) {
        $menu.prop("checked", !$menu.prop("checked"));
    }
});