$(document).ready(function() {
	$('.userId').click(function() {
		fillUserAccountPopUp($(this).offset().left,
		$(this).offset().top,
		$(this).text(),
		$(this).attr("href"));
		return false;
	});	
	    
	$('.fillUserAccountPopUpCloseRef').click(function() {
		$('#fillUserAccountPopUp').css("display","none");
		return false;
	});
	
	$('#fillUserAccount').submit(function(event) {
		fillAccountAJAX();
		event.preventDefault();
	});
});

function fillAccountAJAX() {
	var account = $('#userNameFill').data('accountId');
	var amount = $('#fillAmount').val();
	a = $.ajax({
		url     : $("#fillUserAccount").attr("action"),
		dataType: 'json',
		data    : "account=" + account+ "&amount=" + amount,  
		success : function(response){
			//alert (response);
			$('.hideAfterFill').css("display","none");
			$('#fillResult').text(response.text);
			$("#balance-"+account).text(response.balance);
			setTimeout('$(\'#fillUserAccountPopUp\').css(\"display\",\"none\")',3000);
		},
		error   : function (response,status,e){
			alert('error: '+e);
		}
	});
};



//function fillAccountAJAX() {
//	if (!validateForm()) return false;
//	var account = $('#userNameFill').data('userName');
//	var amount = $('#fillAmount').val();
//	$.ajax({
//		url     : $("#fillUserAccount").attr("action"),
//		data    : "{\"account\" :" + account + ", \"amount\" : \"10\""+", \"balance\":\"\", \"text\":\"\"}",
//		type       : "POST",
//		dataType: 'json',
//		contentType: 'application/json',
//	    mimeType: 'application/json',		
//		success : function(response){
//			$('#fillUserAccount').css("display","none");
//			$('#fillResult').text(response.text);
//			$("balance-"+account).text(response.amount);
//			setTimeout('$(\'#fillUserAccountPopUp\').css(\"display\",\"none\")',2);
//		},
//		error   : function (response,status,e){
//			alert('error: '+e);
//		}
//	});
//};

function validateForm() {
	var noErrors = true;
	$('.formError').remove();
	var fillAmount = $('#fillAmount').val();
	if (!($.isNumeric(fillAmount)&&(fillAmount>0))){
		$("#fillAmount").after("<div class = \'formError\'>Проверьте значение!</div>");
		noErrors = false;
	}
	return noErrors;
};

function fillUserAccountPopUp(a,b,text,account) {
    $('#fillUserAccountPopUp').css("display","block");
    $('#fillUserAccountPopUp').css("left",(a+100)+"px");
    $('#fillUserAccountPopUp').css("top",(b-20)+"px");
	$('#fillResult').text("");
	$('#userNameFill').text(text);
	$('#userNameFill').data('accountId',account);
	$('#fillAmount').val("");
	$('#fillAmount').focus();
	$('.hideAfterFill').css("display","block");
};