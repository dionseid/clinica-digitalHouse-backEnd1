/*window.addEventListener('load', function () {
    // Al cargar la pagina buscamos y obtenemos el formulario donde estarán los datos que le usuarie cargara del nuevo odontologo
    const formulario = document.querySelector('#add_new_odontologo');

    // Ante un submit del formulario se ejecutará la siguiente funcion
    formulario.addEventListener('submit', function (event) {
        console.log(event);
        console.log(event.preventDefault());
        event.preventDefault();
        // Creamos un JSON que tendrá los datos de un nuevo odontólogo
        const formData = {
            apellido: document.querySelector('#apellido').value,
            nombre: document.querySelector('#nombre').value,
            matricula: document.querySelector('#matricula').value
        };

        // Invocamos utilizando la función fetch le API odontologos con el método POST que guardará el odontologo que enviaremos en formato JSON
        const url = '/odontologos';

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
                // Si no hay ningun error se muestra un mensaje diciendo que el odontologo se agrego bien
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> Odontologo agregado </div>'
                $("#response").append(successAlert);
                $("#response").css({ "display": "block" });
                resetUploadForm();
                event.preventDefault();
            }) // Si hay algun error se muestra un mensaje diciendo que el odontologo no se pudo guardar y se intente nuevamente
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'
                $("#response").append(errorAlert);
                $("#response").css({ "display": "block" });
                resetUploadForm();
            })
            .finally(() => {
                event.preventDefault()
                console.log(event);
                console.log(event.preventDefault());
            });
    });

    function resetUploadForm() {
        $("#nombre").val("");
        $("#apellido").val("");
        $("#matricula").val("");
    }

    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/odontologoList.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});

/*$(document).ready(function() {
    $("#add_new_odontologo").submit(function(evt) {
        evt.preventDefault();

        let formData = {
            nombre : $("#nombre").val(),
            apellido :  $("#apellido").val(),
            matricula: $("#matricula").val(),
        }

        $.ajax({
            url: '/odontologos',
            type: 'POST',
            contentType : "application/json",
            data: JSON.stringify(formData),
            dataType : 'json',
            async: false,
            cache: false,
            success: function (response) {
                let odontologo = response
               console.log("desde post " + response)
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> odontologo agregado </div>'
                $("#response").append(successAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            },
            error: function (response) {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error intente nuevamente</strong> </div>'
                $("#response").append(errorAlert);
                $("#response").css({"display": "block"});

                resetUploadForm();
            }
        });
    });

    function resetUploadForm(){
        $("#nombre").val("");
        $("#apellido").val("");
        $("#matricula").val("");
    }

    (function(){
        let pathname = window.location.pathname;
        if(pathname === "/"){
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/odontologos.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});*/

window.addEventListener('load', function () {
    const formulario = document.querySelector('#add_new_odontologo');
    formulario.addEventListener('submit', function (event) {
        event.preventDefault();
        const formData = {
            apellido: document.querySelector('#apellido').value,
            nombre: document.querySelector('#nombre').value,
            matricula: document.querySelector('#matricula').value
        };

        const url = '/odontologos';

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
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong></strong> odontólogo agregado </div>'
                $("#response").append(successAlert);
                $("#response").css({ "display": "block" });
                resetUploadForm();
            })
            .catch(error => {
                let errorAlert = '<div class="alert alert-danger alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> Error, intente nuevamente</strong> </div>'
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

    (function () {
        let pathname = window.location.pathname;
        if (pathname === "/") {
            $(".nav .nav-item a:first").addClass("active");
        } else if (pathname == "/studentsList.html") {
            $(".nav .nav-item a:last").addClass("active");
        }
    })();
});