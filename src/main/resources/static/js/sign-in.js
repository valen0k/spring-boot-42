(function () {
    $(".notifyF").toggleClass("active");
    $("#notifyTypeF").toggleClass("failure");
    setTimeout(function(){
        $(".notifyF").removeClass("active");
        $("#notifyTypeF").removeClass("failure");
    },2000);
}());
(function () {
    $(".notifyL").toggleClass("active");
    $("#notifyTypeL").toggleClass("logout");
    setTimeout(function(){
        $(".notifyL").removeClass("active");
        $("#notifyTypeL").removeClass("logout");
    },2000);
}());
(function () {
    $(".notifyX").toggleClass("active");
    $("#notifyTypeX").toggleClass("confirm");
}());