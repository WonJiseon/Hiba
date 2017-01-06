package example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.dao.CommunityCommentDao;
import example.vo.CommunityComment;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/communityComment/") // 이 페이지의 컨트롤러의 기준 URL
public class CommunityCommentController {

	@Autowired
	CommunityCommentDao communityCommentDao;
	
	
	@RequestMapping(path="list")
	public String list(
			@RequestParam(defaultValue="1")int pageNo,
			@RequestParam(defaultValue="5")int length,
			Model model) throws Exception {
		
		HashMap<String,Object> map = new HashMap<>();
		map.put("startIndex", (pageNo - 1) * length);
		map.put("length", length);

		List<CommunityComment> list = communityCommentDao.selectList(map);
		model.addAttribute("list", list);			
		
		return "communityComment/CommunityCommentList";	
	}
	
	@RequestMapping(path="add")
	public String add(
			CommunityComment communityComment) throws Exception {
		communityCommentDao.insert(communityComment);		
		return "redirect:list.do";
	}
	
	@RequestMapping(path="detail")
	public String detail(int no, Model model) throws Exception{
		CommunityComment communityComment = communityCommentDao.selectOne(no);
		model.addAttribute("communityComment", communityComment);
		return "communityComment/CommunityCommentDetail";
	}
	
	@RequestMapping(path="update")
	public String update(CommunityComment communityComment) throws Exception{
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("no", communityComment.getCommentNo());

/*		if (communityCommentDao.selectOneByPassword(paramMap) == null) {
			throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다.!");
		}*/
		communityCommentDao.update(communityComment);
		return "redirect:list.do";
	}
	
	@RequestMapping("communityCommentDelete")
	public String delete(int no) throws Exception {
		communityCommentDao.delete(no);    	
		return "redirect:list.do";
	}
	
	
	
	
	
	
	
	
	
	
}
