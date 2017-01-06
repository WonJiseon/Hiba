function ajaxGroupBtn() {
	$.getJSON(serverAddr + "/reply/list.json", function(obj) {
		var result = obj.jsonResult

		if (result.state != "success") {
			alert("서버에서 데이터를 가져오는데 실패 했습니다.-re")
			return
		} 

		var contents = ""
		var arr = result.data			
		var template = Handlebars.compile($('#groupReplyMore').html())	
		var getGroupNo = $(location).attr('search')	
		var groupNo = getGroupNo.split("=")[1]
		for (var i in arr) {			
			if (groupNo == arr[i].groupNo) {					
				contents += template(arr[i]) 
			}
		}
		
		$(".group-reply-more").html(contents)
	})
}

/*
function ajaxLoginUser() {
	$.getJSON(serverAddr +"/auth/loginUser.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			$(".my-login").css("display", "none")
			return
		} 	
		$("#userName").text(result.data.name)
		$("#nicknm").text(result.data.nicknm)
	})
}
*/