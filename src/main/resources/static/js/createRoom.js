function addCorner() {

    let $x = $('#coordinateX');
    let $y = $('#coordinateY');
    const x = $x.val();
    const y = $y.val();

    $('#cornerTable tbody')
        .append($('<tr>')
            .append($('<td>')
                .append($('<span>')
                    .text(x)
                )
            )
            .append($('<td>')
                .append($('<span>')
                    .text(y)
                )
            )
            .append($('<td>')
                .append($('<input>')
                    .attr('type', 'button')
                    .attr('value', 'Edit')
                    .attr('class', 'btn btn-outline-secondary w-50')
                    .attr('onclick', 'editCorner(this)')
                )
                .append($('<input>')
                    .attr('type', 'button')
                    .attr('value', 'Remove')
                    .attr('class', 'btn btn-outline-secondary w-50')
                    .attr('onclick', 'removeCorner(this)')
                )
            )
        );

    $x.val(''); // clear inputs
    $y.val('');
}

function removeCorner(oButton) {
    $(oButton).closest('tr').remove();
}

function editCorner(oButton) {
    const row = $(oButton).closest('tr');
    const index = row.index();
    $('#editX').val($(row).find('td:eq(0)').find('span').text());
    $('#editY').val($(row).find('td:eq(1)').find('span').text());
    $('#editSave').val(index);
    $('#editModal').modal('show');

}

function saveEditing(oButton) {
    const index = $(oButton).val();
    const row = $('#cornerTable tbody').find('tr:eq(' + index + ')');
    row.find('td:eq(0)').find('span').text($('#editX').val());
    row.find('td:eq(1)').find('span').text($('#editY').val());
    $('#editModal').modal('hide');
}

function readTableValues() {
    const arr = [];
    let i = 0;
    $('#cornerTable tbody').find('tr').each(function () {
        arr[i] = {x: $(this).find('td:eq(0)').find('span').text(),
                  y: $(this).find('td:eq(1)').find('span').text()};
        i++;
    });
    return arr;
}

async function createRoom() {
    let data = {room: readTableValues()};
    console.log(data);

    const response = await fetch("/room/create", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "POST",
        body: JSON.stringify(data)
    });

    const context = await response.json();
    if (response.ok && !context.hasOwnProperty('error')) {
        window.location.href = '/room/all';
    } else {
        errorInform(context.error);
    }
}