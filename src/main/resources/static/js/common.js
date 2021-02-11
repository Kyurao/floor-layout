function errorInform(errorMessage) {
    $("#errorModal .modal-body").text(errorMessage);
    $('#errorModal').modal('show');
}