var getGroupNo = $(location).attr('search')	
var memberNo = getGroupNo.split("=")[1]
var no = parseInt(memberNo)

var groupNo = getGroupNo.split("=")[2]
var gno = parseInt(groupNo)

var groupScNo = getGroupNo.split("=")[3]
var gsno = parseInt(groupScNo)




function ajaxGroupName() { // gno , gsno 불러오는 function
	$('.wrapper').css({"display" : "none"})
	$('.wrap2').removeClass('display-none');
	$.getJSON(serverAddr +"/album/list.json",  function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("서버에서 데이터를 가져오는데 실패 했습니다.1111")
			return
		} 
		var contents = "";
		var contents2 = "";	
		var photoContents = "";	
		var arr = result.data.list
		var template = Handlebars.compile($('#groupNameTemplateText').html())			
		var template2 = Handlebars.compile($('#groupNameTemplateText2').html())
		var template3 = Handlebars.compile($('#photoTemplate').html())
	
		
		for (var i in arr) {
	
			if (no == arr[i].memberNo && arr[i].groupNo == gno) {
				var sch = arr[i].scheduleList
				$("#groupinfo").attr('data-value', arr[i].groupNo)								
				$("#tasks-title").html(arr[i].groupName)
				for (var j = 0; j < arr[i].scheduleList.length; j++) {
					if (arr[i].groupNo == gno) {						
						$("#scheduleList").attr('data-gsno', arr[i].scheduleList[j].groupscNo)							
						contents += template(arr[i].scheduleList[j]) 						
						if (gsno == arr[i].scheduleList[j].groupscNo) {							
							$(".sc-title").html(arr[i].scheduleList[j].title + " 일정 입니다.")
							
						}
					}				
				}
			}		
			if (gno == arr[i].groupNo) {
				/*contents2 = template2(arr[i])*/
			}

		}
		
		$('.tasks-list').html(contents)	
		$('#groupNa').html(contents2)
		$('.tttt').each(function(i, obj) {
			var vv = $(this).attr("data-value")
			var vvv = $(this)
			$('.asdf').each(function(i, obj) {
				var asd = $(this).attr("data-vo")
				var asdd =$(this)
				if (vv == asd) {
					vvv.append(asdd)
				}
			})

		});
		
		$('.asdf > #groupscList').each(function() {	
			var listGsno = $(this).attr("data-no")			
			if (listGsno == gsno) {
				$(this).prop("checked", true);
				
			}
		})
		
		$('body').on('click', '#groupinfo', function (e) {
			window.location.href = "../album/album02.html?no=" + no + "&gno=" + $(this).attr("data-value")			
		})	
		$('body').on('click', '#scheduleList', function (e) {
			var ggno = $(this).parent().parent().parent().attr('data-vo')
			window.location.href = "../album/album02.html?no=" + no + "&gno=" + gno + "&gsno=" + $(this).attr("data-gsno")	
		})	

		$('.wrapper').css({"display" : "block"})
		$('.wrap2').addClass('display-none');
	});
}

function ajaxGroupPhoto() {
	console.log("!0")
	$('.wrapper').css({"display" : "none"})
	console.log("!1")
	$('.wrap').removeClass('display-none');
	console.log("!2")
	$.getJSON(serverAddr +"/album/listAl.json", function(obj) {
		var result = obj.jsonResult
		console.log(result)
		if (result.state != "success") {
			alert("서버에서 데이터를 가져오는데 실패 했습니다.1111")
			return
		} 
		var arr = result.data
		var contents =""
		var template = Handlebars.compile($('#photoTemplate').html())
		for (var i in arr) {
			if ((gno == arr[i].groupNo) && (gsno == arr[i].groupScheduleNo)) {				
				contents += template(arr[i]) 	
			}
		}
		$('.aaaaa').html(contents)
		
		console.log("!5")
		$('.wrapper').css({"display" : "block"})
		console.log("!6")
		$('.wrap').addClass('display-none');
	})
}


$(".al-btn").click(function(e) { 		
	var formData = new FormData();
	formData.append("memberNo", $("#userName").attr('data-value'));
	console.log($("#userName").attr('data-value'))
	formData.append("groupNo", gno);
	console.log($("#groupinfo2").attr('data-value'))
	formData.append("groupScheduleNo", gsno);	
	console.log(gsno)
	console.log($("#multiFile")[0].files)
	$($("#multiFile")[0].files).each(function(index, file) {
		console.log(file)
		formData.append("file1", file)
	})

	$.ajax({
		url: serverAddr + '/album/add.json',
		processData: false,
		contentType: false,
		data: formData,
		type: 'POST',
		success: function(result){

		}
	});
	window.location.reload();
});



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
		$("#profilePhoto").attr('src', "../upload/" + result.data.filename)		  
	
	}) 
}


