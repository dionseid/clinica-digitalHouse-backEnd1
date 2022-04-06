window.addEventListener('load', function () {
(function () {
            const url2 = '/odontologos';
            const settings2 = {
                method: 'GET'
            }

            fetch(url2, settings2)
                .then(response => response.json())
                .then(data => {
                    for (odontologo of data) {
                        var selectElement2 = document.getElementById('odontologo');
                        selectElement2.add(new Option(odontologo.nombre + " " + odontologo.apellido, odontologo));
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