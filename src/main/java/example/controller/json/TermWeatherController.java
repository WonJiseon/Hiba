/*
 * 2016.11.15 변경사항
 * 단기예보와 장기예보 추가를 위해 클래스명 변경
 * MidTermWeatherController -> TermWeatherController
 */
package example.controller.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import example.service.ScheduleWeatherService;
import example.vo.JsonResult;
import example.vo.TermWeather;
import example.vo.TermWeather;

@Controller
@RequestMapping("/myschedule/")
public class TermWeatherController {
	TermWeather termWeather;
  @Autowired ScheduleWeatherService scheduleWeatherService;
  @RequestMapping(path="termWeather")
  public Object list(int gpno, String date, int dday) throws Exception {
    try {
      HashMap<String,Object> data = new HashMap<String,Object>();
      String placeName = scheduleWeatherService.getPlaceName(gpno);
      TermWeather termWeather = null;
      String xml = "";
      if (dday >= 1 && dday <= 3) { // 단기 예보 
    	  xml = scheduleWeatherService.importShortTermWeather(placeName).toString();
    	  
    	  termWeather = scheduleWeatherService.shortTermXmlParser(xml, placeName, date, dday);
    	  System.out.println(xml);
    	  
    	  data.put("city", termWeather.getCity());
    	  data.put("state", termWeather.getState());
    	  data.put("minTemp", termWeather.getMinTemp());
    	  data.put("maxTemp", termWeather.getMaxTemp());
    	  data.put("currentTemp", termWeather.getCurrentTemp());
    	  data.put("term", "short");
      } else if (dday > 3 & dday <= 10) { //중기 예보 
    	  xml = scheduleWeatherService.importMidTermWeather(placeName).toString();
    	  System.out.println(date);
    	  System.out.println(xml);
    	  
    	  termWeather = scheduleWeatherService.midTermXmlParser(xml, date);

    	  data.put("city", termWeather.getCity());
    	  data.put("mode", termWeather.getMode());
    	  data.put("time", termWeather.getDate());
    	  data.put("state", termWeather.getState());
    	  data.put("minTemp", termWeather.getMinTemp());
    	  data.put("maxTemp", termWeather.getMaxTemp());
    	  data.put("term", "mid");
      } else if (dday > 10){ // 장기 예보 
    	  
      } else {
    	  throw new Exception("기간 정보가 잘못되었습니다. ");
      }
      
      if (termWeather == null)  {
        throw new Exception("해당 날짜에 날씨 정보가 존재하지 않습니다.");
      }
      
      return JsonResult.success(data);
    } catch(Exception e){
      System.out.println("test!!!");
      return JsonResult.fail(e.getMessage());
    }
  }
}
