

function ajaxMyScheduleList() {
	$.getJSON(serverAddr + "/myschedule/list.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
	    	 alert("서버에서 데이터를 가져오는데 실패했습니다.")
	    	 return
	    }
		
		var contents = ""
		var arr = result.data.list
		console.log(arr)
		console.log($("#menu1").text())
		var template = Handlebars.compile($('#divTemplateText').html())
		//var ddayTemplate = Handlebars.compile($('#spanTemplateText').html())
		console.log("template" + template)
		for (var i in arr) {
			if ($("#menu1").attr('data-value') == arr[i].no){
				arr[i].dday = computeDday(arr[i].start) 				
				console.log(arr[i].dday)
				if (arr[i].dday > 0) {
					contents += template(arr[i])
				}
			}
	    }
		
		$("#schedule").html(contents)

    })
}
 


function ajaxLoginUser() {
	   $.getJSON(serverAddr +"/auth/loginUser.json", function(obj) {
	      var result = obj.jsonResult
	      if (result.state != "success") {
	         $(".my-login").css("display", "none")
	         return
	      }    
	      //$("#userEmail").text(result.data.email);
	      $(".my-logout").css("display", "none")
	      $("#user").text(result.data.name)
	      $("#user").attr('data-value', result.data.no)
	      $("#userSc").attr('data-value', result.data.no)
	   })
	}
