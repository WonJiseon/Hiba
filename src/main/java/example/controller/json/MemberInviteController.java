package example.controller.json;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.dao.GroupMemberDao;
import example.dao.MemberInviteDao;
import example.dao.ReplyContentDao;
import example.service.GroupMemberService;
import example.service.MemberInviteService;
import example.vo.GroupMember;
import example.vo.JsonResult;
import example.vo.MemberInvite;


@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/memberInvite/") // 이 페이지의 컨트롤러의 기준 URL
public class MemberInviteController {
	@Autowired MemberInviteService memberInviteService;
	@Autowired ReplyContentDao replyContentDao;
	@Autowired GroupMemberDao groupMemberDao;
	@Autowired GroupMemberService groupMemberService;
	@Autowired MemberInviteDao memberInviteDao;

	@RequestMapping(path="list")
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="5") int length) throws Exception {

		try {
			List<MemberInvite> list = memberInviteService.getMemberInvitetList();

			return JsonResult.success(list);

		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					
	}

	@RequestMapping(path="orginlist")
	public Object orginOList() throws Exception {

		try {
			return JsonResult.success(memberInviteDao.orginSelectList());

		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					
	}


	@RequestMapping(path="add")
	public Object add(MemberInvite memberInvite) throws Exception{
		try {
			if (memberInviteService.getMemberInviteGroupNoAndEmail(memberInvite.getGroupNo(), memberInvite.getInviteEmail() ) != null ) {
			
				throw new Exception("이미 초대된 회원 이거나 일치하는 회원이 없습니다.");
			}
			memberInviteService.insertMemberInvite(memberInvite);
			System.out.println(memberInvite);
			return JsonResult.success();
		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}						
	}

	@RequestMapping(path="update")
	public Object update(MemberInvite memberInvite) throws Exception{

		try {
			if (memberInviteService.getMemberInviteNameAndNumber(memberInvite.getNo(), memberInvite.getGroupNo()) == null) {
				throw new Exception("해당 회원이 없습니다.");
			}			
			System.out.println(memberInvite);
			memberInviteService.updateMemberInvite(memberInvite);
			return JsonResult.success();
		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					

	}

	@RequestMapping(path="update2")
	public Object update2(MemberInvite memberInvite) throws Exception{
		try {
			System.out.println(memberInvite);
			memberInviteService.updateMemberInvite2(memberInvite);
			if (memberInvite.isStatus() == true) {
				GroupMember groupMember = new GroupMember();
				groupMember.setGroupNo(memberInvite.getGroupNo());
				groupMember.setNo(memberInvite.getMemberNo());
				System.out.println(groupMember);
				groupMemberDao.insert(groupMember);

			}
			return JsonResult.success();
		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					

	}

	@RequestMapping(path="delete")
	public Object delete(int no) throws Exception {		
		try {

			if (memberInviteService.getMemberInvite(no) == null) {
				throw new Exception("거절 실패입니다");
			}

			memberInviteService.deleteMemberInvite(no);
			return JsonResult.success();

		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}	
	}




}
