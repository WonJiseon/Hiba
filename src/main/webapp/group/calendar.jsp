
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<link href='../css/fullcalendar.css' rel='stylesheet' />
<link href='../css/fullcalendar.print.css' rel='stylesheet'
	media='print' />
<link href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/css/bootstrap.min.css" rel="stylesheet"/>
<link href="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/build/css/bootstrap-datetimepicker.css" rel="stylesheet"/>
<script src='../js/moment.min.js'></script>

<script src='../js/fullcalendar.min.js' charset="UTF-8"></script>
<script src='../js/ko.js'></script>
<script>
"use strict";
  $(document).ready(function() {
	  var CalLoading = true;    
    $('#calendar').fullCalendar({
      header: {
        left: 'prev,next today',
        center: 'title',
        right: 'month,agendaWeek,agendaDay'
      },
      lang : "ko",
      navLinks: true, // can click day/week names to navigate views
      selectable: true,
      selectHelper: true,
      disableDragging : true,
      select: function(start, end) {
    	  var title = prompt('Event Title:');
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
      },

      eventRender: function(event, element, view) {
    	    var ntoday = new Date().getTime();
          var eventEnd = moment(event.end).valueOf();
          var eventStart = moment(event.start).valueOf();
          if (eventStart >= ntoday) {
        	  element.append( "<span class='closeon' style='position:absolute; right:0; top:0; z-index:1000;'>X</span>" );
        	  
          } 
          if (eventEnd < ntoday) {
        	  event.editable = false;
          }
         element.find(".closeon").click(function() {         
        	  var r = confirm("Delete " + event.title);
            if (r == true) {
            	  $('#calendar').fullCalendar('removeEvents', event._id);
            }              
          });		        
 
      },
    	eventClick: function(event, start, end) {
    		var moment11 = $('#calendar').fullCalendar('getDate');
    		start = moment(event.start).format('YYYY-MM-DD HH:mm');
    		end = moment(event.end).format('YYYY-MM-DD HH:mm');

    		alert("Event title: " + event.title + " Start Date: " + start + " End Date: " + end );	
    		
    		},
    	
    		viewRender:function (view,element) { 	     
	        	$('#btnPopupSave').click(function () {
	        		
	             var event={ 
	                 title: $('#eventTitle').val(), 
	                 start: $('#eventDateStart').val(),
	                 end: $('#eventDateEnd').val()
	                 
	           };
	          
	          $('#calendar').fullCalendar('renderEvent', event, true);
	          $('#calendar').fullCalendar('addEventSource', event);
	          $('#calendar').fullCalendar('refetchEvents');
	          
	         });
   	    },	 		
      editable: true,
      eventLimit: true, // allow "more" link when too many events      
      events: [

							{
			
								title: "Meeting",
						    start: "2016-09-18T10:30:00-05:00",
						    end: "2016-09-22T12:30:00-05:00"
						    
						  },
						  {
						      
                title: "Meeting",
                start: "2016-09-15T10:30:00-05:00",
                end: "2016-09-18T12:30:00-05:00"
              }
      ]
    });
    $(function(){
        $('#eventDateStart').datetimepicker({format:"YYYY-MM-DD HH:mm"}).data('DateTimePicker').date(new Date());
        $('#eventDateEnd').datetimepicker({format:"YYYY-MM-DD HH:mm"}).data('DateTimePicker').date(new Date());
        $('#calendar').fullCalendar('refetchEvents');
     });
  });   

     /* 모바일..
   var filter = "win16|win32|win64|mac";
  
   if (navigator.platform) {
     if(0 > filter.indexOf(navigator.platform.toLowerCase())) {
       $('.mobile-add-caledar').css('display','block')
     }
   }
     */
</script>
<style>
#calendar {
	max-width: 900px;
	margin: 0 auto;
	
}
</style>

	<div id='calendar'></div>
	
	
	<div class="mobile-add-caledar" >
	 
		<div class="container" style="position:relative">
		  <form>
		    <ul>
		      <li><span>이벤트 : </span><input type="text" id="eventTitle" placeholder="Title here"><br /></li>
		      <li><span>시작일 : </span><input type="text" id="eventDateStart"><br></li>
		      <li><span>종료일 : </span><input type="text" id="eventDateEnd"></li>
		    </ul> 
	    </form>	    
		</div>
		<button id="btnPopupSave">save</button>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/moment.js/2.9.0/moment-with-locales.js"></script>
		<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.5/js/bootstrap.min.js"></script>
		<script src="http://cdn.rawgit.com/Eonasdan/bootstrap-datetimepicker/a549aa8780dbda16f6cff545aeabc3d71073911e/src/js/bootstrap-datetimepicker.js"></script>
		

</div>

 

  