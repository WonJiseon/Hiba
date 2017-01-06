/*
$('body').on('click',".albumBtn", function() {
	window.location.href = "../album/album02.html?no=" + $("#userName").attr("data-value") 
});

*/
$('body').on('click','.addImage', function(e) {
	var no = $(this).attr('data-no');
	$('#add-images-pop').modal();	
	$('body').on('click', "#addImageBtn", function(e){
		console.log(no)
		var formData = new FormData();		
		formData.append("no", no);
		formData.append("file2", $("input[name=file2]")[0].files[0]);		
		$.ajax({
			url: serverAddr + '/upload/add.json',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			success: function(result){
			}
		});
		window.location.reload();
	})

})



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
$('body').on('click',"#addBtn", function(){	
		var formData = new FormData();	
		formData.append("groupName", $("input[name=groupName]").val());
		formData.append("groupText", $("input[name=groupText]").val());
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
		 ajaxMemberGroupInviteList()
		 window.location.reload();
})


$('body').on('click', '.sw-btn', function(){
	$(this).parents().children('.list-article').toggle()
	if ($(this).parents().hasClass('gallery_item_list')) {

		console.log("11")
		$(this).parents().children('.list-article').children('.abc').css({"padding" : "5px 0 0 100px"})
		$(this).parents().children('.list-article').children('.list-more-box').css({"left" : "100px"})			
	} else {
		$(this).parents().children('.list-article').children('.abc').css({"padding" : "40px 0 0 20px"})
		$(this).parents().children('.list-article').children('.list-more-box').css({"left" : "10px"})
	}

})
