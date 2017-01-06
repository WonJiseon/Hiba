$("#calendarModal").click(function() {
	$.getJSON("http://apis.skplanetx.com/weather/current/minutely?lon=126.9658000000&village=%EB%8F%84%EA%B3%A1&county=%EA%B0%95%EB%82%A8%EA%B5%AC&stnid=108&lat=37.5714000000&city=%EC%84%9C%EC%9A%B8&version=1&appKey=6e62a500-8f2f-36d6-ac6d-2fcc4b4a5a23", function(obj) {
		var result = obj.jsonResult
		console.log(result)
		if (result.message !="성공") {
			alert("서버에서 데이터를 가져오는데 실패했습니다.")
			return
		}
		
		var contents = ""
		var arr = result.data
		console.log(arr) 
		for (var i in arr) {
			if (arr[i] == "weather") 
				contents += arr[i]
		}
		
		$(".modal-weather").html(contents)
	});
});



function ajaxMyScheduleList() {
	$.getJSON(serverAddr + "/myschedule/list.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
	    	 alert("서버에서 데이터를 가져오는데 실패했습니다.")
	    	 return
	    }
		
		var contents = ""
		var arr = result.data
		console.log(arr)
		console.log($("#user").text())
		var template = Handlebars.compile($('#divTemplateText').html())
	    for (var i in arr) {
	    	if ($("#user").attr('data-value') == arr[i].no){
	    		contents += template(arr[i])
	    	}
	    }
	    
	    $("#schedule").html(contents)
	    
	    $(".titleLink").click(function(event) {
		    window.location.href = "MySchedule.html?no=" + $(this).attr("data-no")
	    })
    })
}



$.ajax({
	  url: url,
	  data: data,
	  success: success,
	  dataType: dataType
	});