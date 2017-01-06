package example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.dao.ReplyContentDao;
import example.vo.ReplyContent;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/replyContent/") // 이 페이지의 컨트롤러의 기준 URL
public class ReplyContentController {

	@Autowired
	ReplyContentDao replyContentDao;
	
	@RequestMapping("list")
	public String list(
			@RequestParam(defaultValue="1")int pageNo,
			@RequestParam(defaultValue="5")int length,
			Model model) throws Exception {
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("startIndex", (pageNo - 1) * length);
		map.put("length", length);

		List<ReplyContent> list = replyContentDao.selectList(map);
		model.addAttribute("list", list);			
		
		return "reply/ReplyList";	
	}
	
	@RequestMapping("add")
	public String add(ReplyContent replyContent) throws Exception {
		replyContentDao.insert(replyContent);		
		return "redirect:list.do";
	}

	
	
	
	
	
	
	
	
	
	
}
