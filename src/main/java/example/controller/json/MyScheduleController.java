package example.controller.json;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.service.MyScheduleService;
import example.vo.JsonResult;
import example.vo.MySchedule;

@Controller
@RequestMapping("/myschedule/")
public class MyScheduleController {
  
  @Autowired MyScheduleService myscheduleService;
  
  @RequestMapping(path="list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="5") int length) throws Exception {
    
    try {
      HashMap<String,Object> map = new HashMap<>();
      map.put("startIndex", (pageNo - 1) * length);
      map.put("length", length);
      
      List<MySchedule> list = myscheduleService.getMyScheduleList(pageNo, length);
      int totalPage = myscheduleService.getTotalPage(length);
      
      
      HashMap<String,Object> data = new HashMap<String,Object>();
      data.put("list", list);
      data.put("totalPage", totalPage);
      data.put("pageNo", pageNo);
      data.put("length", length);
      
      return JsonResult.success(data);

    } catch (Exception e) {
      return JsonResult.fail(e.getMessage());
    }
  }
  
  @RequestMapping(path="listefgroupName")
  public Object listefgroupName(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="1") int length) throws Exception {
    
    try {
      HashMap<String,Object> map = new HashMap<>();
      map.put("startIndex", (pageNo - 1) * length);
      map.put("length", length);
      
      List<MySchedule> list = myscheduleService.getMyScheduleListEFGroupName(pageNo, length);
      int totalPage = myscheduleService.getTotalPage(length);
      
      
      HashMap<String,Object> data = new HashMap<String,Object>();
      data.put("list", list);
      data.put("totalPage", totalPage);
      data.put("pageNo", pageNo);
      data.put("length", length);
      
      return JsonResult.success(data);

    } catch (Exception e) {
      return JsonResult.fail(e.getMessage());
    }
  }

  @RequestMapping(path="detail")
  public Object detail(int groupscNo) throws Exception {
    try {
      MySchedule myschedule = myscheduleService.getScheduleByScheduleNo(groupscNo);

      if (myschedule == null) 
        throw new Exception("해당 번호의 스케줄이 존재하지 않습니다.");

      return JsonResult.success(myschedule);

    } catch (Exception e) {
      return JsonResult.fail(e.getMessage());
    }
  }
  
}
