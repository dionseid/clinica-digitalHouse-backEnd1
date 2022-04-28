window.addEventListener('load', function () {
    // Al cargar la pagina buscamos y obtenemos el formulario donde estarán los datos que le usuarie cargará del nuevo turno
    const formulario = document.querySelector('#add_new_turno');

    // Ante un submit del formulario se ejecutará la siguiente función
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        // Creamos un JSON que tendrá los datos de un nuevo turno
        //const select = document.querySelector('#paciente')
        const idOdontologo = document.querySelector('#odontologo').value;
        const idPaciente = document.querySelector('#paciente').value;
        const fecha = document.querySelector('#fecha').value;
        const hora = document.querySelector('#hora').value;

        const formData = {
            diaHora: `${fecha}T${hora}`,
            paciente: {
                id: idPaciente
            },
            odontologo: {
                id: idOdontologo
            }
            //paciente: document.querySelector('#paciente').option[document.querySelector('#paciente').selectedIndex],
            //odontologo: document.querySelector('#odontologo > option').value,
        };
        console.log(formData);

        // Invocamos utilizando la función fetch le API turnos con el método POST que guardará el turno que enviaremos en formato JSON
        const url = '/turnos';
        const settings = {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }

        fetch(url, settings)
            .then(response => response.json())
            .then(data => {
                // Si no hay ningun error se muestra un mensaje diciendo que el turno se agrego bien
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Turno agregado </div>'

                document.querySelector('#response').innerHTML = successAlert;
                document.querySelector('#response').style.display = "block";
                resetUploadForm();
            }).catch(error => {
                // Si hay algun error se muestra un mensaje diciendo que el turno no se pudo guardar y se intente nuevamente
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'

                document.querySelector('#response').innerHTML = errorAlert;
                document.querySelector('#response').style.display = "block";

                // Se dejan todos los campos vacíos por si se quiere ingresar otro turno
                resetUploadForm();
            });
    });

    function resetUploadForm() {
        document.querySelector('#fecha').value = "";
        document.querySelector('#hora').value = "";
        document.querySelector('#paciente > option').value = "";
        document.querySelector('#odontologo > option').value = "";
    }

    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            document.querySelector(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/turnoList.html") {
            document.querySelector(".nav .nav-item a:last").addClass("active");
        }
    })();
});