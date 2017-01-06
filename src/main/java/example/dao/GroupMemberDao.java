package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.GroupMember;

public interface GroupMemberDao {
	
	
	List<GroupMember> selectList() throws Exception;
  int insert(GroupMember groupMember) throws Exception;
}
