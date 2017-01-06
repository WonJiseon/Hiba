package example.controller.json;

import java.io.File;
import java.util.HashMap;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import example.dao.GroupFileDao;
import example.util.FileUploadUtil;
import example.vo.GroupFile;
import example.vo.JsonResult;
import example.vo.Member;
import net.coobird.thumbnailator.Thumbnails;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/upload/") // 이 페이지의 컨트롤러의 기준 URL
public class GroupFileController {
	@Autowired GroupFileDao groupFileDao;
	@Autowired ServletContext sc;
	
	
	
	@RequestMapping(path="add")
	public Object add(HttpSession session,
			MultipartFile file2,
			String uploadDir, GroupFile groupFile) throws Exception {
		uploadDir = sc.getRealPath("/upload") + "/";
		System.out.println("그룹파일 컨트롤러 호출");
		System.out.println("그룹파일 컨트롤러 file :" + file2);
		Member member = (Member)session.getAttribute("member");
		try {			
			String newFilename = null;			
	    if (file2 != null && !file2.isEmpty()) {
	      newFilename = FileUploadUtil.getNewFilename(file2.getOriginalFilename());
	      file2.transferTo(new File(uploadDir + newFilename));	     
	      groupFile.setMemberNo(member.getNo());		
	      groupFile.setFilename(newFilename);
	     
	      
	      HashMap<String, Object> paramMap = new HashMap<>();
				paramMap.put("no", groupFile.getNo()); //  그룹번호
				paramMap.put("memberNo", member.getNo()); // 회원번호
				System.out.println(paramMap);
				
				if (groupFileDao.selectOneByMemberNoAndGroupNo(paramMap) != null ) {
					System.out.println(groupFile);
					groupFileDao.update(groupFile);
				} else {
					groupFileDao.insert(groupFile);
					System.out.println(groupFile);
					
				}
				//원본이미지파일의 경로+파일명
	      File image = new File(uploadDir + newFilename);
	      //생성할 썸네일파일의 경로+썸네일파일명
	      File thumbnail  = new File(uploadDir + "thumbnail" + newFilename);
	      if (image.exists()) {
	        thumbnail.getParentFile().mkdirs();
	        Thumbnails.of(image).size(330, 180).outputFormat("jpg").toFile(thumbnail);
	        System.out.println("썸네일 생성완료");
	      }    
				
	    }
		
			
			return JsonResult.success();
			
		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail(e.getMessage());
		}						
	}
	
	@RequestMapping(path="update")
	public Object update(GroupFile groupFile) throws Exception{

		try {
			groupFileDao.update(groupFile);
			return JsonResult.success();
		} catch (Exception e) {
			
			return JsonResult.fail(e.getMessage());
		}					
		
	}
	
	
}