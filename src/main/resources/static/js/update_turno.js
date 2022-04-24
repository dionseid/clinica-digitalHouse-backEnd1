let turnoData;

window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_turno_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        let turnoId = document.querySelector('#turno_id').value;
        console.log("turnoData: " + turnoData);

        const formData = {
            id: turnoId,
            paciente: {
                id: turnoData.paciente.id
            },
            odontologo: {
                id: document.querySelector('#odontologo').value
            },
            diaHora: document.querySelector('#dia').value + "T" + document.querySelector('#hora').value
        };
        const url = '/turnos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                let turno = data;
                //turnoData = data;
                console.log("JSON response in turno: " + turno, turno.nombre);
                let turnoString = "{día y hora: " + turno.diaHora + ", paciente: " + turno.paciente.nombre + " " + turno.paciente.apellido + ", odontólogo: " + turno.odontologo.nombre + " " + turno.odontologo.apellido;
                ", matricula: " + turno.matricula + "}";
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> turno = </strong> ' + /*data.toString()*/ turnoString;
                '</div>'

                //$("#response").empty();
                $("#response").append(successAlert);
                $("#response").css({ "display": "block" });
                resetUploadForm(turno);

                // Change the updated data for turno table record
                //$("#tr_" + turnoId + " td.td_id").text(turno.id);
                $("#tr_" + turnoId + " td.td_diaHora").text(turno.diaHora);
                $("#tr_" + turnoId + " td.td_paciente").text(turno.paciente.nombre + " " + turno.paciente.apellido);
                $("#tr_" + turnoId + " td.td_odontologo").text(turno.odontologo.nombre + " " + turno.odontologo.apellido);
            }).catch(error => {
                console.log(error);
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error </strong></div>';

                //$("#response").empty();
                $("#response").append(errorAlert);
                $("#response").css({ "display": "block" });
                resetUploadForm();
            })/*.finally(() => {
                // Change the updated data for odontólogo table record
                $("#tr_" + turnoId + " td.td_id").text(turnoData.id);
                $("#tr_" + turnoId + " td.td_nombre").text(turnoData.nombre);
                $("#tr_" + turnoId + " td.td_apellido").text(turnoData.apellido);
                $("#tr_" + turnoId + " td.td_matricula").text(turnoData.matricula);
                event.preventDefault()
            })*/

    });

    function resetUploadForm(turno) {
        $("#dia").val("");
        $("#hora").val("");
        console.log(turno);
        document.querySelector(`#actualizarOdontologo${turno.odontologo.id}`).setAttribute("selected", "");
    }
});

function find(id) {
    const url = '/turnos/' + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let turno = data;
            turnoData = turno;
            console.log("turno data: " + { ...turno });
            $("#turno_id").val(turno.id);
            $("#dia").val(turno.diaHora.substring(0, 10));
            $("#hora").val(turno.diaHora.substring(11));
            $("#paciente").val(turno.paciente.nombre + " " + turno.paciente.apellido);
            // $("#odontologo > option").val(turno.odontologo.nombre + " " + turno.odontologo.apellido);
            document.querySelector(`#actualizarOdontologo${turno.odontologo.id}`).setAttribute("selected", "");
            $("#div_turno_updating").css({ "display": "block" });
        }).catch(error => {
            console.log(error);
            alert("Error -> " + error);
        })
}