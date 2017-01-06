package example.service.impl;

import java.io.File;
import java.util.HashMap;
import java.util.List;

import javax.servlet.ServletContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import example.dao.CommunityCommentDao;
import example.dao.CommunityDao;
import example.dao.CommunityFileDao;
import example.service.CommunityService;
import example.util.FileUploadUtil;
import example.vo.Community;
import example.vo.CommunityFile;
import net.coobird.thumbnailator.Thumbnails;

@Service 
public class DefaultCommnuityService implements CommunityService {
  @Autowired CommunityDao communityDao;
  @Autowired CommunityFileDao communityFileDao;
	@Autowired ServletContext sc; 
  
  
  public List<Community> getCommunityList() throws Exception {
    return communityDao.selectList();
  }
  
  
	public Community getCommunity(int no) throws Exception{
		Community community = communityDao.selectOne(no);		
		if (community == null)
			throw new Exception("해당 번호의 게시물이 존재하지 않습니다.");
		return communityDao.selectOne(no);
	}
  
	
  public void updateCommunity(Community community) throws Exception {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", community.getNo());
    /*paramMap.put("password", community.getPassword());
    
    if (communityDao.selectOneByPassword(paramMap) == null) {
      throw new Exception("해당 게시물이 없거나 암호가 일치하지 않습니다!");
    }*/
    communityDao.update(community);
  }
  
  
  
  
  public void deleteCommunity(int no) throws Exception {
    communityDao.delete(no);
  }
	
	
  
  public void insertCommunity(Community community, 
      MultipartFile file1,
      String uploadDir) throws Exception {
    communityDao.insert(community);
    System.out.println("사용자번호 : " + community.getUserNo());
    System.out.println("insert : " + community);
    
    String newFilename = null;
    System.out.println("커뮤니티-서비스 file1 :" + file1);
    
    if (file1 != null && !file1.isEmpty()) {
      newFilename = FileUploadUtil.getNewFilename(file1.getOriginalFilename());
      file1.transferTo(new File(uploadDir + newFilename));
      CommunityFile communityFile = new CommunityFile();
      communityFile.setFilename(newFilename);
      communityFile.setCommunityNo(community.getNo());
      communityFile.setFileUpMember(community.getUserNo());
      //communityFile.setCommunityNo(10200); //트랜잭션 테스트 용 
      System.out.println(communityFile);
      communityFileDao.insert(communityFile);
   	/* //원본이미지파일의 경로+파일명
      File image = new File(uploadDir + newFilename);
      //생성할 썸네일파일의 경로+썸네일파일명
      File thumbnail  = new File(uploadDir + "thumbnail" + newFilename);
      if (image.exists()) {
        thumbnail.getParentFile().mkdirs();
        Thumbnails.of(image).size(700, 700).outputFormat("jpg").toFile(thumbnail);
        System.out.println("썸네일 생성완료");
      } */
    }
    
  }
  
/*  public Community getCommunity(int no, String password) throws Exception {
    HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("password", password);
    return communityDao.selectOneByPassword(paramMap);
  }*/
  
  @Override
  public int getTotalPage(int pageSize) throws Exception {
    int countAll = communityDao.countAll();
    int totalPage = countAll / pageSize;
    if ((countAll % pageSize) > 0) {
      totalPage++;
    }
    return totalPage;
  }


	@Override
	public void updateViewCount(int no) throws Exception {
		communityDao.updateViewCount(no);
		
	}

}







