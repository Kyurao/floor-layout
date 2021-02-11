const arr = [];

function addCorner() {
    const table = document.getElementById('cornerTable');

    const coordinateX = document.getElementById("coordinateX").value;
    const coordinateY = document.getElementById("coordinateY").value;
    let rowCnt = table.rows.length;    // get the number of rows
    arr[rowCnt - 1] = {x: coordinateX, y: coordinateY};
    let tr = table.insertRow(rowCnt); // add table row

    createValueColumn(0, coordinateX);
    createValueColumn(1, coordinateY);

    const tdButton = tr.insertCell(2);
    const button = document.createElement('input');
    button.setAttribute('type', 'button');
    button.setAttribute('value', 'Remove');
    button.setAttribute('class', 'btn btn-outline-secondary w-50');
    button.setAttribute('onclick', 'removeCorner(this)');
    tdButton.appendChild(button);

    clearInput();

    function createValueColumn(colIndex, value) {
        const td = tr.insertCell(colIndex);
        const ele = document.createElement('span');
        ele.textContent = value;
        td.appendChild(ele);
    }

    function clearInput() {
        document.getElementById("coordinateX").value = '';
        document.getElementById("coordinateY").value = '';
    }
}

function removeCorner(oButton) {
    const table = document.getElementById('cornerTable');
    let index = oButton.parentNode.parentNode.rowIndex; // buttton -> td -> tr
    table.deleteRow(index);
    arr.splice(index - 1, 1);
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