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
                    selectElement.add(new Option(paciente.nombre + " " + paciente.apellido, paciente.id));
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
