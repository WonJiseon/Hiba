package example.controller.json;

import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import example.dao.CommunityCommentDao;
import example.dao.CommunityDao;
import example.service.CommunityService;
import example.vo.Community;
import example.vo.CommunityFile;
import example.vo.JsonResult;
import example.vo.Member;

@Controller 
@RequestMapping("/community/")
public class CommunityController {
  
  @Autowired CommunityDao communityDao;
	@Autowired CommunityService communityService;
	@Autowired ServletContext sc;
  
  @RequestMapping(path="list")
  public Object list(
      @RequestParam(defaultValue="1") int pageNo,
      @RequestParam(defaultValue="30") int length) throws Exception {
    
		try {
			List<Community> list = communityService.getCommunityList();
			return JsonResult.success(list);

		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					
	}
  
  @RequestMapping(path="add")
  public Object add(Community community,
  		HttpSession session,
			MultipartFile file1,
			String uploadDir) throws Exception {
  // 성공하든 실패하든 클라이언트에게 데이터를 보내야 한다.
   
  	Member member = (Member)session.getAttribute("member");
		uploadDir = sc.getRealPath("/upload") + "/";
		System.out.println("file1 --: " + file1);
	
		community.setUserNo(member.getNo());	
 		communityService.insertCommunity(community, file1, uploadDir);
 		try {
 			System.out.println("1");
      return JsonResult.success();
      
    } catch (Exception e) {
      return JsonResult.fail(e.getMessage());
    }
  }
  
  @RequestMapping(path="detail")
  public Object detail(int no) throws Exception {
    try {
      Community community = communityDao.selectOne(no);
      communityService.updateViewCount(no);
      
      
      if (community == null) 
        throw new Exception("해당 번호의 게시물이 존재하지 않습니다.");
      
      return JsonResult.success(community);
      
    } catch (Exception e) {
      return JsonResult.fail(e.getMessage());
    }
  }
  
  @RequestMapping(path="update")
  public Object update(Community community) throws Exception {
    try {
      HashMap<String,Object> paramMap = new HashMap<>();
      paramMap.put("no", community.getNo());

      communityDao.update(community);
      return JsonResult.success();
      
    } catch (Exception e) {
      return JsonResult.fail(e.getMessage());
    }
  }
  
  
  
   
  
  
  
  @RequestMapping(path="delete")
  public Object delete(int no, String password) throws Exception {
    try {
      HashMap<String,Object> paramMap = new HashMap<>();
      paramMap.put("no", no);

      communityDao.delete(no);
      return JsonResult.success();
      
    } catch (Exception e) {
      return JsonResult.fail(e.getMessage());
    }
  }
}







