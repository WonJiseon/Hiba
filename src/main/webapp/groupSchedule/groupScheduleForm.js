/*$("#schedule-accept-btn").click(function(e) {  
	var event = {
			stauts : $("#schedule-accept-btn").val("1"),			
	}
	ajaxUpdateSchedule(event)
});

$("#schedule-reject-btn").click(function(e) {   
	var event = {
			stauts : $("#schedule-accept-btn").val("0"),			
	}
	ajaxUpdateSchedule(event)
});



function ajaxUpdateSchedule(event) {	
	$.post(serverAddr +"/schedule/update.json", event, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("변경 실패입니다.")
			return
		}
		window.location.href = "memberApp.html"
	}, "json")
}

*/