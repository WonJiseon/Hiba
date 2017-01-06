package example.controller;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.dao.MakeGroupDao;
import example.vo.MakeGroup;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/makegroup/") // 이 페이지의 컨트롤러의 기준 URL
public class MakeGroupController {

	@Autowired
	MakeGroupDao makeGroupDao;
	
	@RequestMapping("list")
	public String list(Model model) throws Exception {
		
		List<MakeGroup> list = makeGroupDao.selectList();
		model.addAttribute("list", list);			
		
		return "group/MakeGroupList";	
	}
	
	@RequestMapping("add")
	public String add(MakeGroup makeGroup) throws Exception {
		makeGroupDao.insert(makeGroup);		
		return "redirect:list.do";
	}
	
	@RequestMapping("detail")
	public String detail(int no, Model model) throws Exception{
		MakeGroup makeGroup = makeGroupDao.selectOne(no);
		model.addAttribute("makeGroup", makeGroup);
		return "group/MakeGroupDetail";
	}
	
	@RequestMapping("update")
	public String update(MakeGroup makeGroup) throws Exception{
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("no", makeGroup.getNo());

		if (makeGroupDao.selectOneByPassword(paramMap) == null) {
			throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다.!");
		}
		makeGroupDao.update(makeGroup);
		return "redirect:list.do";
	}
	
	@RequestMapping("delete")
	public String delete(int no) throws Exception {
		makeGroupDao.delete(no);    	
		return "redirect:list.do";
	}
	
	
	
	
	
	
	
	
	
	
}
