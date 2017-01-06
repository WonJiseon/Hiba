function ajaxMoreScheduleList(moreNo, thisTag) {
	$.getJSON(serverAddr +"/schedulestatus/list.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("서버에서 데이터를 가져오는데 실패 했습니다222.")
			return
		} 

		var contents = ""
		var contents2 = ""
		var arr = result.data
		var template = Handlebars.compile($('#moreScheduleAccept').html())
		var getGroupNo = $(location).attr('search')	
		var groupNo = getGroupNo.split("=")[1]
		for (var i in arr) {
			if (groupNo == arr[i].groupNo && moreNo == arr[i].no && arr[i].status == true) {														
				contents += template(arr[i])			
				if ($(thisTag).nextAll().children(".accept-name").html() == contents) {				
					return false;
				} 	
			}  
		/*	if (groupNo == arr[i].groupNo && moreNo == arr[i].no && arr[i].status == false) {
				contents2 += template(arr[i])
				if (contents2.length == 0) {
					contents += "참석자가 없습니다"
				}
			}*/
		}
		$(thisTag).next().children(".accept-name").html(contents)	

	})
	
}
$('body').on('click', '.more-schedule',function () {
	ajaxMoreScheduleList($(this).attr('data-value'), $(this))	
	$(this).next().show();
});

$('body').on('click', '.more-close',function () { 
	$(this).parent().hide()
});