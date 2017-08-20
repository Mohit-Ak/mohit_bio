ResumeUtil={
		validateEmail : function(email){
			    var re = /^(([^<>()\[\]\\.,;:\s@"]+(\.[^<>()\[\]\\.,;:\s@"]+)*)|(".+"))@((\[[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}\.[0-9]{1,3}])|(([a-zA-Z\-0-9]+\.)+[a-zA-Z]{2,}))$/;
			    return re.test(email);
		},
		contactMeQuery : function(){				  
				  console.log("SPLUtil.contactusQuery has been called");
				  
				  var senderName = $("form#contact-form input[name='username']")[0].value;
				  var senderEmail = $("form#contact-form input[name='email']")[0].value;
				  var MsgTitle = $("form#contact-form input[name='subject']")[0].value;
				  var senderMsg = $("form#contact-form textarea[name='message']")[0].value;
				  
				  var content="Sender Name - "+ senderName+" Email - "+senderEmail+" Subject - "+ MsgTitle+ " Message - "+senderMsg;
					
				  var params ={};
				  params.subject="[Auto-Reply] Mohit Resume - Query Received";
				  params.content=content;
					
					
					$.post( "mohitresume/sendMail",params)
					  .done(function( data ) {
						  	  
						  	Command: toastr["info"](" I will get back to you real quick!", "Thank you for contacting me.")

							  toastr.options = {
							    "closeButton": false,
							    "debug": false,
							    "newestOnTop": false,
							    "progressBar": true,
							    "positionClass": "toast-top-right",
							    "preventDuplicates": false,
							    "onclick": null,
							    "showDuration": "300",
							    "hideDuration": "1000",
							    "timeOut": "5000",
							    "extendedTimeOut": "1000",
							    "showEasing": "swing",
							    "hideEasing": "linear",
							    "showMethod": "fadeIn",
							    "hideMethod": "fadeOut"
							  }
					  
							  var innerParams ={};
							  innerParams.subject="[Auto-Reply] Mohit - Query Received";
							  innerParams.customTo=senderEmail;
							  innerParams.customToName=senderName;
							  innerParams.content="Hi "+senderName+ ", Thank you for contacting Me(Mohit). I will get back to you in a jiffy.";
							  
							  $.post( "mohitresume/sendMail",innerParams)
								  .done(function( data ) {
									  $("div#loader").hide();
									  console.log('Thank you for you interest! I will get back to you real quick!');
									  //toastr.success('Thank you for you interest! We will get back to you real quick!');
								  });
					  });
				
		}
}

// JavaScript contact form Document
$(document).ready(function() {
	$('form#contact-form').submit(function() {
	$('form#contact-form .error').remove();
	var hasError = false;
	$('.requiredField').each(function() {
	if(jQuery.trim($(this).val()) == '') {
    var labelText = $(this).prev('label').text();
    $(this).parent().append('<span class="error">You forgot to enter your '+labelText+'</span>');
    $(this).addClass('inputError');
    hasError = true;
    } else if($(this).hasClass('email')) {
    var emailReg = /^([\w-\.]+@([\w-]+\.)+[\w-]{2,4})?$/;
    if(!emailReg.test(jQuery.trim($(this).val()))) {
    var labelText = $(this).prev('label').text();
    $(this).parent().append('<span class="error">You entered an invalid '+labelText+'</span>');
    $(this).addClass('inputError');
    hasError = true;
    }
    }
    });
    if(!hasError) {
    $('form#contact-form input.submit').fadeOut('normal', function() {
    $(this).parent().append('');
    });

     $("#loader").show();
     var senderEmail = $("form#contact-form input[name='email']")[0].value;
	     if(ResumeUtil.validateEmail(senderEmail)){
	  	   ResumeUtil.contactMeQuery ();
	     }
	   return false;
    }
 
   });
});