package example.controller.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.dao.PlaceDao;
import example.vo.Event;
import example.vo.JsonResult;
import example.vo.Place;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/place/") // 이 페이지의 컨트롤러의 기준 URL
public class PlaceController {
	@Autowired
	PlaceDao placeDao;
	
	@RequestMapping(path="list")
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="30") int length) throws Exception {

		try {
			HashMap<String,Object> map = new HashMap<>();
			map.put("startIndex", (pageNo - 1) * length);
			map.put("length", length);
			return JsonResult.success(placeDao.selectList(map));
			
		} catch (Exception e) {
	
			return JsonResult.fail(e.getMessage());
		}					
	}
		
	@RequestMapping(path="add")
	public Object add(Place place) throws Exception {
		// 성공하든 실패하든 클라이언트에게 데이터를 보내야 한다.
		try {
			placeDao.insert(place);
			return JsonResult.success();
		} catch (Exception e) {
			
			return JsonResult.fail(e.getMessage());
		}						
	}
	
	@RequestMapping(path="detail")
	public Object detail(int no) throws Exception{
		
		try {
			Place place = placeDao.selectOne(no);
			
			if (place == null)
				throw new Exception("해당 번호의 게시물이 존재하지 않습니다.");
			return JsonResult.success(place);
			
		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}		
		
	}
	
	
	@RequestMapping(path="update")
	public Object update(Place place) throws Exception{

		try {
			placeDao.update(place);
			return JsonResult.success();
		} catch (Exception e) {
			
			return JsonResult.fail(e.getMessage());
		}					
		
	}
	
	@RequestMapping(path="delete")
	public Object delete(int no) throws Exception {
		try {
			placeDao.delete(no);
			return JsonResult.success();
		} catch (Exception e) {
			
			return JsonResult.fail(e.getMessage());
		}					
	}
	
	
	
	
}
