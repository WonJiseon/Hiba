"use strict";
function showCalendar(arr) { 	
	$('#calendar').fullCalendar({		
		customButtons: {			
			myCustomButton: {
				text: 'save',
				click: function() {						
					var checkPoint = $('.list-checked').is(':checked')																
					var getGroupNo = $(location).attr('search')	
					var groupNo = getGroupNo.split("=")[1]
					var memberNo = $("#userName").attr('data-value')
					var a = $("#map").find('a').attr('href')					
					var b = a.split("=")[1];
					var c = b.split("&")[0];
					var llet = c.split(",")[0];
					var lot = c.split(",")[1];
					
					
					var arr = $("#pac-input").val().split(" ")
					var placeName = []
					if (arr[0] == "대한민국") {
						for (var i = 1; i < arr.length; i++) {	
							placeName += arr[i] + " "
						}
					} else {
						for (var i = 0; i < arr.length; i++) {	
							placeName += arr[i] + " "
						}
					}
					placeName.trim();
					
					if (checkPoint == false) {				
						var count = ""
							var len = $('.fc-event-container').length
							for (var i = 0; i <= len; i++) {												
								count++	
							}
						var event = {
								title : $("#addeventTitle").val(),
								start : $("#addDateStart").val(),
								end : $("#addDateEnd").val(),
								groupNo : groupNo,
								memberNo : memberNo,
								placeName : placeName,
								lat : llet,
								lon : lot,
								titleNo : count,
								id : count,
						}
						
						ajaxAddSchedule(event)	
						var eventDataValue = $('.sc-list').length;
						var errorTest = "입력하지 않은 항목이 있습니다."
							if ($('#addeventTitle').val().length != 0 
									&& $('#addDateStart').val().length != 0 
									&& $('#addDateEnd').val().length != 0
							) {
								swal(
										'Good job!',
										'일정을 성공적으로 만들었습니다.',
										'success'							
								)
		
																	
								$('#calendarAddModal').modal('hide');
											
							} else {
								checkInput()
							}
					} else {		
						var count = "";
			            $.each($("input[name='schedule']:checked"), function(){            
			            	count = ($(this).attr('data-value'));
			            });					
			            var event = {
								title : $("#addeventTitle").val(),
								start : $("#addDateStart").val(),
								end : $("#addDateEnd").val(),
								groupNo : groupNo,
								memberNo : memberNo,
								placeName : placeName,
								lat : llet,
								lon : lot,
								id : count
						}
			            
						ajaxAddSchedule(event)
						var errorTest = "입력하지 않은 항목이 있습니다."
							if ($('#addeventTitle').val().length != 0 
									&& $('#addDateStart').val().length != 0 
									&& $('#addDateEnd').val().length != 0
							) {
																	
								swal(
										'Good job!',
										'일정을 성공적으로 만들었습니다.',
										'success'							
								)
								$('#calendarAddModal').modal('hide');
							} else {
								checkInput()
							}
					}
					function checkInput() {
						if ($('#addeventTitle').val().length == 0) {

							$('#addeventTitle').css("border", "1px solid #d9534f")	
							$('.title-state').html(errorTest)					
						} else {

							$('#addeventTitle').css("border", "1px solid #5cb85c")
							$('.title-state').html('')
						} 

						if ($('#addDateStart').val().length == 0) {
							$('#addDateStart').css("border", "1px solid #d9534f")
							$('.start-state').html(errorTest)				
						} else {
							$('#addDateStart').css("border", "1px solid #5cb85c")
							$('.start-state').html('')
						}

						if ($('#addDateEnd').val().length == 0) {
							$('#addDateEnd').css("border", "1px solid #d9534f")
							$('.end-state').html(errorTest)		
						} else {
							$('#addDateEnd').css("border", "1px solid #5cb85c")
							$('.end-state').html('')
						}
						swal(
								'입력하지 않은 항목이 존재합니다.',
								'You clicked the button!',
								'error'							
						)	
					}
					
					
					
				}

			}	
		},
		header: {
			left: 'prev,next today myCustomButton',
			center: 'title',
			right: 'month' // agendaWeek,agendaDay
		},
		lang : "ko",
		navLinks: true, // can click day/week names to navigate views
		selectable: false,
		selectHelper: true,
		disableDragging : false,
		/*select: function(start, end) {
			var title = swal({
				  title: '이벤트를 입력해 주세요',
				  input: 'text',
				  showCancelButton: true,
				  inputValidator: function(value) {
				    return new Promise(function(resolve, reject, title) {
				      if (value) {
				        resolve();

				      } else {
				        reject('You need to write something!');
				      }
				    });
				  }			
				}).then(function(result) {
				  swal({
				    type: 'success',
				    html: 'You entered: ' + result
				  });
			})
			var eventData;
			if (moment().diff(start, 'days') > 0) {
				$('#calendar').fullCalendar('unselect');        
				alert("지나간 날입니다.");        	  
				return false;
			}     
			if (title) {
				eventData = {
						title: title,
						start: start,
						end: end
				};
				$('#calendar').fullCalendar('renderEvent', eventData, true); // stick? = true
			}      
			$('#calendar').fullCalendar('unselect');
		},*/
		dayClick: function(date, jsEvent, view) {

			var clickDay = moment(date).format('YYYY-MM-DD HH:mm');
			$('#calendarAddModal').modal();
			$('.fc-myCustomButton-button').css({ "opacity": "0.0" , "position" : "absolute"});
			$('#addeventTitle').val('') 
			$('#addDateStart').val(clickDay);
			$('#addDateEnd').val('')
			$('#pac-input').val('')
			$('.fc-myCustomButton-button').css({ "opacity": "1.0" ,  "position" : "static" });
			if ($('.fc-myCustomButton-button').length <= 1) {				
				$(".fc-myCustomButton-button").appendTo('#add-moadl-footer')					
			}	


		},	
		eventRender: function(event, element, view) {
			
			/* 일정 추가 팝업*/
			
			$('.fc-content').data('data-scno', event.id)
	
			var ntoday = new Date().getTime();
			var eventEnd = moment(event.end).valueOf();
			var eventStart = moment(event.start).valueOf();
			
			if (eventStart >= ntoday) {
				/*$('.fc-event-container').attr('data-scno', event.id)
				element.prepend( "<span class='scno' data-scno=" + event.id +">" + event.id + "</span>" );*/			
				element.append( "<span class='closeon' style='display:blokc; position:absolute; right:0; top:0; z-index:1000;' data-no=" + event.groupPlaceNo +">X</span>" );

			} 
			if (eventEnd < ntoday) {
				event.editable = false;
			}
			element.find(".closeon").click(function() {    
				var no = $(this).attr('data-no')
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
					ajaxDeleteSchedule(no)
					$('#calendar').fullCalendar('renderEvent', event, true)
				})								
				return false;				
			});
			
		
		},
		eventClick: function(event, start, end) {
		
			start = moment(event.start).format('YYYY-MM-DD HH:mm')
			end = moment(event.end).format('YYYY-MM-DD HH:mm')
			$('#calendarModal').modal()
			
			$('#modalTitle').html(event.title)
			$('.modal-start-date').text(start + " ~ ")
			$('.modal-end-date').text(end + " ")
			
			var thisIndex = event.groupPlaceNo
			if (thisIndex) {				
				var marker;
				function initMap() {
				  var map = new google.maps.Map(document.getElementById('google_map'), {
				    zoom: 13,
				    center: {lat: Number(event.lat), lng: Number(event.lon)}
				  });
				  var markerMaxWidth = 260;  // 마커를 클릭했을때 나타나는 말풍선의 최대 크기
				  var contentString = '<table><tr><td><div>' + 
				     '<span style="padding-bottom:10px"><b>'+event.title+'</b></span><br />'+ 
				     '<div class="map_Content">'+ 
				       '주소:' +  event.placeName + 
				     '</div>'+ 
				      '</div></td></tr></table>'; 
				  marker = new google.maps.Marker({
				    map: map,
				    draggable: true,
				    animation: google.maps.Animation.DROP,
				    position: {lat: Number(event.lat), lng: Number(event.lon)}
				  });
				  
				  var infowindow = new google.maps.InfoWindow({
				    content: contentString,				    
				    position: {lat: Number(event.lat), lng: Number(event.lon)}
				  
				  });
				  infowindow.open(map, marker);

				
				}
							
				$('#calendarModal').on('shown.bs.modal', function(){
					
					initMap()
					
				});
			} 
		},
		editable: true,
		eventLimit: true, // allow "more" link when too many events      			
		events: arr	
	});
	$(function(){
		$('#addDateStart').datetimepicker({format:"YYYY-MM-DD HH:mm"}).data('DateTimePicker').date(new Date());
		$('#addDateEnd').datetimepicker({format:"YYYY-MM-DD HH:mm"}).data('DateTimePicker').date(new Date());
		$('#addDateStart').val('');
		$('#addDateEnd').val('');
		$('#addeventTitle').val('');
		
		$(".fc-myCustomButton-button").addClass("btn btn-primary")
		$('.fc-myCustomButton-button').css({ "opacity": "0.0" , "position" : "absolute"});
		$('#addeventTitle').val('') 
		$('#addDateStart').val('')
		$('#addDateEnd').val('')
		$('#pac-input').val('')
		$('body').on('click', '.make-sc-btn', function(e) {
			
			$('#calendarAddModal').modal();				
			$('.fc-myCustomButton-button').css({ "opacity": "1.0" ,  "position" : "static" });
			if ($('.fc-myCustomButton-button').length <= 1) {				
				$(".fc-myCustomButton-button").appendTo('#add-moadl-footer')					

			}	
		})	
		
	});

};   

