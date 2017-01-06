function ajaxMemberList() {
	$.getJSON(serverAddr +"/place/list.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("서버에서 데이터를 가져오는데 실패 했습니다.")
			return
		} 

		var contents = ""
			var arr = result.data
			for (var i in arr) {
				contents += "<tr>" +
				"<td>" + arr[i].no + "</td>" +
				"<td><a class='titleLink' href='#' data-no='" + arr[i].no + "'>" + arr[i].name + "</a></td>" +        
				"<td>" + arr[i].nicknm +"</td>" +
				"<td>" + arr[i].email + "</td>" +
				"</tr>";
			}

		$("#board-Table tbody").html(contents);
		// 태그 를 추가한후 제목에 대해 click 리스너를 추가한다.
		$(".titleLink").click(function (e) {
			window.location.href = "memberForm.html?no=" + $(this).attr("data-no")
		})           
	})
}