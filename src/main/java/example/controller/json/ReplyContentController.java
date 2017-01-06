package example.controller.json;

import java.util.HashMap;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.dao.ReplyContentDao;
import example.vo.JsonResult;
import example.vo.ReplyContent;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/replyContent/") // 이 페이지의 컨트롤러의 기준 URL
public class ReplyContentController {
	@Autowired
	ReplyContentDao replyContentDao;

	@RequestMapping(path="list")
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="30") int length
			) throws Exception {

		try {
			HashMap<String,Object> map = new HashMap<>();
			map.put("startIndex", (pageNo - 1) * length);
			map.put("length", length);

			return JsonResult.success(replyContentDao.selectList(map));
		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					
	}

	@RequestMapping(path="add")
	public Object add(ReplyContent replyContent) throws Exception {
		// 성공하든 실패하든 클라이언트에게 데이터를 보내야 한다.
		try {
			System.out.println(replyContent);
			replyContentDao.insert(replyContent);
			return JsonResult.success();
		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}						
	}
	
	@RequestMapping(path="add2")
	public Object add2(ReplyContent replyContent) throws Exception {
		// 성공하든 실패하든 클라이언트에게 데이터를 보내야 한다.
		try {
			System.out.println(replyContent);
			replyContentDao.insert(replyContent);
			return JsonResult.success();
		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}						
	}

}
