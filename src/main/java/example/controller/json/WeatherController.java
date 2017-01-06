package example.controller.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import example.dao.WeatherDao;
import example.vo.JsonResult;

@Controller 
@RequestMapping("/myschedule/") 
public class WeatherController {
  @Autowired
  WeatherDao weatherDao;

  @RequestMapping(path="listWeather") 
  public Object list() throws Exception {
    try{
      HashMap<String,Object> map = new HashMap<>();
      
      return JsonResult.success(weatherDao.selectList(map));
    }catch(Exception e){
      return JsonResult.fail(e.getMessage());
    }
  }
}