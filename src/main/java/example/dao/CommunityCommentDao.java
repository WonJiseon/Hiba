package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.CommunityComment;

public interface CommunityCommentDao {
	
	CommunityComment selectOneByEmailAndPassword(Map<String, Object> paramMap);
	
	List<CommunityComment> selectList(Map<String, Object> paramMap) throws Exception;
	CommunityComment selectOne(int commentNo) throws Exception;  
/*  CommunityComment selectOneByPassword(Map<String, Object> paramMap) throws Exception;   */ 
  int insert(CommunityComment communityComment) throws Exception;
  int update(CommunityComment communityComment) throws Exception;
  int delete(int commentNo) throws Exception;
  int commentCount(int no) throws Exception;
}
