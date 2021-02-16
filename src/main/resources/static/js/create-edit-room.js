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
}

function clearInput($x, $y) {
    $x.val('');
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
    const data = {room: readTableValues()};
    console.log(data);

    const response = await fetch("/room/create", {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "POST",
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
        window.location.href = '/room/all';
    }
}

async function getRoom(roomId) {
    const url = "/room/" + roomId;
    const response = await fetch(url, {
        headers: {
            'Content-Type': 'application/json'
        },
        method: "GET"
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

