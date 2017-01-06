package example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.dao.GroupMemberDao;
import example.service.GroupMemberService;
import example.vo.GroupMember;

@Service
public class DefaultGroupMemberService implements GroupMemberService{
	@Autowired GroupMemberDao groupMemberDao;

	public List<GroupMember> getGroupMemberList() throws Exception {
			return groupMemberDao.selectList();			
	}
		
	public void insertGroupMember(GroupMember groupMember) throws Exception {
			groupMemberDao.insert(groupMember);
	}
	
}
