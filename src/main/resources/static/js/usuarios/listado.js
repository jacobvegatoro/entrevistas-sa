/**
 * 
 */
$(document).ready(function(){
	
    $(".botonEliminar").click(function () {
        var my_id_value = $(this).data('id');
        $(".modal-footer #idUsuario").val(my_id_value);
    });

});