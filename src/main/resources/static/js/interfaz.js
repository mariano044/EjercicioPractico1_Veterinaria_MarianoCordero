document.addEventListener('DOMContentLoaded', function () {
    // Muestra los toasts de resultado (éxito / error)
    document.querySelectorAll('.toast').forEach(function (aviso) {
        new bootstrap.Toast(aviso, { delay: 5000 }).show();
    });

    // Rellena el modal de eliminación con los datos del elemento elegido
    var modalEliminar = document.getElementById('modalEliminar');
    if (modalEliminar) {
        modalEliminar.addEventListener('show.bs.modal', function (evento) {
            var boton = evento.relatedTarget;
            document.getElementById('idElemento').value = boton.getAttribute('data-id');
            document.getElementById('nombreElemento').textContent = boton.getAttribute('data-nombre');
        });
    }
});
