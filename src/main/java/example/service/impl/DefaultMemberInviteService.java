package example.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.dao.GroupMemberDao;
import example.dao.MemberInviteDao;
import example.service.MemberInviteService;
import example.vo.GroupMember;
import example.vo.MemberInvite;


@Service 
public class DefaultMemberInviteService implements MemberInviteService{
	@Autowired MemberInviteDao memberInviteDao;
	@Autowired GroupMemberDao groupMemberDao;

	public List<MemberInvite> getMemberInvitetList() throws Exception {
			return memberInviteDao.selectList();
	}

	
	public void insertMemberInvite(MemberInvite memberInvite) throws Exception {
			HashMap<String, Object> paramMap = new HashMap<>();
			paramMap.put("groupNo", memberInvite.getGroupNo()); //  그룹번호
			paramMap.put("inviteEmail", memberInvite.getInviteEmail()); // 초대회원 이메일
			System.out.println(paramMap);
			
			if (memberInviteDao.selectOneByGroupNoAndEmail(paramMap) != null ) {
				throw new Exception("이미 초대된 회원 이거나 일치하는 회원이 없습니다.");
			}
			memberInviteDao.insert(memberInvite);
	}

	public void updateMemberInvite(MemberInvite memberInvite) throws Exception{

			HashMap<String,Object> paramMap = new HashMap<>();
			paramMap.put("no", memberInvite.getNo());
			paramMap.put("groupNo", memberInvite.getGroupNo());
			System.out.println(memberInvite);
			System.out.println("-----------------------");
			if (memberInviteDao.selectOneByNameAndNumber(paramMap) == null) {
				throw new Exception("해당 회원이 없습니다.");
			}			
			System.out.println(memberInvite);
			memberInviteDao.update(memberInvite);

	}

	public void updateMemberInvite2(MemberInvite memberInvite) throws Exception{
			System.out.println(memberInvite);
			memberInviteDao.update2(memberInvite);

	}

	public void deleteMemberInvite(int no) throws Exception {		

			if (memberInviteDao.delete(no) < 0) {
				throw new Exception("거절 실패입니다");
			}

			memberInviteDao.delete(no);
	}


	@Override
	public MemberInvite getMemberInviteGroupNoAndEmail(int groupNo, String inviteEmail) {
		HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("groupNo", groupNo);
    paramMap.put("inviteEmail", inviteEmail);
    return memberInviteDao.selectOneByGroupNoAndEmail(paramMap);
	}


	@Override
	public MemberInvite getMemberInviteNameAndNumber(int no, int groupNo) {
		HashMap<String,Object> paramMap = new HashMap<>();
    paramMap.put("no", no);
    paramMap.put("groupNo", groupNo);
    return memberInviteDao.selectOneByNameAndNumber(paramMap);
	}



	@Override
	public MemberInvite getMemberInvite(int no) throws Exception {
		return memberInviteDao.selectOne(no);
	}




}
