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