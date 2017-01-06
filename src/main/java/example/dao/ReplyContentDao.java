package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.ReplyContent;

public interface ReplyContentDao {
	
	
	List<ReplyContent> selectList(Map<String, Object> paramMap) throws Exception;
  int insert(ReplyContent replyContent) throws Exception;
}
