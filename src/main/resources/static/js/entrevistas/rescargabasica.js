/**
 * 
 */

 $(document).ready(function(){

	$('#tablanuevos').DataTable({
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros por página",
            "zeroRecords": "No se encontraron registros",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "Sin datos disponibles",
            "infoFiltered": "(filtrado de _MAX_ registros totales)",
			"search":"Buscar: ",
		    "paginate": {
		        "first": "Primero",
		        "last": "Último",
		        "next": "Siguiente",
		        "previous": "Anterior"
		    }
        }
    });
 
	$('#tablamodificados').DataTable({
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros por página",
            "zeroRecords": "No se encontraron registros",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "Sin datos disponibles",
            "infoFiltered": "(filtrado de _MAX_ registros totales)",
			"search":"Buscar: ",
		    "paginate": {
		        "first": "Primero",
		        "last": "Último",
		        "next": "Siguiente",
		        "previous": "Anterior"
		    }
        }
    });

	$('#tablaerroneos').DataTable({
        "language": {
            "lengthMenu": "Mostrar _MENU_ registros por página",
            "zeroRecords": "No se encontraron registros",
            "info": "Mostrando página _PAGE_ de _PAGES_",
            "infoEmpty": "Sin datos disponibles",
            "infoFiltered": "(filtrado de _MAX_ registros totales)",
			"search":"Buscar: ",
		    "paginate": {
		        "first": "Primero",
		        "last": "Último",
		        "next": "Siguiente",
		        "previous": "Anterior"
		    }
        }
    });

 });