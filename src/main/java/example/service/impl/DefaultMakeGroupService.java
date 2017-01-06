package example.service.impl;

import java.awt.image.BufferedImage;
import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.imageio.ImageIO;
import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import example.dao.GroupFileDao;
import example.dao.MakeGroupDao;
import example.service.MakeGroupService;
import example.util.FileUploadUtil;
import example.vo.GroupFile;
import example.vo.MakeGroup;
import net.coobird.thumbnailator.Thumbnails;


@Service 
public class DefaultMakeGroupService implements MakeGroupService{
	@Autowired MakeGroupDao makeGroupDao;
	@Autowired GroupFileDao groupFileDao;
	@Autowired ServletContext sc;
	
	public List<MakeGroup> getMakeGroupList() throws Exception {
		return makeGroupDao.selectList();
	}


	public MakeGroup getMakeGroup(int no) throws Exception{
		MakeGroup makeGroup = makeGroupDao.selectOne(no);		
		if (makeGroup == null)
			throw new Exception("해당 번호의 게시물이 존재하지 않습니다.");
		return makeGroupDao.selectOne(no);

	}


	public void updateMakeGroup(MakeGroup makeGroup) throws Exception{
		HashMap<String,Object> paramMap = new HashMap<>();
		paramMap.put("no", makeGroup.getNo());

		if (makeGroupDao.selectOneByPassword(paramMap) == null) {
			throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다.!");
		}
		makeGroupDao.update(makeGroup);

	}

	public void deleteMakeGroup(int no) throws Exception {
		if (makeGroupDao.delete(no) == 0) {
			throw new Exception("삭제 실패입니다");
		}
		makeGroupDao.delete(no);
	}


	@Override
	public void insertMakeGroup(MakeGroup makeGroup, MultipartFile file1, String uploadDir) throws Exception {
		makeGroupDao.insert(makeGroup);
		System.out.println(makeGroup);
		String newFilename = null;

		
		System.out.println("---서비스 file1 :" + file1);
		if (file1 != null && !file1.isEmpty()) {
			newFilename = FileUploadUtil.getNewFilename(file1.getOriginalFilename());			
			file1.transferTo(new File(uploadDir + newFilename));
			GroupFile groupFile = new GroupFile();
			groupFile.setFilename(newFilename);
			groupFile.setNo(makeGroup.getNo());
			groupFile.setMemberNo(makeGroup.getMemberNumber());
			System.out.println(groupFile);
			groupFileDao.insert(groupFile);
			
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

	}
}
