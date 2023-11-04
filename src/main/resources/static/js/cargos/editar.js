/**
 * 
 */

$(document).ready(function(){
	
	$("form[name='frmcargos']").validate({
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