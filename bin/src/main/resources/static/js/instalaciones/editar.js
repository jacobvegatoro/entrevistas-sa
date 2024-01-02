/**
 * 
 */

$(document).ready(function(){

	var servidor = $(location).attr('origin');
	//var response = true;

	$.validator.addMethod(
		"runValido", 
		function(value, element) {
			var valida = this.optional(element) || $.Rut.validar(value);
			if (valida){
				return true;
			}
			else{
				$("#txtRun").focus();
				return false;
			}
		},
		"RUN debe ser válido."
	);

	$("#slcRegion").change(function (){
		var idregion = $("#slcRegion").val();
		var comunas = $("#slcComuna");
		$.ajax({ 
             type: "GET",
             dataType: "json",
             url: servidor + "/api/v1/comunas/" + idregion,
             success: function(data){        
                comunas.find('option').remove();
                comunas.append('<option value="">Selecciona una opción</option>');
                $(data).each(function(i, v){ // indice, valor
                    comunas.append('<option value="' + v.id + '">' + v.nombreComuna + '</option>');
                })
             }
         });
	});
	
	$("form[name='frminstalaciones']").validate({
		rules: {
			txtRun: {
				required: true,
				runValido: true
			},
			txtNombre: {
				required: true,
				minlength: 5,
				maxlength: 100
			},
			slcRegion: {
				required: true
			},
			slcComuna: {
				required: true
			}
		},
		messages: {
			txtRun: {
				required: "Ingresa el RUT",
				runValido: "El RUT ingresado no es válido"
			},
			txtNombre: {
				required: "Ingresa el nombre",
				minlength: "El largo mínimo es de 5 caracteres",
				maxlength: "El largo máximo es de 100 caracteres"
			},
			slcRegion: {
				required: "Selecciona una región"
			},
			slcComuna: {
				required: "Selecciona una comuna"
			}
		},
		// Make sure the form is submitted to the destination defined
		// in the "action" attribute of the form when valid
		submitHandler: function(form) {
			form.submit();
		}
	});
	
	$('#txtRun').Rut();	
	
});