function ajaxMyScheduleList(no) {
	$('header').css({"display" : "none"})
	$('.make-text').css({"display" : "none"})
	$('.header-top').css({"display" : "none"})
	$('.header-top').css({"display" : "none"})
	$('.header-top').css({"display" : "none"})
	$('.wrap').removeClass('display-none');
	$.getJSON(serverAddr +"/schedule/list.json", function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("서버에서 데이터를 가져오는데 실패 했습니다.")
			return
		} 
		var contents = ""
		var arr = result.data
	    var arrTest=[]
		function getFormatDate(date){
			var year = date.getFullYear();                                 
			var month = (1 + date.getMonth());                    
			month = month >= 10 ? month : '0' + month;  
			var day = date.getDate();                                       
			day = day >= 10 ? day : '0' + day;                           
			return  year + '-' + month + '-' + day;
		}

		var date = new Date();
		date = getFormatDate(date);
		var template = Handlebars.compile($('#sideScheduleList').html())
		for (var i in arr) {
			if (no == arr[i].groupNo) {				
		        arrTest.push(arr[i]);
		        
		        if(arr[i].titleNo) {
		        	if (arr[i].start > date) {
		        		contents += template(arr[i])
		        	}       			        	
		        }
			}
			
		}
		
		showCalendar(arrTest);
		$(".side-schedhule-List").html(contents) 
	
	$('header').css({"display" : "block"})
	$('.make-text').css({"display" : "block"})
	$('.header-top').css({"display" : "block"})
	$('.header-top').css({"display" : "block"})
	$('.header-top').css({"display" : "block"})
	$('.wrap').addClass('display-none');
	
	})
}




function ajaxAddSchedule(event) {
	$.post(serverAddr +"/schedule/add.json", event, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("등록 실패 입니다.")       
			return
		}  
		window.location.reload()   
	}, "json" )	
}


function ajaxUpdateMember(event) {	
	$.post(serverAddr +"/schedule/update.json", event, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("변경 실패입니다.")
			return
		}
		window.location.href = "memberApp.html"
	}, "json")
}

function ajaxDeleteSchedule(no) {
	$.getJSON(serverAddr +"/schedule/delete.json",{
		no: no
	}, function(obj) {
		var result = obj.jsonResult
		if (result.state != "success") {
			alert("삭제 실패 입니다.11")       
			return
		} 
		window.location.reload()   		
	})		
}


