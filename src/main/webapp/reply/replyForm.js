/*$("#addMember").click(function(e) { 	
	var replyContent = {
		groupNo : $("#group-reply-btn").attr('data-no'),
		content : "그룹에 참가하였습니다",
		name : $("#name").val()
	}
	ajaxAddReplyContent(replyContent)	
});
*/
function ajaxAddReply(reply) {
	$.post(serverAddr +"/reply/add.json", reply,  function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			console.log(result.data)
			alert("등록 실패 입니다.-그룹 참가 실패")       
			return
		}
	}, "json" )	
}
 

function ajaxAddReplyContent(replyContent) {
	$.post(serverAddr +"/replyContent/add.json", replyContent,  function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			console.log(result.data)
			alert("등록 실패 입니다.")       
			return
		}
	}, "json" )	
}
