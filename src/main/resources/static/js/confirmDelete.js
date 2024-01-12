function confirmDelete(productId) {
    Swal.fire({
        title: '¿Estás seguro?',
        text: '¿Deseas eliminar este producto?',
        icon: 'warning',
        showCancelButton: true,
        confirmButtonColor: '#d33',
        cancelButtonColor: '#3085d6',
        confirmButtonText: 'Sí, eliminar'
    }).then((result) => {
        if (result.isConfirmed) {
            // Aquí puedes realizar alguna acción adicional si es necesario antes de redirigir

            window.location.href = '/products/delete/' + productId;

        }
    });
}
