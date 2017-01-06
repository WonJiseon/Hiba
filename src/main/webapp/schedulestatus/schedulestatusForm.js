var groupNo = location.search.split("=")[1];
$('body').on('click', "#schedule-accept-btn", function(e) {
	var eventStatus = {
			no : $(this).attr('data-value'),
			memberNo : $("#userName").attr('data-value'),
			groupNo : groupNo,
			status : '1'	
	}
	console.log(eventStatus)
	ajaxAddScheduleStatus(eventStatus)
});

$('body').on('click', "#schedule-reject-btn", function(e) {   
	var eventStatus = {
			no : $(this).attr('data-value'),
			memberNo : $("#userName").attr('data-value'),
			groupNo : groupNo,
			status : '0'	
	}
	console.log(eventStatus)
	ajaxAddScheduleStatus(eventStatus)
});


function ajaxAddScheduleStatus(eventStatus) {	
	$.post(serverAddr +"/schedulestatus/add.json", eventStatus, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("변경 실패입니다.11")
			return
		}
		window.location.reload();
	}, "json")
}

