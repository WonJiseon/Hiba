package example.controller.json;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import example.service.GroupMemberService;
import example.vo.GroupMember;
import example.vo.JsonResult;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/groupMember/") // 이 페이지의 컨트롤러의 기준 URL
public class GroupMemberController {
	@Autowired
	GroupMemberService groupMemberService;
	
	@RequestMapping(path="list")
	public Object list() throws Exception {
		try {
			return JsonResult.success(groupMemberService.getGroupMemberList());	
		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}					
	}
		
	@RequestMapping(path="add")
	public Object add(GroupMember groupMember) throws Exception {
		// 성공하든 실패하든 클라이언트에게 데이터를 보내야 한다.
		try {
			groupMemberService.insertGroupMember(groupMember);
			return JsonResult.success();
		} catch (Exception e) {
			
			return JsonResult.fail(e.getMessage());
		}						
	}
	
	


	
	
}
