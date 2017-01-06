var resultUser = []


$("#loginBtn").click(function (e) {
	location.href = "../auth/authApp.html"
});

$("#logoutBtn").click(function (e) {
	location.href = "../auth/authApp.html"
});


$("#prevBtn").click(function(event) {
	pageNo--;
	ajaxBoardList();
});

$("#nextBtn").click(function(event) {
	pageNo++;
	ajaxBoardList();
});

//글로벌 변수 = window 프로퍼티 
var pageNo = 1, /* window.pageNo */
pageLength = 5; /* window.pageLength */

$("#writeBtn").click(function (e) {
	console.log(resultUser)
	if (resultUser.data == null) {
		alert("로그인하세요")
		location.href = "../index_h.html"
			return
	} 
	location.href = "communityForm.html"
});		





function ajaxCommunityList() {
	$('.side-header').css({"display": "none"})
	$('.selectbar-left').css({"display": "none"})
	$('.header-top').css({"display": "none"})
	$('.make-text').css({"display": "none"})
	$('.wrap').removeClass('display-none');

	$.getJSON(serverAddr + "/community/list.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("커뮤니티: 서버에서 데이터를 가져오는데 실패했습니다.")
			return
		}

		var template = Handlebars.compile($('#divTemplateText').html())
		$("#communityDiv div").html(template(result))

		$(".titleLink").click(function(event) {
			window.location.href = "communityForm.html?no=" + $(this).attr("data-no")
		})

		// 현재 페이지 번호를 span 태그에 출력한다.http://localhost:8080/teamproject/makegroup/makegroup.html
		pageNo = result.data.pageNo;
		totalPage = result.data.totalPage;
		$('#pageNo').text(pageNo);

		// 페이지 번호가 1이면 [이전] 버튼을 비활성화시킨다.
		if (pageNo <= 1) {
			$('#prevBtn').attr('disabled', true);
		} else {
			$('#prevBtn').removeAttr('disabled');
		} 

		// 페이지 번호가 마지막 페이지라면 [다음] 버튼을 비활성화시킨다.
		if (pageNo >= totalPage) {
			$('#nextBtn').attr('disabled', true);
		} else {
			$('#nextBtn').removeAttr('disabled');
		}

		$('.wrap').addClass('display-none');   
		$('.side-header').css({"display": "block"})
		$('.header-top').css({"display": "block"})
		$('.make-text').css({"display": "block"})
		$('.selectbar-left').css({"display": "block"})
	})
}

function ajaxCommentCount() {

	$.getJSON(serverAddr + "/communityComment/list.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("커뮤니티: 서버에서 데이터를 가져오는데 실패했습니다.")
			return
		}
		var contents = 0
		var aaa = []
		var arr = result.data
		$('.titleLink').each(function(i, obj) {
			var boardContentNo = $(this).attr("data-no")			
			var boardContentNo2 = $(this)
			for (var i in arr) {	
				if (arr[i].communityBoardNo) {
					if(boardContentNo == arr[i].communityBoardNo) {
						/*console.log("태그 번호 : " + boardContentNo)
						console.log("json번호  : " + arr[i].communityBoardNo)*/
						contents++;
						boardContentNo2.nextAll(".bbbb").children(".icon").children("#coCount").children("#ccount").html(contents);
					}	
				}			
			}

			contents = 0;
		});
		



	})
}


function ajaxLoginUser() {
	$.getJSON(serverAddr + "/auth/loginUser.json", function(obj) { 
		resultUser = obj.jsonResult
		if (resultUser.state != "success") { // 로그아웃 상태일 경우 로그인 상태와 관련된 태그를 감춘다.
			$('.my-login').css("display", "none")
			return
		}

		$('.my-logout').css("display", "none")

		$("#userName").text(resultUser.data.name);
		$("#userName").attr('data-value', resultUser.data.no);
		$("#userNo2").attr('data-value', resultUser.data.no);
		$("#userNo3").attr('data-value', resultUser.data.no);
		$("#profilePhoto").attr("src", "../upload/" + resultUser.data.filename)
		$("#profilePhoto2").attr("src", "../upload/" + resultUser.data.filename)

		if (resultUser.data.filename == null) {
			$("#profilePhoto").attr("src", "../upload/user-3.png")
			$("#profilePhoto2").attr("src", "../upload/user-3.png")
			return
		} 
	})
}





window.initMap = function() {
	var map = new google.maps.Map(document.getElementById('map'), {
		center: {lat: -33.8688, lng: 151.2195},
		zoom: 16,
		mapTypeId: google.maps.MapTypeId.ROADMAP
	});

	// Create the search box and link it to the UI element.
	var input = document.getElementById('pac-input');
	var searchBox = new google.maps.places.SearchBox(input);
	map.controls[google.maps.ControlPosition.TOP_LEFT].push(input);

	// Bias the SearchBox results towards current map's viewport.
	map.addListener('bounds_changed', function() {
		searchBox.setBounds(map.getBounds());
	});

	var markers = [];
	// [START region_getplaces]
	// Listen for the event fired when the user selects a prediction and retrieve
	// more details for that place.
	searchBox.addListener('places_changed', function() {
		var places = searchBox.getPlaces();

		if (places.length == 0) {
			return;
		}

		// Clear out the old markers.
		markers.forEach(function(marker) {
			marker.setMap(null);
		});
		markers = [];

		// For each place, get the icon, name and location.
		var bounds = new google.maps.LatLngBounds();
		places.forEach(function(place) {
			var icon = {
					url: place.icon,
					size: new google.maps.Size(71, 71),
					origin: new google.maps.Point(0, 0),
					anchor: new google.maps.Point(17, 34),
					scaledSize: new google.maps.Size(25, 25)
			};

			// Create a marker for each place.
			markers.push(new google.maps.Marker({
				map: map,
				icon: icon,
				title: place.name,
				position: place.geometry.location
			}));

			if (place.geometry.viewport) {
				// Only geocodes have viewport.
				bounds.union(place.geometry.viewport);
			} else {
				bounds.extend(place.geometry.location);
			}
		});
		map.fitBounds(bounds);
	});
	// [END region_getplaces]
}







function createdatawithcheck(data){

	var sec = 60;
	var mins = 60;
	var hours = 24;
	var days = 7;
	var month =12;

	//시간차 비교
	//현재시간 - 등록된시간

	//현재시간
	var tday = new Date();
	var cday = new Date(data);
	var difftime = Math.floor((tday - cday)/1000);
	var msg="";
	if(data == "0000-00-00 00:00:00"){

		msg = 0;

	}else

		if(difftime < sec){
			msg="방금";
		}else if((difftime /=sec) < mins){

			msg=Math.floor(difftime) + "분";
		}else if((difftime /=mins) < hours){

			msg=Math.floor(difftime) + "시간";
		}else if((difftime /=hours) < days){

			msg=Math.floor(difftime) + "일";
		}else if((difftime /=days) < month){

			msg=Math.floor(difftime) + "달";
		}else {

			msg=Math.floor(difftime) + "년";
		}
	return msg;
} 






