function addEvent(element, event, callback) {
    let previousEventCallBack = element["on"+event];
    element["on"+event] = function (e) {
        let output = callback(e);

        if (output === false) return false;

        if (typeof previousEventCallBack === 'function') {
            output = previousEventCallBack(e);
            if (output === false) return false;
        }
    }
}

addEvent(window, "load", function () {
    document.querySelector('button').disabled = true;
});

addEventListener("input", function() {
    disableButton(document.getElementById("seats").value);
});

addEventListener("input", function() {
    disableButtons(
        document.getElementById("title").value,
        document.getElementById("year").value,
        document.getElementById("ageLimit").value,
        document.getElementById("description").value,
        document.getElementById("file-upload").value,
    );
});

addEventListener("input", function() {
    disableButtonsv2(
        document.getElementById("select1").value,
        document.getElementById("select2").value,
        document.getElementById("date").value,
        document.getElementById("price").value
    );
});

const isEmpty = str => !str.trim().length;

function disableButton(value) {
    document.querySelector('button').disabled = !!isEmpty(value);
}

function disableButtons(v1, v2, v3, v4, v5) {
    document.querySelector('button').disabled = !!(isEmpty(v1) || isEmpty(v2) || isEmpty(v3) || isEmpty(v4) || isEmpty(v5));
}

function disableButtonsv2(v1, v2, v3, v4) {
    document.querySelector('button').disabled = !!(isEmpty(v1) || isEmpty(v2) || isEmpty(v3) || isEmpty(v4));
}