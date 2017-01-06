package example.service;

import java.util.List;

import example.vo.MemberInvite;

public interface MemberInviteService {
	
	List<MemberInvite> getMemberInvitetList() throws Exception;	
	void insertMemberInvite(MemberInvite memberInvite) throws Exception;
	
	MemberInvite getMemberInviteGroupNoAndEmail(int groupNo, String inviteEmail);		
	MemberInvite getMemberInviteNameAndNumber(int no, int groupNo);
  void updateMemberInvite(MemberInvite memberInvite) throws Exception;
  void updateMemberInvite2(MemberInvite memberInvite) throws Exception;
  void deleteMemberInvite(int no) throws Exception;
  MemberInvite getMemberInvite(int no) throws Exception;
}
