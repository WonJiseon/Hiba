/**
 * 
 */$("#loginBtn").click(function(event) {
	location.href = "../index.html"
});

$("#logoutBtn").click(function(event) {
	location.href = "../index.html"
});

function computeDday(start) {
	var now = new Date();
	var then = new Date(start);
	gap = now.getTime() - then.getTime();
	gap = Math.floor(gap / (1000 * 60 * 60 * 24)) * -1;
	if (gap < 0) {
		return "이미 지난 스케줄입니다."
	} else {
		return gap
	}
}
/*
 * Object배열에서 key을 기반으로 중복을 제거하는 함수  
 */
function removeDuplicate(inArray) {
	var arr = {};

	for ( var i=0, len=inArray.length; i < len; i++ )
	    arr[inArray[i]['groupNo']] = inArray[i];

	inArray = new Array();
	
	for ( var key in arr )
	    inArray.push(arr[key]);	
	
	return inArray;
}

function ajaxMygroupList() {
	$.getJSON(serverAddr + "/myschedule/list.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("서버에서 데이터를 가져오는데 실패했습니다.")
			return
		}

		var arr = result.data.list
		var pastCount = 0;
		var upcomCount = 0;
		var arrGrp = [];
		var arrGrp2 = [];
		var dday = [];

		for (var i in arr) {
			dday[i] = computeDday(arr[i].start)
			if (dday[i] > 0) {
				upcomCount += 1;		
				//console.log(upcomArrGrpNo[i])
			} else if (dday[i] == "이미 지난 스케줄입니다.") {
				pastCount += 1;
			}
			arrGrp[i] = arr[i].groupNo
		}



		arrGrp = arrGrp.sort();
		arrGrp2 = arrGrp
		console.log(arrGrp)
		console.log(arrGrp2)

		var count = new Object();

		for (var i in arrGrp) {
			count[arrGrp[i]] = 0;
		}


		for (var i = 0; i < arrGrp2.length; i++) {
			while(1) {
				console.log(arrGrp2[i])
				var tmp = arrGrp.indexOf(arrGrp2[i]);
				console.log(tmp);
				var tmpVal = arrGrp[tmp];
				//console.log(tmpVal)
				if (tmp == -1) {
					break;
				}

				count[tmpVal] += 1;
				arrGrp.splice(tmp,1);
			}
			console.log(count)
		}


		/* 2016.11.01 내일부터 아래 코딩하기*/
		var contents = ""

			var template = Handlebars.compile($('#divTemplateText').html())
			for (var i in arr) {
				$("#group-Info").attr('data-value', arr[i].groupNo)
				arr[i].dday = computeDday(arr[i].start)

				if (arr[i].dday > 0 
						&& $("#user").attr('data-value') == arr[i].no
						&& $("#group-Info").attr('data-value') == arr[i].groupNo) {
					//&& $("#group-Info").attr('data-value') == count(arr[i].groupNo)) {
					arr[i].count = count[i]
					arr[i].upcomCount = upcomCount
				}
				//contents += template(arr[i])
			}
			var newArr = removeDuplicate(arr);
			console.log(newArr);
			for (var i in newArr) {
				contents += template(newArr[i])
			}
			
		$("#group-Info").html(contents)

		var contents2 = ""
			var template2 = Handlebars.compile($('#div2TemplateText').html())
			var number = 0;
		$("#schedule-Info").attr('data-value', arr[i].groupscNo)
		var divNum = 1;
		console.log(arr);
		for (var i in arr) {
			arr[i].dday = computeDday(arr[i].start)

			if (arr[i].dday > 0 
					&& $("#user").attr('data-value') == arr[i].no
					&& $("#group-Info").attr('data-value') == arr[i].groupNo) {
				//&& $("#group-Info").attr('data-value') == count(arr[i].groupNo)) {
				number++;
				console.log(divNum)
				$("#sc").attr('data-no', divNum)
				divNum++;
				contents2 += template2(arr[i])

			}
		}
		var btnLength = number
		contents2 = "<ul class='bxslider'>" + contents2 + "</ul>"
		$("#schedule-Info").html(contents2);
		
		$('.bxslider').bxSlider({
			nextSelector: '#slider-next',
			prevSelector: '#slider-prev',
			mode:'horizontal',
			speed:1000,
			slideMargin:100,
			captions: true,
		})
		
		function btnClickAction() {
			$("#btn-left").click(function() {
				btnLength--;
				btnAnimate(btnLength)
			})

			$("#btn-right").click(function() {
				btnLength++;
				btnAnimate(btnLength)
			})

			function btnAnimate(num) {
				$("#schedule-Info")
				
				if (num >= btnLength -1) {
					$("#btn-right").hide();
				} else {
					$("#btn-right").show();
				}
				if (num >= 1) {
					$("#btn-left").show();
				} else {
					$("#btn-left").hide();
				}
			}
		}
	})
}

function btnAction() {

}

/*
function ajaxMyScheduleList() {
	$.getJSON(serverAddr + "/myschedule/list.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("서버에서 데이터를 가져오는데 실패했습니다.")
			return
		}
		$("#group-Info").attr('data-value', result.data.groupNo)

		var contents = ""
			var contents2 = ""
				var arr = result.data.list
				console.log(arr)
				console.log($("#user").text())
				var template = Handlebars.compile($('#divTemplateText').html())
				//var ddayTemplate = Handlebars.compile($('#spanTemplateText').html())
				//console.log("template" + template)
				var template2 = Handlebars.compile($('#div2TemplateText').html())
				//console.log("template2" + template2)
				for (var i in arr) {
					if ($("#user").attr('data-value') == arr[i].no){

						if ($("#group-Info").attr('data-value') == arr[i].groupNo) {
							arr[i].dday = computeDday(arr[i].start) 				
							console.log(arr[i].dday)
							if (arr[i].dday > 0) {
								contents += template(arr[i].groupName)
								contents2 += template2(arr[i])
							}
						}
					}
				}
		$("#group-Info").html(contents)
		$("#schedule-Info").html(contents2)
	})
}
 */

function ajaxLoginUser() {
	$.getJSON(serverAddr +"/auth/loginUser.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			$(".my-login").css("display", "none")
			return
		}    
		//$("#userEmail").text(result.data.email);
		$(".my-logout").css("display", "none")
		$("#user").text(result.data.name)
		$("#user").attr('data-value', result.data.no)
		$("#userSc").attr('data-value', result.data.no)
	})
}