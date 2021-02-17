function addRoomToTable(room) {
    $('#roomsTable tbody')
        .append($('<tr>')
            .append($('<td>')
                .append($('<span>')
                    .text(room.id)
                )
            )
            .append($('<td>')
                .append($('<span>')
                    .text(room.size)
                )
            )
            .append($('<td>')
                .append($('<input>')
                    .attr('type', 'button')
                    .attr('value', 'View')
                    .attr('class', 'btn btn-outline-dark w-25 mx-3')
                    .attr('onclick', 'onclick=viewRoom(this)')
                )
                .append($('<input>')
                    .attr('type', 'button')
                    .attr('value', 'Edit')
                    .attr('class', 'btn btn-outline-dark w-25 mx-3')
                    .attr('onclick', 'onclick=editRoom(this)')
                )
                .append($('<input>')
                    .attr('type', 'button')
                    .attr('value', 'Delete')
                    .attr('class', 'btn btn-outline-danger w-25 mx-3')
                    .attr('onclick', 'onclick=deleteRoom(this)')
                )
            )
        );
}

async function getAllRooms() {
    const path = '/room/all';
    const headers = {'Content-Type': 'application/json'};
    const method = 'GET';
    const response = await fetch(path, {
        headers: headers,
        method: method,
    });

    const context = await response.json();
    if (response.ok) {
        for (let i = 0; i < context.length; i++) {
            addRoomToTable(context[i]);
        }
    } else {
        errorInform(context.error);
    }
}

async function deleteRoom(oButton) {
    const id = $(oButton).closest('tr').find('td:eq(0)').find('span').text();
    const path = '/room/delete/' + id;
    const headers = {'Content-Type': 'application/json'};
    const method = 'DELETE';
    const response = await fetch(path, {
        headers: headers,
        method: method,
    });

    if (response.headers.has('Content-Type')) {
        const context = await response.json();
        errorInform(context.error);
    } else {
        removeRow(oButton);
    }
}

function viewRoom(oButton) {
    const id = $(oButton).closest('tr').find('td:eq(0)').find('span').text();
    console.log('view room ' + id);
}

function editRoom(oButton) {
    const id = $(oButton).closest('tr').find('td:eq(0)').find('span').text();
    window.location.href = '/view/edit/' + id;
}


