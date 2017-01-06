$("#addBtn").click(function(e) { 
	var member = {
			name : $("#name").val(),
			nicknm : $("#nicknm").val(),
			email : $("#email").val(),
			password : $("#password").val()
	}
	ajaxAddMember(member)
	
});


$("#updateBtn").click(function(e) {  
	var member = {
			name : $("#name").val(),
			nicknm : $("#nicknm").val(),
			email : $("#email").val(),
			password : $("#password").val(),
			no : $("#no").val()
	}
	ajaxUpdateMember(member)
});

$("#deleteBtn").click(function(e) {   
	ajaxDeleteMember($("#no").val(), $("#password").val())
});



function ajaxAddMember(member) {
	$.post(serverAddr +"/place/add.json", member, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			console.log(result.data)
			alert("등록 실패 입니다.")       
			return
		} 
		window.location.href ="memberApp.html"
	}, "json" )	
}

function ajaxLoadMember(no) {
	$.getJSON(serverAddr +"/place/detail.json?no=" + no, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("조회 실패 입니다.")       
			return
		} 
		// 서버에서 받은 데이터로 폼을 채운다
		$("#no").val(result.data.no);
		$("#name").val(result.data.name);
		$("#nicknm").val(result.data.nicknm);
		$("#email").val(result.data.email);

	})
}

function ajaxUpdateMember(member) {	
	$.post(serverAddr +"/place/update.json", member, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("변경 실패입니다.")
			return
		}
		window.location.href = "memberApp.html"
	}, "json")
}

function ajaxDeleteMember(no, password) {
	$.getJSON(serverAddr +"/place/delete.json",{
		no: no,
		password : password
	}, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("삭제 실패 입니다.")       
			return
		} 
		location.href = "memberApp.html"    		
	})		
}