/**
 * 
 */

$(document).ready(function(){

	var servidor = $(location).attr('origin');
	var response = true;

	$.validator.addMethod(
		"clavesIguales", 
		function(value, element) {
			var clave = $("#txtClave").val();
			if (clave == value){
				return true;
			}
			else{
				return false;
			}
		},
		"Las claves deben ser iguales."
	);

	$.validator.addMethod(
		"textoSinEspacios", 
		function(value, element) {
			var texto = value.replace(' ','');
			if (texto.trim().length == value.length){
				return true;
			}
			else{
				return false;
			}
		},
		"El texto ingresado no puede tener espacios en blanco."
	);
	
	/*
	$.validator.addMethod(
        "usuarioUnico", 
        function(value, element) {
            $.ajax({
                type: "GET",
                url: servidor + "/api/v1/usuarios/" + value,
                dataType:"json",
                success: function(data)
                {
					var usuarioId = $("#txtId").val();
					if (data.length > 0){
						if (data.length == 1 && data[0].id == usuarioId){
							response = true;
						}
						else{
							response = false;
							$("#txtNombreUsuario").focus();						
						}
					}
					else{
						response = true;
					}
					//console.log(data);
					//console.log(response);
                }
            });
            return response;
        },
        "El nombre de usuario ya está registrado"
    );
	*/

  $("form[name='frmusuario']").validate({
    rules: {
		txtId:{
			required: true
		},
        txtNombres: {
			required: true,
			minlength: 5,
			maxlength: 50
		},
        txtApellidos: {
			required: true,
			minlength: 5,
			maxlength: 50
		},
        txtNombreUsuario: {
			required: true,
			minlength: 5,
			maxlength: 30,
			textoSinEspacios: true
			//usuarioUnico: true
		},
		txtCorreo: {
			required: true,
			email: true,
			minlength: 10,
			maxlength: 50
		},
		slcRol: {
			required: true
		},
		slcActivo: {
			required: true
		},
		txtClave: {
			required: true,
			minlength: 8,
			maxlength: 30
		},
		txtClaveDos: {
			required: true,
			clavesIguales: true,
			minlength: 8,
			maxlength: 30
		}
    },
    messages: {
		txtId:{
			required: "Debe existir un identificador"
		},
		txtNombres:{
			required: "Ingresa los nombres",
			minlength: "El largo mínimo es de 5 caracteres",
			maxlength: "El largo máximo es de 50 caracteres"
		},
		txtApellidos:{
			required: "Ingresa los apellidos",
			minlength: "El largo mínimo es de 5 caracteres",
			maxlength: "El largo máximo es de 50 caracteres"
		},
		txtNombreUsuario:{
			required: "Ingresa el nombre de usuario",
			minlength: "El largo mínimo es de 5 caracteres",
			maxlength: "El largo máximo es de 30 caracteres",
			textoSinEspacios: "No puede tener espacios en blanco"
			//usuarioUnico: "Ya existe un usuario con este identificador"
		},
		txtCorreo:{
			required: "Ingresa un correo electrónico",
			email: "Debe ingresar un correo válido",
			minlength: "El largo mínimo es de 10 caracteres",
			maxlength: "El largo máximo es de 50 caracteres"
		},
		slcRol:{
			required: "Selecciona un rol"
		},
		slcActivo:{
			required: "Selecciona activación"
		},
		txtClave:{
			required: "Ingresa una clave",
			minlength: "El largo mínimo es de 8 caracteres",
			maxlength: "El largo máximo es de 30 caracteres"
		},
		txtClaveDos:{
			required: "Ingresa nuevamente la clave",
			clavesIguales: "Las claves ingresadas deben ser iguales",
			minlength: "El largo mínimo es de 8 caracteres",
			maxlength: "El largo máximo es de 30 caracteres"
		}
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });

  $("form[name='frmclave']").validate({
    rules: {
		txtClave: {
			required: true,
			minlength: 8,
			maxlength: 30
		},
		txtClaveDos: {
			required: true,
			clavesIguales: true,
			minlength: 8,
			maxlength: 30
		}
    },
    messages: {
		txtClave:{
			required: "Ingresa una clave",
			minlength: "El largo mínimo es de 8 caracteres",
			maxlength: "El largo máximo es de 30 caracteres"
		},
		txtClaveDos:{
			required: "Ingresa nuevamente la clave",
			clavesIguales: "Las claves ingresadas deben ser iguales",
			minlength: "El largo mínimo es de 8 caracteres",
			maxlength: "El largo máximo es de 30 caracteres"
		}
    },
    // Make sure the form is submitted to the destination defined
    // in the "action" attribute of the form when valid
    submitHandler: function(form) {
      form.submit();
    }
  });

});