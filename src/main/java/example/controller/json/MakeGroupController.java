package example.controller.json;

import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import example.dao.GroupFileDao;
import example.dao.GroupMemberDao;
import example.dao.MakeGroupDao;
import example.dao.MemberInviteDao;
import example.dao.ReplyContentDao;
import example.dao.ReplyDao;
import example.service.MakeGroupService;
import example.vo.GroupMember;
import example.vo.JsonResult;
import example.vo.MakeGroup;
import example.vo.Member;
import example.vo.MemberInvite;
import example.vo.Reply;
import example.vo.ReplyContent;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/group/") // 이 페이지의 컨트롤러의 기준 URL
public class MakeGroupController {
	@Autowired MakeGroupDao makeGroupDao;
	@Autowired MemberInviteDao memberInviteDao;
	@Autowired ReplyDao replyDao;
	@Autowired ReplyContentDao replyContentDao;
	@Autowired GroupMemberDao groupMemberDao;
	@Autowired MakeGroupService makeGroupService;
	@Autowired GroupFileDao groupFileDao;
	@Autowired ServletContext sc;

	@RequestMapping(path="list")
	public Object list() throws Exception {
		try {
			List<MakeGroup> list = makeGroupService.getMakeGroupList();
			return JsonResult.success(list);

		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					
	}
	
	@RequestMapping(path="add")	
	public Object add(MakeGroup makeGroup,	
			HttpSession session,
			MultipartFile file1,
			String uploadDir) throws Exception {
		// 성공하든 실패하든 클라이언트에게 데이터를 보내야 한다.
		Member member = (Member)session.getAttribute("member");
		uploadDir = sc.getRealPath("/upload") + "/";
		
		System.out.println("file1 --: " + file1);
		
		try {
			
			makeGroup.setEmail(member.getEmail());				
			makeGroup.setName(member.getName());		
			makeGroup.setMemberNumber(member.getNo());		
			makeGroupService.insertMakeGroup(makeGroup, file1, uploadDir);
			
			MemberInvite memberInvite = new MemberInvite();
			// 그룹 생성시 생성자 자동추가
			memberInvite.setGroupNo(makeGroup.getNo());
			memberInvite.setName(makeGroup.getName());
			memberInvite.setInviteName(makeGroup.getName());
			memberInvite.setInviteEmail(member.getEmail());
			memberInvite.setStatus(true);
			System.out.println(memberInvite);			
			memberInviteDao.insert(memberInvite);

			// 그룹 생성시 그룹 댓글 생성
			Reply reply = new Reply();
			reply.setGroupNo(makeGroup.getNo());
			reply.setMemberNo(member.getNo());
			reply.setName(member.getName());
			System.out.println(reply);		
			replyDao.insert(reply);

			// 그룹 댓글 생성시 댓글 콘텐츠 생성
			ReplyContent replyContent = new ReplyContent();			
			replyContent.setGroupNo(reply.getNo()); //rgno
			replyContent.setMemberNo(member.getNo()); //mno
			replyContent.setName(member.getName());
			replyContent.setContent("그룹을 만들었습니다.");
			System.out.println(replyContent);		
			replyContentDao.insert(replyContent);


			GroupMember groupMember = new GroupMember();
			groupMember.setGroupNo(makeGroup.getNo());
			groupMember.setNo(member.getNo());
			groupMemberDao.insert(groupMember);		


			return JsonResult.success();

		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}						
	}


	@RequestMapping(path="detail")
	public Object detail(int no) throws Exception{

		try {
			MakeGroup makeGroup = makeGroupService.getMakeGroup(no);

			if (makeGroup == null)
				throw new Exception("해당 번호의 게시물이 존재하지 않습니다.");
			return JsonResult.success(makeGroup);

		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}		

	}


	@RequestMapping(path="update")
	public Object update(MakeGroup makeGroup) throws Exception{

		try {

			if (makeGroupService.getMakeGroup(makeGroup.getNo()) == null) {
				throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다.!");
			}
			makeGroupService.updateMakeGroup(makeGroup);
			return JsonResult.success();
		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					

	}

	@RequestMapping(path="delete")
	public Object delete(int no) throws Exception {
		try {
			if (makeGroupService.getMakeGroup(no) == null) {
				throw new Exception("삭제 실패입니다");
			}
			makeGroupService.deleteMakeGroup(no);
			return JsonResult.success();

		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}	
	}

}
