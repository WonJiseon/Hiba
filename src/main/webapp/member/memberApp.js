$("#loginBtn").click(function (e) {
	location.href = "../auth/authApp.html"
})

$("#logoutBtn").click(function (e) {
	location.href = "../index_h.html"
})

function ajaxMemberList() {
	$.getJSON(serverAddr +"/member/list.json", function(obj) {
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

		$("#board-Table tbody").html(contents)
		    $('.demo').each( function() {
        $(this).minicolors({
            control: $(this).attr('data-control') || 'hue',
            defaultValue: $(this).attr('data-defaultValue') || '',
            format: $(this).attr('data-format') || 'hex',
            keywords: $(this).attr('data-keywords') || '',
            inline: $(this).attr('data-inline') === 'true',
            letterCase: $(this).attr('data-letterCase') || 'lowercase',
            opacity: $(this).attr('data-opacity'),
            position: $(this).attr('data-position') || 'bottom left',
            swatches: $(this).attr('data-swatches') ? $(this).attr('data-swatches').split('|') : [],
            change: function(value, opacity) {
                if( !value ) return;
                if( opacity ) value += ', ' + opacity;
                if( typeof console === 'object' ) {
                    console.log(value);
                }
            },
            theme: 'bootstrap'
        });
    });
		// 태그 를 추가한후 제목에 대해 click 리스너를 추가한다.
		$(".titleLink").click(function (e) {
			window.location.href = "memberForm.html?no=" + $(this).attr("data-no")
		})           
	})
}


function ajaxLoginUser() {
	$.getJSON(serverAddr +"/auth/loginUser.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			$(".my-login").css("display", "none")
			return
		} 	
		//$("#userEmail").text(result.data.email);
		$(".my-logout").css("display", "none")
		$("#groupName").text(result.data.name)
		$("#userName").attr('data-value', result.data.no)		
		$("#userName").text(result.data.name)		
		$("#profilePhoto").attr("src", "../upload/" + result.data.filename)
		
	})
}
