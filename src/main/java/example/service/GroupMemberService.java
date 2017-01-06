package example.service;

import java.util.List;

import example.vo.GroupMember;

public interface GroupMemberService  {
	
	List<GroupMember> getGroupMemberList() throws Exception;
	void  insertGroupMember(GroupMember groupMember) throws Exception;

}


