var resultUser = []

$("#loginBtn").click(function(event) {
	location.href = "../auth/authApp.html"
});
 
$("#logoutBtn").click(function(event) {
	location.href = "../auth/authApp.html"
});




$("#addBtn").click(function(event) {
	   
		var formData = new FormData();
		formData.append("userNo" , $("#userName").attr('data-value'));
		formData.append("title" , $("#title").val());
		formData.append("contents" , $("#contents").val());
		formData.append("address" , $("#pac-input").val());
		formData.append("file1", $("input[name=file1]")[0].files[0]);
		console.log($("#userName").attr('data-value'))
		console.log($("#title").val())
		console.log($("#contents").val())
		console.log($("input[name=file1]")[0].files[0])
		console.log($("#pac-input").val())
		$.ajax({
			url: serverAddr + '/community/add.json',
			processData: false,
			contentType: false,
			data: formData,
			type: 'POST',
			success: function(result){
			},
			error: function(jqXHR, textStatus, errorThrown) {
		          console.log('error : ' + textStatus + " " + errorThrown);
		     }
		});
	/*	window.location.reload();*/
		location.href = "communityApp.html"
});


/*$("#addBtn").click(function(event) {
	var community = {
			userNo: $("#userNo2").attr('data-value'),
			title: $("#title").val(),
			contents: $("#contents").val(),
			address: $("#pac-input").val()
			 filename:$("#file1", $("input[name=file1]")[0].files[0]);
	}
	ajaxAddCommunity(community)	
	ajaxAddFilePath()
});
*/
$("#updateBtn").click(function(event) {
  var community = {
    title: $("#title").val(),
    contents: $("#contents").val(),
    address: $("#pac-input").val(),
    no: $("#no").val()
  }
  ajaxUpdateCommunity(community)
});


$("#deleteBtn").click(function(event) {
  ajaxDeleteCommunity($("#no").val())
});



/*댓글 버튼*/
$("#addCommentBtn").click(function(event) {
	if (resultUser.data == null) {
		alert("로그인하세요")
		/*window.location.reload()*/
		location.href = "../index_h.html"
		return
	  } 
	var communityComment = {
			commentUserNo: $("#userNo3").attr('data-value'),
			communityBoardNo:$("#no").val(),
			comment: $("#comment").val(),
			commentRegisterDate: $("#commentRegisterDate").val()
	}
	
	ajaxAddCommunityComment(communityComment)
	$("#comment").val("")
});

$("#updateCommentBtn").click(function(event) {
  var communityComment = {
		    commentUserNo: $("#userNo3").attr('data-value'),
	        comment: $("#comment").val(),
			commentRegisterDate: $("#commentRegisterDate").val(),
            commentNo: $("#commentNo").val()
  }
  ajaxUpdateCommunityComment(communityComment)
});

/*$("#deleteCommentBtn").click(function(event) {
  ajaxDeleteCommunityComment($("#commentNo").val())
  ajaxDeleteCommunity($("#no").val())
});
*/

$("#deleteCommentBtn").click(function(event) {
   var no = {commentNo: $("#commentNo").attr('data-no'),}
   ajaxDeleteCommunityComment($("#no").val())
  
});







$("#LikeBtn").click(function(event) {
	
  var boardLike = {
	    boardLike: $("#boardLike").val()
	  }
 });





/*function ajaxAddCommunity(community) {
	$.post(serverAddr + "/community/add.json", community, function(obj) {
		var result = obj.jsonResult
		console.log(result)
		if (result.state != "success") {
	    	 alert("등록 실패입니다.")
	    	 return
	    }
	    window.location.href = "communityApp.html"	 	
	}, "json")
}

*/





function ajaxLoadCommunity(no) {
	$('.side-header').css({"display": "none"})
	$('.selectbar-left').css({"display": "none"})
	$('.header-top').css({"display": "none"})
	$('.make-text').css({"display": "none"})
	$('.wrap').removeClass('display-none');
 	
	$.getJSON(serverAddr + "/community/detail.json?no=" + no, function(obj) {
		var result = obj.jsonResult
		
		if (result.state != "success") {
			alert("조회 실패입니다 디테일.")
			return
		}		
		$("#no").val(result.data.no);
		$("#userNicName").val(result.data.userNicName);
		$("#title").val(result.data.title);
		$("#contents").val(result.data.contents);
		$("#pac-input").val(result.data.address);
		$("#registerDate").text(result.data.registerDate2);
		$("#viewCount").text(result.data.viewCount);
		$("#fileNo").text(result.data.fileNo);
		$("#filename > img").attr("src", "../upload/" + result.data.filename);
		$("#boardLike").text(result.data.boardLike);
		console.log(result)
		if (result.data.userNo != $("#userName").attr('data-value')) {
			$('#title').attr('disabled','disabled');
			$('#contents').attr('disabled','disabled');
			$(".fileupload-box").remove()	
		}
		
		
	    $('.wrap').addClass('display-none');   
	    $('.side-header').css({"display": "block"})
	    $('.header-top').css({"display": "block"})
	    $('.make-text').css({"display": "block"})
	    $('.selectbar-left').css({"display": "block"})
	})
}

