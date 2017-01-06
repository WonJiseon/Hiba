package example.dao;

import example.vo.CommunityFile;
import java.util.List;
import java.util.Map;

public interface CommunityFileDao {
	
	CommunityFile selectOne(Map<String, Object> paramMap);
	
	List<CommunityFile> selectList() throws Exception;
	int insert(CommunityFile communityFile);
	int update(CommunityFile communityFile) throws Exception;
}



	
