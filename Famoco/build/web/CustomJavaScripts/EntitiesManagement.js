function saveEditedItem(btnClicked, containerId, containerParentId) {
    event.preventDefault();
    alert("seriazed: "+ $form.serialize());
var $form = $(btnClicked).parents('form');
alert($form.attr('action'));
$.ajax({
    type: "POST",
    url: $form.attr('action'),
    data: $form.serialize(),
    error: function (xhr, status, error) {

        if (xhr.status === 400) { //if this is an invalid modelstate error redisplay the edit form with with the model with validation errors
            lightcase.get('contentInner').children().html(xhr.responseText);
        }
        else {
            alert(error + ":" + status);
        }
    },
    success: function (response) {
        lightcase.get('contentInner').children().html(response);  
        lightcase.close();
    }
});

return false; // if it's a link to prevent post
}