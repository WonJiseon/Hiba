$('body').on('click', "#addBtn", function(e) { 
	
	var formData = new FormData();	
	formData.append("name", $("#name").val());
	formData.append("nicknm", $("#nicknm").val());
	formData.append("email", $("#email").val());
	formData.append("password", $("#password").val())
	formData.append("file", $("input[name=filename]")[0].files[0]);

	console.log("name= " + formData.get("name"));
	console.log("nicknm= " + formData.get("nicknm")); 
	console.log("email= " + formData.get("email"));
	console.log("password= " + formData.get("password"));
	console.log("file= " + formData.get("file"));
	
	ajaxJoinMember(formData)
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
	$.post(serverAddr +"/member/add.json", member, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			console.log(result.data)
			alert("등록 실패 입니다.")       
			return
		} 
		window.location.reload()
	}, "json" )	
}

function ajaxLoadMember(no) {
	
	$.getJSON(serverAddr +"/member/detail.json?no=" + no, function(obj) {
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
	$.post(serverAddr +"/member/update.json", member, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("변경 실패입니다.")
			return
		}
		window.location.href = "memberApp.html"
	}, "json")
}

function ajaxDeleteMember(no, password) {
	$.getJSON(serverAddr +"/member/delete.json",{
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



function ajaxJoinMember(formData) {
	
	console.log("name= " + formData.get("name"));
	console.log("nicknm= " + formData.get("nicknm"));
	console.log("email= " + formData.get("email"));
	console.log("password= " + formData.get("password"));
	console.log("file= " + formData.get("file"));
	
	console.log("formData Start");
	
	
	$.ajax({
		url: serverAddr + '/member/add.json',
		data: formData,
		processData: false,
		contentType: false,
		type: 'POST',
		success: function(result){
			alert("18");
		}
	});
	window.location.reload(true);
}





