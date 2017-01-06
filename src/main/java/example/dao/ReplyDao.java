package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.Reply;

public interface ReplyDao {
	
	
	List<Reply> selectList(Map<String, Object> paramMap) throws Exception;
  int insert(Reply reply) throws Exception;
  int delete(int no) throws Exception;
}
