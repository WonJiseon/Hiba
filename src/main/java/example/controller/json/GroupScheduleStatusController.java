package example.controller.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.dao.GroupScheduleStatusDao;
import example.vo.EventStatus;
import example.vo.JsonResult;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/schedulestatus/") // 이 페이지의 컨트롤러의 기준 URL
public class GroupScheduleStatusController {
	@Autowired
	GroupScheduleStatusDao groupScheduleStatusDao;


	
	@RequestMapping(path="list")
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="100") int length) throws Exception {
		try {
			HashMap<String,Object> map = new HashMap<>();
			map.put("startIndex", (pageNo - 1) * length);
			map.put("length", length);

			return JsonResult.success(groupScheduleStatusDao.selectList(map));
			
		} catch (Exception e) {
	
			return JsonResult.fail(e.getMessage());
		}					
	}
		
	@RequestMapping(path="add")
	public Object add(EventStatus eventStatus) throws Exception {
		// 성공하든 실패하든 클라이언트에게 데이터를 보내야 한다.
		try {
			
			
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("no", eventStatus.getNo());
			paramMap.put("groupNo", eventStatus.getGroupNo());
			paramMap.put("memberNo", eventStatus.getMemberNo());
			
			if (groupScheduleStatusDao.selectOneByScheduleNoAndMemberNoAndGroupNo(paramMap) == null) {
				groupScheduleStatusDao.insert(eventStatus);
			}  else {
				groupScheduleStatusDao.update(eventStatus);	
			}			
			return JsonResult.success();
		} catch (Exception e) {
			
			return JsonResult.fail(e.getMessage());
		}						
	}
	
	@RequestMapping(path="update")
	public Object update(EventStatus eventStatus) throws Exception{

		try {
			groupScheduleStatusDao.update(eventStatus);
			return JsonResult.success();
		} catch (Exception e) {
			
			return JsonResult.fail(e.getMessage());
		}					
		
	}
	
	@RequestMapping(path="delete")
	public Object delete(int no) throws Exception {
		try {
			groupScheduleStatusDao.delete(no);
			return JsonResult.success();
		} catch (Exception e) {
			
			return JsonResult.fail(e.getMessage());
		}					
	}
	
	
	
	
}
