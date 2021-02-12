const arr = [];

function addCorner() {

    let selX = $('#coordinateX');
    let selY = $('#coordinateY');
    const x = selX.val();
    const y = selY.val();

    const index = $('#cornerTable tbody tr').length;  // get the number of body rows
    arr[index] = {x: x, y: y};

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

    selX.val(''); // clear inputs
    selY.val('');
}

function removeCorner(oButton) {
    const row = $(oButton).closest('tr');
    const index = row.index();
    arr.splice(index, 1);
    row.remove();
}

function editCorner(oButton) {
    const row = $(oButton).closest('tr');
    const index = row.index();

    const selEditX = $('#editX');
    const selEditY = $('#editY');
    selEditX.val(arr[index].x);
    selEditY.val(arr[index].y);
    $('#editModal').modal('show');
    $('#editSave').click(function () {
        const x = selEditX.val();
        const y = selEditY.val();
        arr[index].x = x;
        arr[index].y = y;
        $(row).find("td").eq(0).text(x);
        $(row).find("td").eq(1).text(y);
        $('#editModal').modal('hide');
    });
}

async function createRoom() {
    let data = {room: arr};

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