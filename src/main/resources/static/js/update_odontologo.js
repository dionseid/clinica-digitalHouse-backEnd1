window.addEventListener('load', function () {
    const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        let odontologoId = document.querySelector('#odontologo_id').value;
        let odontologoData;

        const formData = {
            id: odontologoId,
            apellido: document.querySelector('#apellido').value,
            nombre: document.querySelector('#nombre').value,
            matricula: document.querySelector('#matricula').value
        };
        const url = '/odontologos';
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
                let odontologo = data;
                odontologoData = data;
                let odontologoString = "{apellido: " + odontologo.apellido + ", nombre: " + odontologo.nombre +
                    ", matricula: " + odontologo.matricula + "}";
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> odontólogo = </strong> ' + odontologoString;
                '</div>'

                $("#response").append(successAlert);
                $("#response").css({ "display": "block" });
                resetUploadForm();

                // Change the updated data for odontólogo table record
                $("#tr_" + odontologoId + " td.td_nombre").text(odontologo.nombre.toUpperCase());
                $("#tr_" + odontologoId + " td.td_apellido").text(odontologo.apellido.toUpperCase());
                $("#tr_" + odontologoId + " td.td_matricula").text(odontologo.matricula);
            }).catch(error => {
                console.log(error);
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error </strong></div>';

                $("#response").append(errorAlert);
                $("#response").css({ "display": "block" });
                resetUploadForm();
            })
    });

    function resetUploadForm() {
        $("#nombre").val("");
        $("#apellido").val("");
        $("#matricula").val("");
    }
});

function find(id) {
    const url = '/odontologos' + "/id/" + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let odontologo = data;
            console.log("odontólogo data: " + odontologo);
            $("#odontologo_id").val(odontologo.id);
            $("#nombre").val(odontologo.nombre);
            $("#apellido").val(odontologo.apellido);
            $("#matricula").val(odontologo.matricula);
            $("#div_odontologo_updating").css({ "display": "block" });
        }).catch(error => {
            console.log(error);
            alert("Error -> " + error);
        })
}