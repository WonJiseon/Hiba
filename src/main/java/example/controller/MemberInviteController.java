package example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.dao.MemberDao;
import example.dao.MemberInviteDao;
import example.vo.MemberInvite;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/memberInvite/") // 이 페이지의 컨트롤러의 기준 URL
public class MemberInviteController {

	@Autowired
	MemberInviteDao memberInviteDao;
	
	
	@RequestMapping("list")
	public String list(
			@RequestParam(defaultValue="1")int pageNo,
			@RequestParam(defaultValue="100")int length,
			Model model) throws Exception {
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("startIndex", (pageNo - 1) * length);
		map.put("length", length);

		List<MemberInvite> list = memberInviteDao.selectList();
		model.addAttribute("list", list);			
		
		return "member/MemberList";	
	}
	
	@RequestMapping("add")
	public String add(MemberInvite memberInvite) throws Exception {
		memberInviteDao.insert(memberInvite);		
		return "redirect:list.do";
	}
	
		
	@RequestMapping("delete")
	public String delete(int no) throws Exception {
		memberInviteDao.delete(no);    	
		return "redirect:list.do";
	}
	
	
	
	
	
	
	
	
	
	
}
