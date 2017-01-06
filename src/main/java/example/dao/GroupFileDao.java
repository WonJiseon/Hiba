package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.GroupFile;

public interface GroupFileDao {
	
	GroupFile selectOneByMemberNoAndGroupNo(Map<String, Object> paramMap);
	
	List<GroupFile> selectList() throws Exception;
	int insert(GroupFile groupFile);
	int update(GroupFile groupFile) throws Exception;
	
}
