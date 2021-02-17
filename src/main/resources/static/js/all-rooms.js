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
                .append($('<button>')
                    .attr('type', 'button')
                    .attr('value', room.corners)
                    .attr('class', 'btn btn-outline-dark w-25 mx-3')
                    .attr('onclick', 'onclick=viewRoom(this)')
                    .text('View')
                )
                .append($('<button>')
                    .attr('type', 'button')
                    .attr('value', room.id)
                    .attr('class', 'btn btn-outline-dark w-25 mx-3')
                    .attr('onclick', 'onclick=editRoom(this)')
                    .text('Edit')
                )
                .append($('<button>')
                    .attr('type', 'button')
                    .attr('value', room.id)
                    .attr('class', 'btn btn-outline-danger w-25 mx-3')
                    .attr('onclick', 'onclick=deleteRoom(this)')
                    .text('Delete')
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
    const id = $(oButton).val();
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
    $('#viewModal').modal('show');
}

function editRoom(oButton) {
    const id = $(oButton).val();
    window.location.href = '/view/edit/' + id;
}


