window.addEventListener('load', function () {
    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del odontolohgo
    const formulario = document.querySelector('#update_paciente_form');

    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        let pacienteId = document.querySelector('#paciente_id').value;

        //creamos un JSON que tendrá los datos del odontologo
        //a diferencia de un odontologo nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: pacienteId,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            dni: document.querySelector('#dni').value,
            email: document.querySelector('#email').value,
            fechaIngreso: document.querySelector('#fechaIngreso').value,
            domicilio: {
                id: document.querySelector('#domicilio_id').value,
                calle: document.querySelector('#calle').value,
                numero: document.querySelector('#numero').value,
                localidad: document.querySelector('#localidad').value,
                provincia: document.querySelector('#provincia').value,
            }
        };

        //invocamos utilizando la función fetch la API odontologos con el método PUT que modificará
        //al odontologo que enviaremos en formato JSON
        const url = '/pacientes';
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
                let paciente = data;
                let pacienteString = "{nombre: " + paciente.nombre + ", apellido: " + paciente.apellido + ", DNI: " + paciente.dni + ", email: " + paciente.email + ", fecha de ingreso: " + paciente.fechaIngreso + ", domicilio: " + paciente.domicilio.calle + ", N° " + paciente.domicilio.numero + ", localidad: " + paciente.domicilio.localidad + ", provincia: " + paciente.domicilio.provincia + "}";
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> paciente = </strong> ' + pacienteString;
                '</div>'

                $("#response").append(successAlert);
                $("#response").css({ "display": "block" });
                resetUploadForm();

                // Change the updated data for paciente table record
                $("#tr_" + pacienteId + " td.td_id").text(paciente.id);
                $("#tr_" + pacienteId + " td.td_nombre").text(paciente.nombre.toUpperCase());
                $("#tr_" + pacienteId + " td.td_apellido").text(paciente.apellido.toUpperCase());
                $("#tr_" + pacienteId + " td.td_dni").text(paciente.dni);
                $("#tr_" + pacienteId + " td.td_email").text(paciente.email);
                $("#tr_" + pacienteId + " td.td_fechaIngreso").text(paciente.fechaIngreso);
                $("#tr_" + pacienteId + " td.td_domicilio").text(paciente.domicilio.calle + " " + paciente.domicilio.numero);
                $("#tr_" + pacienteId + " td.td_localidad").text(paciente.domicilio.localidad);
                $("#tr_" + pacienteId + " td.td_provincia").text(paciente.domicilio.provincia);

                //$("#response").empty();
                // $("#response").append(successAlert);
                // $("#response").css({ "display": "block" });

            }).catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error </strong></div>';

                $("#response").empty();
                $("#response").append(errorAlert);
                $("#response").css({ "display": "block" });
            })

    });

    function resetUploadForm() {
        $("#nombre").val("");
        $("#apellido").val("");
        $("#dni").val("");
        $("#email").val("");
        $("#fechaIngreso").val("");
        $("#calle").val("");
        $("#numero").val("");
        $("#localidad").val("");
        $("#provincia").val("");
    }
})

//Es la funcion que se invoca cuando se hace click sobre el id de un odontologo del listado
//se encarga de llenar el formulario con los datos del odontologo
//que se desea modificar
function findBy(id) {
    const url = '/pacientes' + "/" + id;
    const settings = {
        method: 'GET'
    }
    fetch(url, settings)
        .then(response => response.json())
        .then(data => {
            let paciente = data;
            document.querySelector('#paciente_id').value = paciente.id;
            document.querySelector('#nombre').value = paciente.nombre;
            document.querySelector('#apellido').value = paciente.apellido;
            document.querySelector('#dni').value = paciente.dni;
            document.querySelector('#fechaIngreso').value = paciente.fechaIngreso;
            document.querySelector('#email').value = paciente.email;
            document.querySelector('#domicilio_id').value = paciente.domicilio.id;
            document.querySelector('#calle').value = paciente.domicilio.calle;
            document.querySelector('#numero').value = paciente.domicilio.numero;
            document.querySelector('#localidad').value = paciente.domicilio.localidad;
            document.querySelector('#provincia').value = paciente.domicilio.provincia;
            //el formulario por default esta oculto y al editar se habilita
            document.querySelector('#div_paciente_updating').style.display = "block";
        }).catch(error => {
            alert("Error: " + error);
        })
}