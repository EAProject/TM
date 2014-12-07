function reply_click(clicked_id)
{
    alert(clicked_id);
    authorizeNotification() ;
}

function authorizeNotification() {
    Notification.requestPermission(function(perm) {
        alert(perm);
    });
}

function showNotification() {
    var notification = new Notification("This is a title", {
        dir: "auto",
        lang: "",
        body: "This is a notification body",
        tag: "sometag",
    });

}

document.querySelector("#authorize").onclick = authorizeNotification;
document.querySelector("#show").onclick = showNotification;
