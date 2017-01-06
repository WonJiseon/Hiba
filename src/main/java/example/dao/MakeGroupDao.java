package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.MakeGroup;

public interface MakeGroupDao {
	
	MakeGroup selectOneByEmailAndPassword(Map<String, Object> paramMap);
	
	List<MakeGroup> selectList() throws Exception;
	MakeGroup selectOne(int no) throws Exception;  
	MakeGroup selectOneByPassword(Map<String, Object> paramMap) throws Exception;    
  int insert(MakeGroup makeGroup) throws Exception;
  int update(MakeGroup makeGroup) throws Exception;
  int delete(int no) throws Exception;
}
