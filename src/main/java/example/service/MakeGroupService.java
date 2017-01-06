package example.service;

import java.util.List;

import org.springframework.web.multipart.MultipartFile;

import example.vo.MakeGroup;

public interface MakeGroupService {
	
	
	List<MakeGroup> getMakeGroupList() throws Exception;
	MakeGroup getMakeGroup(int no) throws Exception;  
  void updateMakeGroup(MakeGroup makeGroup) throws Exception;
  void insertMakeGroup(MakeGroup makeGroup, MultipartFile file1, String uploadDir) throws Exception;
  void deleteMakeGroup(int no) throws Exception;
  
}
