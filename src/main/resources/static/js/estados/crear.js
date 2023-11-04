/**
 * 
 */
$(document).ready(function(){

	//var servidor = $(location).attr('origin');
	//var response = true;
	
	$("form[name='frmestados']").validate({
		rules: {
			txtNombre: {
				required: true,
				minlength: 5,
				maxlength: 100
			}
		},
		messages: {
			txtNombre: {
				required: "Ingresa el nombre",
				minlength: "El largo mínimo es de 5 caracteres",
				maxlength: "El largo máximo es de 100 caracteres"
			}
		},

		submitHandler: function(form) {
			form.submit();
		}
	});
		
});