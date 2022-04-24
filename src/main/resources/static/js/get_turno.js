window.onload = () => {
    fetch("http://localhost:8080/turnos/")
        .then((response) => response.json())
        .then((data) => cargarInformacionTurnos(data));
};

function cargarInformacionTurnos(turnos) {
    const tablaTurnos = document
        .getElementById("div_turno_table")
        .getElementsByTagName("tbody")[0];

    turnos.forEach((turno) => {
        let tr_id = 'tr_' + turno.id;
        let deleteButton = '<button ' +
            'id=' +
            '\"' + 'btn_delete_' + turno.id + '\"' +
            ' type="button" class="btn btn-danger btn_delete" data-toggle="modal"  data-target="#delete-modal"' +
            '>&times</button>';
        let get_More_Info_Btn = '<button' +
            ' id=' + '\"' + 'btn_id_' + turno.id + '\"' +
            ' type="button" onclick="find(' + turno.id + ')" class="btn btn-info btn_id">' +
            turno.id +
            '</button>';

        tablaTurnos.innerHTML += `
            <tr id="${tr_id}">
                <td>${get_More_Info_Btn}</td> 
                <td class="td_diaHora">${turno.diaHora}</td>
                <td class="td_paciente">${turno.paciente.nombre.toUpperCase() + " " + turno.paciente.apellido.toUpperCase()}</td>
                <td class="td_odontologo">${turno.odontologo.nombre.toUpperCase() + " " + turno.odontologo.apellido.toUpperCase()}</td>
                <td>${deleteButton}</td> 
            </tr>
        `;
    });
}