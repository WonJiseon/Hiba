function ajaxGroupInviteListMessage() {
	
	$.getJSON(serverAddr +"/memberInvite/list.json", function(obj) {
		async: false
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("서버에서 데이터를 가져오는데 실패 했습니다.멤버리스트")
			return
		} 
		var contents = ""
		var inviteContents = ""
		var arr = result.data
	    var template2 = Handlebars.compile($('#inviteMessage').html())										
		for (var i in arr) {
			if (($("#userName").attr('data-value') == arr[i].memberNo) && (arr[i].status == false) && (arr[i].groupNo == arr[i].groupGroupNo)) {				 
				inviteContents += template2(arr[i])
				
			 }	
			if (($("#user").attr('data-value') == arr[i].memberNo) && (arr[i].status == false) && (arr[i].groupNo == arr[i].groupGroupNo)) {				 
				inviteContents += template2(arr[i])
				
			 }		
	    }
		$('.alert-box').html(inviteContents) 
		if (inviteContents.length == 0) {
			contents += "<div class='invite-box' style='display:none'>" +
			"<p>초대된 그룹이 없습니다.</p>" +  
			"</div>"
			$('.alert-box').html(contents); 
		}
		var inviteBox = $(".alert-box > .invite-box > ul").length;  
		if (inviteBox > 0) {
			$(".alert-icon").show()
		} 
	
	})
		
}

$("body").on('click','.addTrue', function(e){
	var memberInvite = {
			no : $(this).attr('data-value'),
			status : "1",
			groupNo : $(this).attr('data-no'),
			memberNo : $("#userName").attr('data-value')
	}
	console.log(memberInvite)
	ajaxMemberInvite(memberInvite)
	/*groupNo : $(this).attr('data-no'),*/
	var replyContent = {
		memberNo : $('.invite-box').attr('data-value'),
		groupNo : $(this).attr('data-chat'),
		content : "그룹에 참가하였습니다",
		name : $("#userName").text()
	}
	console.log(replyContent)
	ajaxAddReplyContent2(replyContent)
});

$("body").on('click','.addFail', function(e){	
	ajaxDeleteMemberInvite($(this).attr('data-value'))
});

function ajaxMemberInvite(memberInvite) {	
	$.post(serverAddr +"/memberInvite/update2.json", memberInvite, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("수락 실패입니다.")
			return
		}		
		window.location.reload();
	}, "json")
}


function ajaxDeleteMemberInvite(no) {
	$.getJSON(serverAddr +"/memberInvite/delete.json",{
		no: no
	}, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("거절 실패 입니다.")       
			return
		} 
		window.location.reload();  		
	})		
}

/*수락시 채팅방 메시지 출력*/
function ajaxAddReplyContent2(replyContent) {
	$.post(serverAddr +"/replyContent/add2.json", replyContent,  function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			console.log(result.data)
			alert("등록 실패 입니다.")       
			return
		}
	}, "json" )	
}
