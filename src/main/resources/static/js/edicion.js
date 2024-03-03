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
					console.log($.Rut.formatear(value,"1"));
					var entrevistadoId = $("#idEntrevistado").val();
					if (data.length > 0){
						if (data.length == 1 && data[0].idEntrevistado == entrevistadoId){
							response = true;
						}
						else{
							response = false;
							$("#run").focus();							
						}
					}
					else{
						response = true;
					}
					console.log(response);
                }
            });
            return response;
        },
        "RUN ya está registrado"
    );
	
	$("#slcRegion").change(function (){
		var idregion = $("#slcRegion").val();
		//var servidor = $(location).attr('origin');
		var comunas = $("#slcComuna");
		//alert(servidor + " - Cambio las comunas Region: " + idregion);
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
	
	$("#slcRegionEmergencia").change(function (){
		var idregion = $("#slcRegionEmergencia").val();
		//var servidor = $(location).attr('origin');
		var comunas = $("#slcComunaEmergencia");
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
	
	$("form[name='frmeditaentrevistado']").validate({
    rules: {
		/*"reclutador.id": "required",*/
		run: {
			required: true,
			runValido: true,
			runUnico: true
		},
		"cliente.id": "required",
        nombres: "required",
        apPaterno: "required",
		slcRegion: "required",
		slcComuna: "required",
		"canal.id": "required",
		"cargo.id": "required"
    },
    messages: {
		nombres:{
			required: "Ingresa los nombres"
		},
		apPaterno:{
			required: "Ingresa el apellido paterno"
		},
		run:{
			required: "Ingresa el RUN del entrevistado",
			runValido: "El RUN ingresado no es válido",
			runUnico: "Este RUN ya existe"
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
	
	$('#run').Rut();

	/*
	$("#run").keyup(function(){
		var run = $("#run").val();
		run = run.replace(/[^0-9\.\-]+/g, "");
		$("#run").val(run);
	});

	$("#run").rut({formatOn: 'keyup'});

	$("#run").rut({formatOn: 'change'});
	
	$("#run").on('rutInvalido',function(){
		$(this).addClass("error");
	});

	$("#run").on('rutValido',function(){
		$(this).removeClass("error");
	});
	*/

});