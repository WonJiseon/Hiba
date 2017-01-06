$('body').on("click", ".btn-group ul li", function(e) {
	 var thisIndex = $(this).index();
	 console.log(thisIndex)
	 $("article").hide();
	 $("article").eq(thisIndex).show();
	 
 });
 
 $(".btn-group ul li").eq(0).click();
  

var detailResult;
var eventLocation;
var mySchedulList;
var startDay;
var endDay;
var gpno;
var tourKey = 'OdaqOYG5yh3hxY4TPYcvp3AMSAUwYjRdEYSibyUuD3PYYjK%2BR%2FfyUSnEyCas2KQDZmHtFIZ8Ir1gC4XIddpJtA%3D%3D';
var placeName = "";
var tourAreaCode = "";
var sigunguCode = "";

$("#loginBtn").click(function(event) {
   location.href = "../index.html"
});
$("#logoutBtn").click(function(event) {
   location.href = "../index_h.html"
});

/*
function ajaxgetAreaCode() {
   $.getJSON(areaCodeAddr, function(obj) {
      var result = obj.response.body.items
      var arr = result.item
      console.log(arr)
   })
}
 */

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

function cityNameParser(inputStr) {
	var tempArr = inputStr.split(" ");
	var tempStr = tempArr[0];
	var sigunguStr = "";
	var resultStr = "";
	console.log("r_test:" + tempStr);
	if(tempStr == "서울특별시"  || tempStr == "인천광역시" || tempStr == "대전광역시" ||
			tempStr == "대구광역시" || tempStr == "울산광역시" || tempStr == "광주광역시"
			|| tempStr == "부산광역시") {
		resultStr = tempStr.substring(0,2);
	} else {
		resultStr = tempStr;
	}	
	
	return resultStr;
}

function sigunguNameParser(inputStr) {
	var tempArr = inputStr.split(" ");
	
	return tempArr[1];
}

function ajaxTourInfo(tourAreaCode, sigunguCode) {
   console.log(tourAreaCode);
   console.log(sigunguCode);
   
   
   var contents = ""
   var contents2 = ""
   var template = ""
   var template2 = ""

   var festiUrl = "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=" +
         tourKey + "&contentTypeId=15&areaCode="+ tourAreaCode +"&sigunguCode="+sigunguCode+"&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1";
   var accUrl =  "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaBasedList?ServiceKey=" +
   tourKey + "&contentTypeId=32&areaCode="+ tourAreaCode +"&sigunguCode="+sigunguCode+"&cat1=&cat2=&cat3=&listYN=Y&MobileOS=ETC&MobileApp=TourAPI3.0_Guide&arrange=A&numOfRows=12&pageNo=1";
   
   console.log(festiUrl);
   $.getJSON(festiUrl, function(obj) {
      var festiResult = obj.response.body.items.item;
      //console.log(festiResult)
      contents = "";
      template = Handlebars.compile($('#festiResult').html())
      
      for (var i in festiResult) {
    	  contents += template(festiResult[i])
      }
      
      //console.log(contents)
      
       $("#products").html(contents)
      
   });
   
   $.getJSON(accUrl, function(obj) {
      var accResult = obj.response.body.items.item;
      
      //console.log(accResult)
      
      contents2 = "";
      template2 = Handlebars.compile($('#accResult').html())
      
       for (var i in accResult) {
    	  contents2 += template2(accResult[i])
    	  
    	  //console.log(contents2)
      }
      $("#products2").html(contents2)

   });

}

function ajaxSigunguCode(tourAreaCode) {
	console.log(tourAreaCode);
	var url = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?ServiceKey='
		+ tourKey + 
		'&numOfRows=50&areaCode='
		+ tourAreaCode +
		'&pageNo=1&MobileOS=ETC&MobileApp=TestApp&_type=json';
	console.log(url);
	$.getJSON(url, function(obj) {
		sigunguResult = obj.response.body.items.item;
      if(obj.response.header.resultCode != "0000") {
         alert("AreaCode를 불러올수가엄써!");
      }
      
      var tempStr = sigunguNameParser(placeName);
      console.log("sigungu" + tempStr);
      for(var i in sigunguResult) {
    	  if (sigunguResult[i].name == tempStr) 
    		  sigunguCode = sigunguResult[i].code;
      }
      console.log(sigunguCode);
      ajaxTourInfo(tourAreaCode, sigunguCode);
	});
   
}
function ajaxTourAreaCode() {
   var url = 'http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode?ServiceKey='
      + tourKey + 
   '&numOfRows=17&pageNo=1&MobileOS=ETC&MobileApp=TestApp&_type=json';
      
   $.getJSON(url, function(obj) {
      areaResult = obj.response.body.items.item;
      if(obj.response.header.resultCode != "0000") {
         alert("AreaCode를 불러올수가엄써!");
      }
      var tempStr = cityNameParser(placeName);
      console.log(tempStr);
      for(var i in areaResult) {
         if (areaResult[i].name == tempStr) 
            tourAreaCode = areaResult[i].code;
      }
      console.log(tourAreaCode);
      ajaxSigunguCode(tourAreaCode);
   })
}




