package example.controller.json;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import example.dao.AlbumDao;
import example.service.AlbumService;
import example.util.FileUploadUtil;
import example.vo.Album;
import example.vo.JsonResult;
import example.vo.Member;
import example.vo.OrignAlbum;
import net.coobird.thumbnailator.Thumbnails;

@Controller // 페이지 컨트롤러에 붙이는 애노테이션 
@RequestMapping("/album/") // 이 페이지의 컨트롤러의 기준 URL
public class AlbumController {
	@Autowired AlbumDao albumDao;
	@Autowired ServletContext sc;
	@Autowired AlbumService albumService;

	@RequestMapping(path="list")
	public Object list(
			@RequestParam(defaultValue="1") int pageNo,
			@RequestParam(defaultValue="5") int length, 
			HttpSession session) throws Exception {

		try {
			List<Album> list = albumService.getAlbumList(pageNo, length);
			int totalPage = albumService.getTotalPage(length);

			HashMap<String,Object> data = new HashMap<>();
			data.put("list", list);
			data.put("totalPage", totalPage);
			data.put("pageNo", pageNo);
			data.put("length", length);

			System.out.println("JSON앨범컨트롤");
			return JsonResult.success(data);

		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}
	}				
	
	@RequestMapping(path="listAl")
	public Object listAl() throws Exception {
		try {
			List<OrignAlbum> list = albumService.getOrginAlbumList();
			return JsonResult.success(list);

		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					
	}
	
	
	@RequestMapping(path="add")
	public Object add(HttpSession session,
			MultipartFile[] file1,
			String uploadDir, Album album) throws Exception {
		System.out.println("그룹파일 컨트롤러 호출");
		System.out.println("그룹파일 컨트롤러 file :" + file1);
		Member member = (Member)session.getAttribute("member");
		uploadDir = sc.getRealPath("/upload") + "/";

		try {			
			String newFilename = null;
			for (int i = 0; i < file1.length; i++) {
				if (file1[i] != null && !file1[i].isEmpty()) {
					
				 
	         
					newFilename = FileUploadUtil.getNewFilename(file1[i].getOriginalFilename());
					file1[i].transferTo(new File(uploadDir + newFilename));	     
					album.setMemberNo(member.getNo());
					album.setFilename(newFilename);
					System.out.println(album);
					albumDao.insert(album);		
					
					 //원본이미지파일의 경로+파일명
	         File image = new File(uploadDir + newFilename);
	         //생성할 썸네일파일의 경로+썸네일파일명
	         File thumbnail  = new File(uploadDir + "thumbnail" + newFilename);
	         if (image.exists()) {
	           thumbnail.getParentFile().mkdirs();
	           Thumbnails.of(image).size(190, 150).outputFormat("jpg").toFile(thumbnail);
	           System.out.println("썸네일 생성완료");
	         } 
	          
										
				}
			}
			return JsonResult.success();

		} catch (Exception e) {
			e.printStackTrace();
			return JsonResult.fail(e.getMessage());
		}						
	}



	@RequestMapping(path="update")
	public Object update(Album album) throws Exception{

		try {
			albumDao.update(album);
			return JsonResult.success();
		} catch (Exception e) {

			return JsonResult.fail(e.getMessage());
		}					

	}

	@RequestMapping(path="detail")
	public Object detail(int album) throws Exception {
		try {
			albumDao.detail(album);
			return JsonResult.success();

		} catch (Exception e) {
			return JsonResult.fail(e.getMessage());
		}

	}
}	



