$("#addBtn").click(function(e) { 
	var makegroup = {
			groupName : $("#groupName").val(),
			name : $("#userName").text()
	}
	/*ajaxAddGroup(makegroup)*/
	ajaxAddGroupPhoto()
	
	 
	
	
});


$("#color-btn").click(function(e) {  
	var makegroup = {
			color : $("#name").val(),
			nicknm : $("#nicknm").val(),
			email : $("#email").val(),
			password : $("#password").val(),
			no : $("#no").val()
	}
	ajaxUpdateGroup(makegroup)
});

$("#member").on('click', '#deleteBtn', function(e) {
	var delNo = $(this).attr('data-no')
	swal({
		title: 'Are you sure?',
		text: "일정을 삭제 하시겠습니까?",
		type: 'warning',
		showCancelButton: true,
		confirmButtonColor: '#3085d6',
		cancelButtonColor: '#d33',
		confirmButtonText: 'Yes, delete it!'
	}).then(function() {
		swal(
				'Deleted!',
				'Your file has been deleted.',
				'success'
		);
		ajaxDeleteGroup(delNo)
	})	

});



/*function ajaxAddGroup(makegroup) {
	$.post(serverAddr +"/group/add.json", makegroup, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			console.log(result.data)
			alert("등록 실패 입니다.makeGroup")       
			return
		} 
		swal(
				'Good job!',
				'You clicked the button!',
				'success'
		)



		window.location.reload();
	}, "json" )	
}*/


function ajaxLoadGroup(no) {
	$.getJSON(serverAddr +"/group/detail.json?no=" + no, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("조회 실패 입니다.")       
			return
		} 

	})
}

function ajaxUpdateGroup(makegroup) {	
	$.post(serverAddr +"/group/update.json", makegroup, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("변경 실패입니다.")
			return
		}
		window.location.href = "makegroup.html"
	}, "json")
}

function ajaxDeleteGroup(no) {
	$.getJSON(serverAddr +"/group/delete.json", {
		no: no
	}, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("삭제 실패 입니다.")       
			return
		} 
		ajaxGroupList()
	})		
}
/*$('body').on('click',"#addBtn", function(){
	
})*/
function ajaxAddGroupPhoto() {
	var formData = new FormData();
	formData.append("memberNumber" , $("#userName").attr('data-value'));
	formData.append("name" , $("#userName").text());
	formData.append("groupName", $("input[name=groupName]").val());
	formData.append("file1", $("input[name=file1]")[0].files[0]);
	$.ajax({
		url: serverAddr + '/group/add.json',
		processData: false,
		contentType: false,
		data: formData,
		type: 'POST',
		success: function(result){
		}
	});

};




