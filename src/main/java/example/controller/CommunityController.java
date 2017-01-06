package example.controller;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import example.dao.CommunityDao;
import example.service.CommunityService;
import example.vo.Community;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/community/") // 이 페이지의 컨트롤러의 기준 URL
public class CommunityController {
	
  @Autowired ServletContext sc;
  @Autowired CommunityService communityService;
  @Autowired CommunityDao communityDao;
	
	
	@RequestMapping(path="list")
	public String list(
			@RequestParam(defaultValue="1")int pageNo,
			@RequestParam(defaultValue="30")int length,
			Model model) throws Exception {
		
		List<Community> list = communityService.getCommunityList();
		model.addAttribute("list", list);			
		
		return "community/CommunityList";	
	}
	
	@RequestMapping("add")
	public String add(Community community) throws Exception {
		communityDao.insert(community);		
		return "redirect:list.do";
  }
	
	@RequestMapping("detail")
	public String detail(int no, Model model) throws Exception{
		Community community = communityService.getCommunity(no);
		model.addAttribute("community", community);
		return "community/CommunityDetail";
	}
	
	@RequestMapping("update")
	public String update(Community community) throws Exception{
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("no", community.getNo());

		if (communityDao.selectOneByPassword(paramMap) == null) {
			throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다.!");
		}
		communityDao.update(community);
		return "redirect:list.do";
	}
	
	@RequestMapping("delete")
	public String delete(int no) throws Exception {
		communityService.deleteCommunity(no);    	
		return "redirect:list.do";
	}
}
