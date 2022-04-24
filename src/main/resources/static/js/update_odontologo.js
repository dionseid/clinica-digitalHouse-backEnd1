/*window.addEventListener('load', function () {


    //Buscamos y obtenemos el formulario donde estan
    //los datos que el usuario pudo haber modificado del odontolohgo
    const formulario = document.querySelector('#update_odontologo_form');

    formulario.addEventListener('submit', function (event) {
        let peliculaId = document.querySelector('#odontologo_id').value;

        //creamos un JSON que tendrá los datos del odontologo
        //a diferencia de un odontologo nuevo en este caso enviamos el id
        //para poder identificarlo y modificarlo para no cargarlo como nuevo
        const formData = {
            id: document.querySelector('#odontologo_id').value,
            nombre: document.querySelector('#nombre').value,
            apellido: document.querySelector('#apellido').value,
            matricula: document.querySelector('#matricula').value,

        };

        //invocamos utilizando la función fetch la API odontologos con el método PUT que modificará
        //al odontologo que enviaremos en formato JSON
        const url = '/odontologos';
        const settings = {
            method: 'PUT',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(formData)
        }
          fetch(url,settings)
          .then(response => response.json())

    })
 })

    //Es la funcion que se invoca cuando se hace click sobre el id de un odontologo del listado
    //se encarga de llenar el formulario con los datos del odontologo
    //que se desea modificar
    function findBy(id) {
          const url = '/odontologos'+"/"+id;
          const settings = {
              method: 'GET'
          }
          fetch(url,settings)
          .then(response => response.json())
          .then(data => {
              let odontologo = data;
              document.querySelector('#odontologo_id').value = odontologo.id;
              document.querySelector('#nombre').value = odontologo.nombre;
              document.querySelector('#apellido').value = odontologo.apellido;
              document.querySelector('#matricula').value = odontologo.matricula;
              //el formulario por default esta oculto y al editar se habilita
              document.querySelector('#div_odontologo_updating').style.display = "block";
          }).catch(error => {
              alert("Error: " + error);
          })
      }*/

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
                // console.log("JSON response in odontologo: " + odontologo.toString(), odontologo.nombre.toString());
                let odontologoString = "{apellido: " + odontologo.apellido + ", nombre: " + odontologo.nombre +
                    ", matricula: " + odontologo.matricula + "}";
                let successAlert = '<div class="alert alert-success alert-dismissible">' +
                    '<button type="button" class="close" data-dismiss="alert">&times;</button>' +
                    '<strong> odontólogo = </strong> ' + /*data.toString()*/ odontologoString;
                '</div>'

                //$("#response").empty();
                $("#response").append(successAlert);
                $("#response").css({ "display": "block" });
                resetUploadForm();

                // Change the updated data for odontólogo table record
                //$("#tr_" + odontologoId + " td.td_id").text(odontologo.id);
                $("#tr_" + odontologoId + " td.td_nombre").text(odontologo.nombre.toUpperCase());
                $("#tr_" + odontologoId + " td.td_apellido").text(odontologo.apellido.toUpperCase());
                $("#tr_" + odontologoId + " td.td_matricula").text(odontologo.matricula);
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
                $("#tr_" + odontologoId + " td.td_id").text(odontologoData.id);
                $("#tr_" + odontologoId + " td.td_nombre").text(odontologoData.nombre);
                $("#tr_" + odontologoId + " td.td_apellido").text(odontologoData.apellido);
                $("#tr_" + odontologoId + " td.td_matricula").text(odontologoData.matricula);
                event.preventDefault()
            })*/

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