$(document).ready(function () {
    let turnoId = 0;

    $(document).on("click", "#div_turno_table table button.btn_delete", function () {
        let btn_id = (event.srcElement.id);
        turnoId = btn_id.split("_")[2];

        $("div.modal-body")
            .text("Querés eliminar el turno con ID = " + turnoId + "?");
        $("#model-delete-btn").css({ "display": "inline" });
    });

    $(document).on("click", "#model-delete-btn", function () {
        $.ajax({
            url: '/turnos/' + turnoId,
            type: 'DELETE',
            success: function (response) {
                $("div.modal-body")
                    .text("eliminado correctamente!");

                $("#model-delete-btn").css({ "display": "none" });
                $("button.btn.btn-secondary").text("Close");

                // Delete the turno row on HTML page
                let row_id = "tr_" + turnoId;
                $("#" + row_id).remove();
                $("#div_turno_updating").css({ "display": "none" });
            },
            error: function (error) {
                console.log(error);
                $("#div_turno_updating").css({ "display": "none" });
                alert("Error -> " + error);
            }
        });
    });
});