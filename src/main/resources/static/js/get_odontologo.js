/*window.addEventListener('load', function () {
  (function () {
    event.preventDefault();
    // Con fetch invocamos al API de odontólogos con el método GET
    // Nos devolverá un JSON con una colección de odontólogos
    const url = '/odontologos';
    const settings = {
      method: 'GET'
    }

    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        // Recorremos la colección de odontólogos del JSON
        /*$.each(data, (i, odontologo)) => {
           // Por cada odontologo creamos un boton delete que agregaremos en cada fila para poder eliminar la misma
           // Dicho boton invocara a la funcion de JavaScript deleteByKey que se encargará de llamar al API para eliminar un odontologo
           let deleteButton = '<button' +
                                  ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
                                  ' type="button" onclick="deleteBy(' + odontologo.id + ')" class="btn btn-danger btn_delete">' +
                                  '&times' +
                                  '</button>';

           let get_More_Info_Btn = '<button' +
                                       ' id=' + '\"' + "btn_id_" + odontologo.id + '\"' +
                                       ' type="button" onclick="find(' + odontologo.id + ')" class="btn btn-info btn_id">' +
                                       odontologo.id +
                                       '</button>';

           let tr_id = 'tr_' + odontologo.id;
           let odontologoRow = '<tr id=\"' + tr_id + "\"" + '>' +
                                   '<td>' + get_More_Info_Btn + '</td>' +
                                   '<td class=\"td_first_name\">' + odontologo.name.toUpperCase() + '</td>' +
                                   '<td class=\"td_address\">' + odontologo.lastname + '</td>' +
                                   '<td>' + deleteButton + '</td>' +
                                   '</tr>';
           $('#odontologoTable tbody').append(odontologoRow);
        }));*/
/*for (odontologo of data) { // La implementación del for funciona con Firefox
  // Por cada odontólogo armaremos un fila de la tabla
  // Cada fila tendrá un ID que luego nos permitirá borrar la fila si eliminamos el odontólogo
  var table = document.getElementById("odontologoTable");
  var odontologoRow = table.insertRow();
  let tr_id = 'tr_' + odontologo.id;

  let deleteButton = '<button' +
    ' id=' + '\"' + 'btn_delete_' + odontologo.id + '\"' +
    ' type="button" class="btn btn-danger btn_delete" data-toggle="modal" data-target="#delete-modal" onclick="deleteBy(' + odontologo.id + ')" >' +
    '&times' +
    '</button>';

  // Por cada odontólogo creamos un botón que muestra el ID y que al hacerle clic invocará a la función de JavaScript findBy que se encargará de buscar al odontólogo que queremos modificar y mostrar los datos del mismo en un formulario
  let updateButton = '<button' +
    ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
    ' type="button" onclick="findBy(' + odontologo.id + ')" class="btn btn-info btn_id">' +
    odontologo.id +
    '</button>';

  // Armamos cada columna de la fila
  // Como primer columna pondremos el boton modificar
  // Luego los datos del odontologo
  // Como ultima columna el boton eliminar
  odontologoRow.innerHTML = '<tr id=\"' + tr_id + "\"" + '>' +
    '<td>' + updateButton + '</td>' +
    '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
    '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
    '<td class=\"td_matricula\">' + odontologo.matricula.toUpperCase() + '</td>' +
    '<td>' + deleteButton + '</td>' +
    '</tr>';
};
})
})

(function () {
let pathname = window.location.pathname;
if (pathname == "/odontologoList.html") {
document.querySelector(".nav .nav-item a:last").addClass("active");
}
})
})*/

window.addEventListener('load', function () {
  (function () {
    event.preventDefault();
    const url = '/odontologos';
    const settings = {
      method: 'GET'
    }
    fetch(url, settings)
      .then(response => response.json())
      .then(data => {
        $.each(data, (i, odontologo) => {
          console.log("data " + data, "\ni: " + i, "\nodontologo: " + odontologo);
          let deleteButton = '<button ' +
            'id=' +
            '\"' + 'btn_delete_' + odontologo.id + '\"' +
            ' type="button" class="btn btn-danger btn_delete" data-toggle="modal"  data-target="#delete-modal"' +
            '>&times</button>';

          let get_More_Info_Btn = '<button' +
            ' id=' + '\"' + 'btn_id_' + odontologo.id + '\"' +
            ' type="button" onclick="find(' + odontologo.id + ')" class="btn btn-info btn_id">' +
            odontologo.id +
            '</button>';

          let tr_id = 'tr_' + odontologo.id;
          let odontologoRow = '<tr id=\"' + tr_id + "\"" + '>' +
            '<td>' + get_More_Info_Btn + '</td>' +
            '<td class=\"td_nombre\">' + odontologo.nombre.toUpperCase() + '</td>' +
            '<td class=\"td_apellido\">' + odontologo.apellido.toUpperCase() + '</td>' +
            '<td class=\"td_matricula\">' + odontologo.matricula + '</td>' +
            '<td>' + deleteButton + '</td>' +
            '</tr>';
          $('#odontologoTable tbody').append(odontologoRow);
          console.log("tr_id: " + tr_id, "odontologoRow: " + odontologoRow);
        });

      })
  })

    (function () {
      let pathname = window.location.pathname;
      if (pathname == "/odontologosList.html") {
        $(".nav .nav-item a:last").addClass("active");
      }
    })
})