var eventLocationList;
var mySchedulList;
var wholeScList = [];
var selectedScList = [];
var tempScList = [];
var reloadFlag = 0;


$("#loginBtn").click(function(event) {
   location.href = "../index.html"
});

$("#logoutBtn").click(function(event) {
   location.href = "../index.html"
});

$("#calendarModal").click(function() {

});


/*
$('#calendarModal').on('shown.bs.modal', function(){
   google_map("google_map", event.placeName);
   //console.log(event);      
   google.maps.event.trigger(map,'resize',{});
});*/

function listCheck() {
   for(var i in wholeScList) {
      console.log("wholeScList[" + i + "]" + wholeScList[i].groupNo + wholeScList[i].title)
   }
   for(var i in selectedScList) {
      console.log("selectedScList[" + i + "]" + selectedScList[i].groupNo +selectedScList[i].title)
   }
}

function removeDuplication(arr) {// 배열내에 중복된 요소 제거함수
    for(var i=0; i<arr.length; i++) {
        var checkDobl = 0;
        for(var j=0; j<arr.length; j++) {
            if(arr[i] != arr[j]) {
                continue;
            } else {
                checkDobl++;
                if(checkDobl>1){
                    spliced = arr.splice(j,1);
                }
            }
        }
    }
    return arr;
}

function showCalendar() {
   $('#calendar').fullCalendar({      
      header: {
         left: 'prev,next today myCustomButton',
         center: 'title',
         right: 'month' // agendaWeek,agendaDay
      },
      lang : "ko",
      navLinks: true, // can click day/week names to navigate views
      selectable: false,
      selectHelper: true,
      disableDragging : true,

      eventClick: function(event, start, end) {
         var placeName = event.placeName
         var gpno = event.gpno
         var moment11 = $('#calendar').fullCalendar('getDate')
         start = moment(event.start).format('YYYY-MM-DD HH:mm')
         end = moment(event.end).format('YYYY-MM-DD HH:mm')
         ajaxMidTermWeather(start, gpno)
         //console.log(start)
         //console.log(end)
         
         //alert("Event title: " + event.title + " Start Date: " + start + " End Date: " + end );
         
         console.log(event)
         $('#calendarModal').modal()
         console.log("11111111111")
         $('.modal-groupName').html(event.groupName + "그룹의")
         $('#modalTitle').html(event.title)
         $('.modal-start-date').html(start + "~")
         $('.modal-end-date').html(end)
         $(".modal-place").html(placeName)
         $('#modalBody').html(event.description)
         $('#eventUrl').attr('href',event.url)
         var groupscNo = event.groupscNo
         console.log(groupscNo)
         $("#detailBtn").click(function(event) {
            console.log(groupscNo)
            window.location.href = "detailInfo.html?no=" + groupscNo
         })

         function google_map(mapid, addr) {
            console.log(placeName)
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
                  '주소: ' + placeName + 
                  '</div>'+ 
                  '</div></td></tr></table>'; 

                  var marker = new google.maps.Marker({ 
                     position: map.getCenter(), 
                     map: map, 
                     draggable:false,
                     animation: google.maps.Animation.DROP, 
                     title: markerTitle,
                  }); 

                  var infowindow = new google.maps.InfoWindow({ 
                     content: contentString,
                     maxWidth: markerMaxWidth,
                     placeName : placeName
                  }); 
                  infowindow.open(map, marker, placeName); 

                  google.maps.event.addListener(marker, 'click', function() { 
                     infowindow.open(map, marker, placeName); 
                     
                  }); 
               }
            });
         }
         
         $('#calendarModal').on('shown.bs.modal', function(){
            google_map("google_map", placeName);
            google.maps.event.trigger(map,'resize',{});
         
         });
      },
      editable: true,
      eventLimit: true, // allow "more" link when too many events 
      events: wholeScList 
      
   });
/*   $('#calendar').fullCalendar( 'addEventSource',        
         function(startDate, endDate, callback, event) {
   }*/
}

function reloadWholeCalendar() {
   $('#calendar').fullCalendar('removeEvents');
   $('#calendar').fullCalendar('refetchEvents');
   $('#calendar').fullCalendar('addEventSource', wholeScList);
   $('#calendar').fullCalendar('refetchEvents');
   
   selectedScList = wholeScList;
   //listCheck();
}
function reloadCalendar() {
   //selectedScSet = removeDuplication(selectedScList);
   
   $('#calendar').fullCalendar('removeEvents')
   $('#calendar').fullCalendar('refetchEvents')
   $('#calendar').fullCalendar('addEventSource', selectedScList)
   $('#calendar').fullCalendar('refetchEvents');
   listCheck();
   
}


function getLatLon(placeName) {
   for (var i in eventLocationList) {
      if (eventLocationList[i].placeName == placeName) {
         return eventLocationList[i];
      }
   }
}


function removeDuplicate(inArray) {
	console.log(inArray)
      var arr = {};

      for (var i in inArray ) {
    	  console.log(inArray[i])
         arr[inArray[i]['groupNo']] = inArray[i];
      }
      
      inArray = new Array();

      for ( var key in arr )
         inArray.push(arr[key]);   

      return inArray;
   }

