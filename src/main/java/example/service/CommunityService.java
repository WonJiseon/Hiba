package example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import example.vo.Community;

public interface CommunityService {
	
  List<Community> getCommunityList() throws Exception;
  Community getCommunity(int no) throws Exception;
  void updateCommunity(Community community) throws Exception;
  void insertCommunity(Community community, MultipartFile file1, String uploadDir) throws Exception;
  void deleteCommunity(int no) throws Exception;
  
  void updateViewCount(int no) throws Exception;
  
  int getTotalPage(int pageSize) throws Exception;
  
}







