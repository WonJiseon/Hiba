package example.controller.json;

import java.io.File;
import java.util.HashMap;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import example.dao.MemberDao;
import example.util.FileUploadUtil;
import example.vo.JsonResult;
import example.vo.Member;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/member/") // 이 페이지의 컨트롤러의 기준 URL
public class MemberController {
	@Autowired MemberDao memberDao;
	@Autowired ServletContext sc;
	
	

	@RequestMapping(path="countAllGroup")
	public Object countAllGroup() throws Exception {		
		int groupCount = memberDao.countAllGroup();
		return JsonResult.success(groupCount);		
	}
	
	@RequestMapping(path="countAllSc")
	public Object countAllSc() throws Exception {	
		int scCount = memberDao.countAllSc();
		return JsonResult.success(scCount);		
	}
	@RequestMapping(path="countAllMember")
	public Object countAllMember() throws Exception {		
		int memberCount = memberDao.countAllMember();
		return JsonResult.success(memberCount);		
	}
	
	@RequestMapping(path="list")
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="30") int length) throws Exception {

		try {
			HashMap<String,Object> map = new HashMap<>();
			map.put("startIndex", (pageNo - 1) * length);
			map.put("length", length);
			return JsonResult.success(memberDao.selectList(map));
			
		} catch (Exception e) {
	
			return JsonResult.fail(e.getMessage());
		}					
	}
		
	@RequestMapping(path="add")
	public Object add(Member member,
		MultipartFile file,
		String uploadDir) throws Exception {
		
		System.out.println("member : " + member); 
		System.out.println("file : " + file); 
		uploadDir = sc.getRealPath("/upload") + "/";
		try {
			String newFilename = null;			
	    if (file != null && !file.isEmpty()) {
	      newFilename = FileUploadUtil.getNewFilename(file.getOriginalFilename());
	      file.transferTo(new File(uploadDir + newFilename));
	      member.setFilename(newFilename);
	    }
	    
	    memberDao.insert(member);
	    return JsonResult.success();
	    
		} catch (Exception e) {
	      
			return JsonResult.fail(e.getMessage());
		}						
	}
			
	
	@RequestMapping(path="detail")
	public Object detail(int no) throws Exception{
		
		try {
			Member Member = memberDao.selectOne(no);
			
			if (Member == null)
				throw new Exception("해당 번호의 게시물이 존재하지 않습니다.");
			return JsonResult.success(Member);
			
		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}		
		
	}
	
	
	@RequestMapping(path="update")
	public Object update(Member Member) throws Exception{

		try {
			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("no", Member.getNo());
			paramMap.put("password", Member.getPassword());

			if (memberDao.selectOneByPassword(paramMap) == null) {
				throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다.!");
			}
			memberDao.update(Member);
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
      paramMap.put("password", password);

			if (memberDao.selectOneByPassword(paramMap) == null) {
				throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다.!");
			}
			memberDao.delete(no);
			return JsonResult.success();
		} catch (Exception e) {
			
			return JsonResult.fail(e.getMessage());
		}					
	}
	
	
	
	
}
