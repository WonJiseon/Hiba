package example.controller.json;

import java.util.HashMap;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import example.dao.CommunityCommentDao;
import example.vo.CommunityComment;
import example.vo.JsonResult;
import example.vo.Member;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/communityComment/") // 이 페이지의 컨트롤러의 기준 URL
public class CommunityCommentController {
	@Autowired
	CommunityCommentDao communityCommentDao;

	@RequestMapping(path="list")
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="100") int length) throws Exception {

		try {
			HashMap<String,Object> map = new HashMap<>();
			map.put("startIndex", (pageNo - 1) * length);
			map.put("length", length);
			return JsonResult.success(communityCommentDao.selectList(map));

		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}					
	}

	@RequestMapping(path="add")
	public Object add(CommunityComment communityComment, HttpSession session) throws Exception {
		// 성공하든 실패하든 클라이언트에게 데이터를 보내야 한다.
		try {
    /*  Member member = (Member)session.getAttribute("member");
      member.setNicknm(member.getNicknm());
*/
			communityCommentDao.insert(communityComment);
			return JsonResult.success();

		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}						
	}

	@RequestMapping(path="detail")
	public Object detail(int no) throws Exception{

		try {
			CommunityComment communityComment = communityCommentDao.selectOne(no);

			if (communityComment == null)
				throw new Exception("해당 번호의 게시물이 존재하지 않습니다.");
			return JsonResult.success(communityComment);

		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}		

	}


	@RequestMapping(path="update")
	public Object update(CommunityComment communityComment) throws Exception{

		try {
			/*HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("communitycommentNo", CommunityComment.getcommunityCommentNo());

			if (communityCommentDao.selectOneByPassword(paramMap) == null) {
				throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다.!");
			}*/
			communityCommentDao.update(communityComment);
			return JsonResult.success();
		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					

	}

	@RequestMapping(path="delete")
	public Object delete(int no) throws Exception {
		try {

			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("commentNo", no);
			/*paramMap.put("password", password);*/

/*			if (communityCommentDao.selectOneByPassword(paramMap) == null) {
				throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다.!");
			}*/
			communityCommentDao.delete(no);
			return JsonResult.success();
		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					
	}
}
