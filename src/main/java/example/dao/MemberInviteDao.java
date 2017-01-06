package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.MemberInvite;

public interface MemberInviteDao {
	
	MemberInvite selectOneByGroupNoAndEmail(Map<String, Object> paramMap);		
	MemberInvite selectOneByNameAndNumber(Map<String, Object> paramMap);
	List<MemberInvite> selectList() throws Exception;	
	List<MemberInvite> orginSelectList() throws Exception;
	MemberInvite selectOne(int no) throws Exception;

	int insert(MemberInvite memberInvite) throws Exception;
  int delete(int no) throws Exception;
  int update(MemberInvite memberInvite) throws Exception;
  int update2(MemberInvite memberInvite) throws Exception;
  int countAll() throws Exception;
  
}
