/**
 * 
 */

$(document).ready(function(){
	
	$("form[name='frmcargabasica']").validate({
	    rules: {
	        flCargaBasica: {
				required: true
			}
	    },
	    messages: {
			flCargaBasica:{
				required: "Ingresa un archivo para la carga básica"
			}
	    },
	    // Make sure the form is submitted to the destination defined
	    // in the "action" attribute of the form when valid
	    submitHandler: function(form) {
	      form.submit();
	    }
  });

	$("form[name='frmcargacompleta']").validate({
	    rules: {
	        flCargaCompleta: {
				required: true
			}
	    },
	    messages: {
			flCargaCompleta:{
				required: "Ingresa un archivo para la carga completa"
			}
	    },
	    // Make sure the form is submitted to the destination defined
	    // in the "action" attribute of the form when valid
	    submitHandler: function(form) {
	      form.submit();
	    }
  });
	
});