function ajaxMyScheduleList() {
   
   var placeName = "";
   var lon = "";
   var lat = "";
   

   $.getJSON(serverAddr + "/myschedule/list.json", function(obj) {
      var result = obj.jsonResult
      if (result.state != "success") {
         alert("서버에서 데이터를 가져오는데 실패했습니다.")
         return
      }

      var contents = "";
      var prevmyScheduleList = result.data.list;
      var myScheduleList = [];
      
      for (var i in prevmyScheduleList) {
    	  console.log($("#user").attr('data-value'))
    	  if ($("#user").attr('data-value') == prevmyScheduleList[i].no
    			  && prevmyScheduleList[i].myScheduleStatus == 1) 
    		  myScheduleList[i] = prevmyScheduleList[i]
      }
      
      var newArr = removeDuplicate(myScheduleList)

      var template = Handlebars.compile($('#divTemplateText').html())
      
      for (var i in newArr) {
         if ($("#user").attr('data-value') == newArr[i].no
        		 && newArr[i].myScheduleStatus == 1){
            contents += template(newArr[i])
            $("#groupList").attr('data-value', newArr[i].groupNo)
         }
      }
      
      
      for (var i in myScheduleList) {       
            wholeScList.push(myScheduleList[i]);
            $("#groupList").attr('data-no', myScheduleList[i].groupscNo) // 그 원지선이 가진 no중에서 groupscNo 넣어주는거
         
      }
      
      $("#groupList").html(contents)
      showCalendar();
      
      $("input:checkbox").change(function() {
    	  var no = $(this).attr('value');
    	  var groupNo = $(this).attr('data-value');
    	  var tempArr = [];
    	  if($(this).is(":checked")) {
    		  for(var i = 0; i < wholeScList.length; i++) {
    			  if(wholeScList[i]['groupNo'] == groupNo)
    				  selectedScList.push(wholeScList[i]);
    		  }
    	  } else {
    		  /*
    		   * 선택된 group의 groupNo와 일치하는 스케줄들을 tempArr에 넣어놓고
    		   * 차집합의 원리를 이용해서 selectedScList와 중복되는 값을을 제거하여
    		   * 다시 selectedScList에 대입해서 Calendar를 reload를 한다.
    		   */
    		  for(var i = 0; i < selectedScList.length; i++) {
    			  if(selectedScList[i].groupNo == groupNo) {
    				  tempArr.push(selectedScList[i]);
    			  }     		 
    		  }
    		  selectedScList = $(selectedScList).not(tempArr).get();
    	  }
    	  reloadCalendar();
      });

      $("a#whole-grpSchedule").click(function(){
         $('#groupList').each(function() {
            if($(this).is(":checked"))
               $(this).attr('checked', false);
         })
         reloadWholeCalendar();
      })
      
   })
   
}


/*function ajaxWeather(lat, lon) {
   console.log(lat, lon);
   $.getJSON(skPlanetWeather, {
      "lat": lat,
      "lon": lon,
      "version": "1",
      "appKey": "6e62a500-8f2f-36d6-ac6d-2fcc4b4a5a23"
   }, function(data) {
      var arr = data.result
      if (arr.message != "성공") {
         alert("서버에서 데이터를 가져오는데 실패했습니다.")
         return
      }
      var calendarWeather = data.weather
      console.log(calendarWeather.minutely[0])
      $(".humidity").html(calendarWeather.minutely[0].humidity)
      $(".precipitation").html(calendarWeather.minutely[0].precipitation.sinceOntime)
      $(".sky").html(calendarWeather.minutely[0].sky.name)
      $(".temperature-tc").html(calendarWeather.minutely[0].temperature.tc)
      $(".temperature-tmax").html(calendarWeather.minutely[0].temperature.tmax)
      $(".temperature-tmin").html(calendarWeather.minutely[0].temperature.tmin)
      $(".timeObservation").html(calendarWeather.minutely[0].timeObservation)
      
      for (var i in arr) {
         if (arr[i].weather)
            contents += arr[i]
      }
   })
}
function ajaxEventLocationList() {
   $.getJSON(serverAddr + '/myschedule/listWeather.json', function(obj){
      var result = obj.jsonResult
      if (result.state != "success") {
         alert("서버에서 데이터를 가져오는데 실패했습니다.")
         return
      }
      eventLocationList = result.data;
   })
}
*/

function ajaxMidTermWeather(date, gpno) {
	date = date.substring(0,10);

	$.getJSON(serverAddr + '/myschedule/midTermWeather.json?gpno='
			+gpno+'&date='+date, function(obj) {
		midTermResult = obj.jsonResult;
		console.log(midTermResult);
		if(midTermResult.state != "success") {
			alert("조회 실패입니다.");
			return;
		}
		
		$(".weather-city").html(midTermResult.data.city);
		$(".weather-temperature-mx").html(midTermResult.data.maxTemp + "°C");
		$(".weather-temperature-mn").html(midTermResult.data.minTemp + "°C");
	
		switch(midTermResult.data.state) {
	      case "맑음":
	    	  $(".weather-img").addClass("sunny");
	    	  break;
	    	  
	      case "구름조금":
	    	  $(".weather-img").addClass("partlyCloudyDay");
	    	  break;
	      case "구름많음" :
	    	  $(".weather-img").addClass("heavyClouds");
	    	  break;
	      case "흐림" :
	    	  $(".weather-img").addClass("clouds");
	    	  break;
	      case "비":
	    	  $(".weather-img").addClass("rain");
	    	  break;
	      case "흐리고 비":
	    	  $(".weather-img").addClass("rainWithClouds");
	    	  break;
	     case "구름많고 비/눈":
	    	  $(".weather-img").addClass("snowAndRain");
	    	  break;
	       };
		$(".weather-state").html(midTermResult.data.state);
	});
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
      $("#user").text(result.data.name)
      $("#user").attr('data-value', result.data.no)
      
      $("#userSc").attr('data-value', result.data.no)
      $("#profilePhoto").attr("src", "../upload/" + result.data.filename)

   })
}