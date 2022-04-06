/*window.addEventListener('load', function () {
    (function(){
      // Con fetch invocamos al API de pacientes con el método GET
      // Nos devolverá un JSON con un colección de pacientes
      const url = '/pacientes';
      const settings = {
        method: 'GET'
      }

      fetch(url,settings)
      .then(response => response.json())
      .then(data => {
      // Recorremos la colección de pacientes del JSON
         for (paciente of data) {
            // Por cada paciente armaremos un fila del selector
            // Cada fila tendrá un ID que luego nos permitirá borrar la fila si eliminamos el odontologo
            //var selector = document.getElementById("paciente");

            var selectElement = document.getElementById('paciente');
            selectElement.add(new Option(paciente.nombre + " " + paciente.apellido, paciente));
            /*for (var age = 0; age < data.length; age++) {
              selectElement.add(new Option(paciente.nombre + " " + paciente.apellido));
            }*/






            /*var pacienteRow = table.insertRow();
            let tr_id = 'tr_' + paciente.id;
            pacienteRow.id = tr_id;*/

            //por cada odontologo creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
            //dicho boton invocara a la funcion de java script deleteByKey que se encargará
            //de llamar a la API para eliminar un odontologo
            /*let deleteButton = '<button' +
                                      ' id=' + '\"' + 'btn_delete_' + paciente.id + '\"' +
                                      ' type="button" onclick="deleteBy('+paciente.id+')" class="btn btn-danger btn_delete">' +
                                      '&times' +
                                      '</button>';*/

            //por cada odontologo creamos un boton que muestra el id y que al hacerle clic invocará
            //a la función de java script findBy que se encargará de buscar al odontologo que queremos
            //modificar y mostrar los datos del mismo en un formulario.
            /*let updateButton = '<button' +
                                      ' id=' + '\"' + 'btn_id_' + paciente.id + '\"' +
                                      ' type="button" onclick="findBy('+paciente.id+')" class="btn btn-info btn_id">' +
                                      paciente.id +
                                      '</button>';*/

            //armamos cada columna de la fila
            //como primer columna pondremos el boton modificar
            //luego los datos del odontologo
            //como ultima columna el boton eliminar
            /*pacienteRow.innerHTML = '<td>' + updateButton + '</td>' +
                    '<td class=\"td_nombre\">' + paciente.nombre.toUpperCase() + '</td>' +
                    '<td class=\"td_apellido\">' + paciente.apellido.toUpperCase() + '</td>' +
                    '<td class=\"td_dni\">' + paciente.dni + '</td>' +
                    '<td class=\"td_fechaIngreso\">' + paciente.fechaIngreso + '</td>' +
                    '<td class=\"td_domicilio\"> Calle ' + paciente.domicilio.calle + ' ' + paciente.domicilio.numero + '</td>' +
                    '<td class=\"td_email\">' + paciente.email + '</td>' +

                    '<td>' + deleteButton + '</td>';*/

        /*}

    })
    })

    (function() {
        const url = '/odontologos';
        const settings = {
            method: 'GET'
        }

        fetch(url,settings)
              .then(response => response.json())
              .then(data => {
              // Recorremos la colección de pacientes del JSON
                 for (odontologo of data) {
                    // Por cada paciente armaremos un fila del selector
                    // Cada fila tendrá un ID que luego nos permitirá borrar la fila si eliminamos el odontologo
                    //var selector = document.getElementById("paciente");

                    var selectElement2 = document.getElementById('odontologo');
                    selectElement2.add(new Option(odontologo.nombre + " " + odontologo.apellido, odontologo));
                 }})

    });

    (function(){
      let pathname = window.location.pathname;
      if (pathname == "/turnoList.html") {
          document.querySelector(".nav .nav-item a:last").addClass("active");
      }
    })


    })*/




    window.addEventListener('load', function () {
        (function () {
            const url = '/pacientes';
            const settings = {
                method: 'GET',
            }

            fetch(url, settings)
                .then(response => response.json())
                .then(data => {
                    for (paciente of data) {
                        var selectElement = document.getElementById('paciente');
                        selectElement.add(new Option(paciente.nombre + " " + paciente.apellido, paciente));
                    }
                })
        })

        (function () {
            let pathname = window.location.pathname;
            if (pathname == "/turnoList.html") {
                document.querySelector(".nav .nav-item a:last").addClass("active");
            }
        })
    });
