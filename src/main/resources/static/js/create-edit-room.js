function addCorner(x, y) {

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
                    .attr('class', 'btn btn-outline-secondary w-25 mx-3')
                    .attr('onclick', 'editCorner(this)')
                )
                .append($('<input>')
                    .attr('type', 'button')
                    .attr('value', 'Remove')
                    .attr('class', 'btn btn-outline-danger w-25 mx-3')
                    .attr('onclick', 'removeRow(this)')
                )
            )
        );
}

function clearInput($x, $y) {
    $x.val('');
    $y.val('');
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

async function saveRoom(id) {
    const data = {room: readTableValues()};
    const headers = {'Content-Type': 'application/json'};
    let path;
    let method;
    if (id == null) {
        method = 'POST';
        path = '/room/create';
    } else {
        method = 'PUT';
        path = '/room/update/' + id;
    }

    const response = await fetch(path, {
        headers: headers,
        method: method,
        body: JSON.stringify(data)
    });

    if (response.headers.has('Content-Type')) {
        const context = await response.json();
        if (response.ok && context.hasOwnProperty('error')) {
            errorInform(context.error);
        } else {
            errorInform(context.message);
        }
    } else {
        window.location.href = '/view/all';
    }
}

async function getRoom(roomId) {
    const path = '/room/' + roomId;
    const headers = {'Content-Type': 'application/json'};
    const method = 'GET';
    const response = await fetch(path, {
        headers: headers,
        method: method,
    });

    const context = await response.json();
    if (response.ok && context.hasOwnProperty('corners')) {
        const corners = context.corners;
        for (let i = 0; i < corners.length; i++) {
            addCorner(corners[i].x, corners[i].y);
        }
    } else {
        errorInform(context.error);
    }
}

