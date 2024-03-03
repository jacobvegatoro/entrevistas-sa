$(document).ready(function(){

	var servidor = $(location).attr('origin');
	var response = true;

	$.validator.addMethod(
		"runValido", 
		function(value, element) {
			var valida = this.optional(element) || $.Rut.validar(value);
			if (valida){
				return true;
			}
			else{
				$("#run").focus();
				return false;
			}
		},
		"RUN debe ser válido."
	);

    $.validator.addMethod(
        "runUnico", 
        function(value, element) {
            $.ajax({
                type: "GET",
                url: servidor + "/api/v1/entrevistados/" + $.Rut.formatear(value,"1"),
                dataType:"json",
                success: function(data)
                {
					if (data.length > 0){
						response = false;
						$("#run").focus();
					}
					else{
						response = true;
					}
					//console.log(response);
                }
            });
            return response;
        },
        "RUN ya está registrado"
    );
	
	$("form[name='frmentrevistado']").validate({
    rules: {
        nombres: "required",
        apPaterno: "required",
		txtFechaIngreso: "required",
		run: 
		{
			required: true,
			runUnico: true,
			runValido: true
		},
		"cliente.id": "required",
		slcRegion: "required",
		slcComuna: "required",
		"canal.id": "required",
		"cargo.id": "required"/*,
		"reclutador.id": "required"*/
    },
    messages: {
		nombres:{
			required: "Ingresa los nombres"
		},
		apPaterno:{
			required: "Ingresa el apellido paterno"
		},
		txtFechaIngreso:{
			required: "Ingresa la fecha"			
		},
		run:{
			required: "Ingresa el RUN del entrevistado",
			runUnico: "Este RUN ya existe",
			runValido: "El RUN ingresado no es válido"
		},
		"cliente.id":{
			required: "Selecciona un cliente"
		},
		slcRegion:{
			required: "Selecciona una región"
		},
		slcComuna:{
			required: "Selecciona una comuna"
		},
		"canal.id":{
			required: "Selecciona un canal"
		},
		"cargo.id":{
			required: "Selecciona un cargo"
		}/*,
		"reclutador.id":{
			required: "Selecciona un reclutador"
		}*/
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });

	$("#slcRegion").change(function (){
		var idregion = $("#slcRegion").val();
		var comunas = $("#slcComuna");
		//alert(servidor + " - Cambio las comunas Region: " + idregion);
		$.ajax({ 
             type: "GET",
             dataType: "json",
             url: servidor + "/api/v1/comunas/" + idregion,
             success: function(data){        
                //alert(data);
                comunas.find('option').remove();
                comunas.append('<option value="">Selecciona una opción</option>');
                $(data).each(function(i, v){ // indice, valor
                    comunas.append('<option value="' + v.id + '">' + v.nombreComuna + '</option>');
                })
             }
         });
	});
	
	$('#run').Rut();
	
	/*$('#run').Rut({
		on_error: function(){ 
			$("#run").focus();
			$("#run").addClass("error");
			$("#run").css("background-color", "#E09385");
			
			$("#runAyuda").removeClass("text-muted");
			$("#runAyuda").css("color", "#FF0000");
			$("#runAyuda").html("El RUN ingresado es incorrecto");
		},
		on_success: function(){

			$("#run").css("background-color", "white");
			$("#run").removeClass("error");

			$("#runAyuda").css("color", "#6c757d");
			$("#runAyuda").addClass("text-muted");
			$("#runAyuda").html("Ingresa el RUN del entrevistado.");
		}
	});*/
		
});