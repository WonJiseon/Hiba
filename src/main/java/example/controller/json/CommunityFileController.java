package example.controller.json;

import java.io.File;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import example.dao.CommunityFileDao;
import example.util.FileUploadUtil;
import example.vo.CommunityFile;
import example.vo.JsonResult;
import example.vo.Member;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/communityupload/") // 이 페이지의 컨트롤러의 기준 URL
public class CommunityFileController {
	@Autowired CommunityFileDao communityFileDao;
	@Autowired ServletContext sc;
	
	
	
	@RequestMapping(path="add")
	public Object add(HttpSession session,
			MultipartFile file2,
			String uploadDir, CommunityFile communityFile) throws Exception {
		uploadDir = sc.getRealPath("/upload") + "/";
		System.out.println("커뮤니티파일 컨트롤러 호출");
		System.out.println("커뮤니티파일 컨트롤러 file :" + file2);
		Member member = (Member)session.getAttribute("member");
		try {			
			String newFilename = null;			
	    if (file2 != null && !file2.isEmpty()) {
	      newFilename = FileUploadUtil.getNewFilename(file2.getOriginalFilename());
	      file2.transferTo(new File(uploadDir + newFilename));	     
	      communityFile.setFileUpMember(member.getNo());		
	      communityFile.setFilename(newFilename);
	     
	      
	      HashMap<String, Object> paramMap = new HashMap<>();
				paramMap.put("communityNo", communityFile.getNo());    //  게시판번호
				paramMap.put("fileUpMember", member.getNo());          // 회원번호
				System.out.println(paramMap);
				
				if (communityFileDao.selectOne(paramMap) != null ) {
					System.out.println(communityFile);
					communityFileDao.update(communityFile);
				} else {
					communityFileDao.insert(communityFile);
					System.out.println(communityFile);
					
				}
	      
	    }
		
			
			return JsonResult.success();
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail(e.getMessage());
		}						
	}
	
	@RequestMapping(path="update")
	public Object update(CommunityFile communityFile) throws Exception{

		try {
			communityFileDao.update(communityFile);
			return JsonResult.success();
		} catch (Exception e) {
			
			return JsonResult.fail(e.getMessage());
		}					
		
	}
	
	
}