function ajaxUpdateCommunity(community) {
	$.post(serverAddr + "/community/update.json", community, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("변경 실패입니다.")
			return
		} 
		window.location.href = "communityApp.html"
	}, "json")
}

function ajaxDeleteCommunity(no) {
	$.getJSON(serverAddr + "/community/delete.json", {
		no: no,
	}, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("삭제 실패입니다.")
			return
		}
		location.href = "communityApp.html"
	})
}






//_______________________________________________[댓글]기능_____________________________________________________//





function ajaxCommentCommunityList(no) {
	$.getJSON(serverAddr + "/communityComment/list.json", function(obj) {
		var result = obj.jsonResult
		console.log(result)
		if (result.state != "success") {
	    	 alert("댓글: 서버에서 데이터를 가져오는데 실패했습니다.")
	    	 return
	    }
		

		
		var template = Handlebars.compile($('#commentTemplateText').html())
		var contents = ""
	    var arr = result.data
	    var commentCount2 = result.length
	    
        for (var i in arr) {
          if (no == arr[i].communityBoardNo) {
        	contents += template(arr[i])
        	}		
          }
	    
	    $("#communityCommentTable").html(contents)
    })
} 







function ajaxAddCommunityComment(communityComment) {
	$.post(serverAddr + "/communityComment/add.json", communityComment, function(obj) {
		var result = obj.jsonResult
		var no = location.search.split("=")[1];
		console.log(result)
		if (result.state != "success") {
	    	 alert("댓글: 등록 실패입니다.")
	  	   } 
		 ajaxCommentCommunityList(no)
	}, "json")
}



function ajaxLoadCommunityComment(no) {
	$.getJSON(serverAddr + "/communityComment/detail.json?no=" + no, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("댓글: 조회 실패입니다.")
			return
    	/*}
		
		$("#commentNo").val(result.data.commentNo);
		$("#commentUserNo").val(result.data.commentUserNo);
		$("#communityBoardNo").val(result.data.communityBoardNo);
		$("#commentUserNicName").val(result.data.commentUserNicName);
		$("#comment").val(result.data.comment);
		$("#commentRegisterDate").text(result.data.commentRegisterDate2);
		*/
	    } else {
	         $("#communityCommentTable div[data-no=" + no + "]").find("div:eq(4)").html(
	         "<textarea cols='75' rows='2' class='update-contents reAddLimit' id='reUpdateLimit'></textarea>");
	         
	         $("#communityCommentTable div[data-no=" + no + "]").find("div:eq(7)").html(
	         "<button type='button' class='bit-save-btn' data-no=" + no + ">저장</button>" +
	         "<button type='button' class='bit-cancel-btn' data-no=" + no + ">취소</button>");
	         
	         $("#communityCommentTable div[data-no=" + no + "]").find(".update-contents").val(result.data.comment);
	         
	   }
	})
}


function ajaxUpdateCommunityComment(communityComment) {
	$.post(serverAddr + "/communityComment/update.json", communityComment, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("댓글: 변경 실패입니다.")
			return
		} 
		window.location.href = "communityApp.html"
	}, "json")
}

function ajaxDeleteCommunityComment() {
	$.getJSON(serverAddr + "/communityComment/delete.json", {
		no: no,
	}, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("댓글: 삭제 실패입니다.")
			return
		}
		location.href = "communityApp.html"
	})
}









function google_map(mapid, addr) {
	var geocoder =  new google.maps.Geocoder();
	geocoder.geocode( {'address': addr }, function(results, status) {
		if (status == google.maps.GeocoderStatus.OK) {
			var map = new google.maps.Map(document.getElementById(mapid), {
				zoom: 16,
				center: results[0].geometry.location,
				mapTypeId: google.maps.MapTypeId.ROADMAP
			});

			var markerTitle    = "";  // 현재 위치 마커에 마우스를 올렸을때 나타나는 이름
			var markerMaxWidth = 260;  // 마커를 클릭했을때 나타나는 말풍선의 최대 크기
			var contentString = '<table><tr><td width=90><img src="" width="80" style="border-radius:5px;"></td><td><div>' + 
			'<span style="padding-bottom:10px"><b>'+markerTitle+'</b></span><br />'+ 
			'<div class="map_Content">'+ 
			//'TEL: <a href=tel:031-398-0902>031-398-0902</a><br />'+ 
			//'진료시간: 00:00~24:00 연중무휴<br />' + 
			'주소: '+ event.placeName + 
			'</div>'+ 
			'</div></td></tr></table>'; 

			var marker = new google.maps.Marker({ 
				position: map.getCenter(), 
				map: map, 
				draggable:false,
				animation: google.maps.Animation.DROP,  
				title: markerTitle 
			}); 

			var infowindow = new google.maps.InfoWindow({ 
				content: contentString,
				maxWidth: markerMaxWidth
			}); 
			infowindow.open(map, marker); 

			google.maps.event.addListener(marker, 'click', function() { 
				infowindow.open(map, marker); 
			}); 
		}
	});
}



