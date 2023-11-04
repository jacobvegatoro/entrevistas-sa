/**
 * 
 */

$(document).ready(function(){
	
	$("form[name='frmactmasiva']").validate({
	    rules: {
	        flCambiaEstado: {
				required: true
			}
	    },
	    messages: {
			flCambiaEstado:{
				required: "Ingresa un archivo a procesar"
			}
	    },
	    // Make sure the form is submitted to the destination defined
	    // in the "action" attribute of the form when valid
	    submitHandler: function(form) {
	      form.submit();
	    }
  });
	
});