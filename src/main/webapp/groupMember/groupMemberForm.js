function ajaxAddGroupMember(groupmember) {
	$.post(serverAddr +"/groupMember/add.json", groupmember, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			console.log(result.data)
			alert("등록 실패 입니다.")       
			return
		} 
	//	window.location.href ="memberApp.html"
	}, "json" )	
}


function ajaxDeleteGroupMember(no, password) {
	$.getJSON(serverAddr +"/groupMember/delete.json",{
		no: no,
		password : password
	}, function(result){
		if (result.state != "success") {
			alert("삭제 실패 입니다.")       
			return
		} 
		location.href = "memberApp.html"    		
	})		
}