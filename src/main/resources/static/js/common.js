function errorInform(errorMessage) {
    $("#errorModal .modal-body").text(errorMessage);
    $('#errorModal').modal('show');
}

function removeRow(oButton) {
    $(oButton).closest('tr').remove();
}