$('#email').keyup(function() {  	
	var email = $(this).val();

	$.ajax({
		url : 'http://t3.java85.com:8989/teamproject/index.html',
		dataType: "jsonp",
		jsonpCallback: 'callback',
        contentType: "application/json; charset=UTF-8",
		data: ({
			email : email
		}),
		success : function(response) {
			if (JSON.stringify(response).length > 3) {
				$('#checkedEmail').html("이미 가입된 이메일 입니다").css("color", "red");
			} else if (JSON.stringify(response).length < 3){				
				$('#checkedEmail').html("사용 가능한 이메일 입니다.").css("color", "green");
			} 	 
			if ($("#email").val() == 0){
				$('#checkedEmail').html("");
			}		
		},
		 error: function(jqXHR, textStatus, errorThrown) {
	            console.log('error : ' + textStatus + " " + errorThrown);
	     }

	}); 

});