function ajaxMyScheduleLoad(no) {
   $.getJSON(serverAddr + "/myschedule/detail.json?groupscNo=" + no, function(obj) {
      detailResult = obj.jsonResult

      startDay = detailResult.data.start;
      endDay = detailResult.data.end;
      gpno = detailResult.data.gpno;
      

      if (detailResult.state != "success") {
         alert("조회 실패입니다.")
         return
      }

      var contents = "";
      var template = Handlebars.compile($('#divTemplateText').html())

      detailResult.data.dday = computeDday(detailResult.data.start)
      contents = template(detailResult.data)
      $("#detail").html(contents)

      var template2 = Handlebars.compile($('#groupTemplateText').html())
      var contents2 = template2(detailResult.data.groupName)
      placeName = detailResult.data.placeName;
      console.log(detailResult.data.groupName)
      console.log(contents2)
      $("#groupName").html(contents2)
      ajaxTermWeather(startDay, gpno, detailResult.data.dday);
      ajaxTourAreaCode();
   })
}

/*
 * 2016.11.1 수정사항 : 중기예보를 불러오는 ajaxMidTermWeather 추가
 * gpno : 그룹 번호, date : 날짜 (형식 : YYYY-MM-DD)
 */
function ajaxTermWeather(date, gpno, dday) {
   //date = date.substring(0,10);
   /*console.log(date);
   console.log("dday"+dday)
   console.log(gpno);*/
	
   $.getJSON(serverAddr + '/myschedule/termWeather.json?gpno='
         +gpno+'&date='+date+'&dday='+ dday, function(obj) {
	   termResult = obj.jsonResult;
	   
	      if(termResult.state != "success") {
	    	  $(".wrap-dayweather").html("기간 정보가 잘못되어 기상청에서 날씨 정보를 받아올 수 없습니다.");
	      }
	      if(termResult.data.term == "short") {
	    	  $(".weather-temperature-current").html(termResult.data.currentTemp + "°C");
	      }
      
      $(".weather-city").html(termResult.data.city);
      if (!termResult.data.temp) {
    	  $(".weather-temperature-mx2").html(termResult.data.maxTemp + "°C");
      } 
    $(".weather-temperature-mx2").html(termResult.data.currentTemp);    	  
      
    if ((termResult.data.maxTemp == -999.0) || (termResult.data.minTemp == -999.0)) {
      $(".weather-temperature-mx").html("기상청에서 정보를 받아올수 없습니다.");
      $(".weather-temperature-mn").html("기상청에서 정보를 받아올수 없습니다.");
    } else {
    	
    	$(".weather-temperature-mx").html(termResult.data.maxTemp + "°C");
    	$(".weather-temperature-mn").html(termResult.data.minTemp + "°C");
    }
      $(".weather-state").html(termResult.data.state);
   
      var aaa = termResult.data.state.trim()
      switch(aaa) { 
      case "맑음":
    	  $(".weather-img").addClass("sunny");
    	  break;
      case "구름조금":
    	  $(".weather-img").addClass("partlyCloudyDay");
    	  break;
      case "구름 조금":
    	  $(".weather-img").addClass("partlyCloudyDay");
    	  break;
      case "구름 많음" :
    	  $(".weather-img").addClass("heavyClouds");
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
    	  console.log()
       };
      $(".weather-state").html(termResult.data.state);
   });
      
      
      
      
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
      $(".weather-humidity").html(calendarWeather.minutely[0].humidity)
      $(".weather-windspeed").html(calendarWeather.minutely[0].wind.wspd)
      $(".weather-sky").html(calendarWeather.minutely[0].sky.name)
      $(".weather-temperature-tc").html(calendarWeather.minutely[0].temperature.tc)
      //$(".temperature-tmax").html(calendarWeather.minutely[0].temperature.tmax)
      //$(".temperature-tmin").html(calendarWeather.minutely[0].temperature.tmin)
      $(".timeObservation").html(calendarWeather.minutely[0].timeObservation)
      
      switch() {
      case:
      }
      
      
      for (var i in arr) {
         if (arr[i].weather)
            contents += arr[i]
      }
   })
}*/
/*
function ajaxEventLocationList() {
   $.getJSON(serverAddr + '/myschedule/listWeather.json', function(obj){
      var result = obj.jsonResult
      if (result.state != "success") {
         alert("서버에서 데이터를 가져오는데 실패했습니다.")
         return
      }

      var arr = result.data 
      console.log(detailResult.data.placeName)
      for (var i in arr) {
         if (detailResult.data.placeName == arr[i].placeName) {
            eventLocation = arr[i]

         }
      }

      console.log(eventLocation)

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
      $("#profilePhoto").attr("src", "../upload/" + result.data.filename)
   })
}