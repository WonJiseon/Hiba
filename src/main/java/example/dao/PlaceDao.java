package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.Place;

public interface PlaceDao {
	
	Place selectOneByEmailAndPassword(Map<String, Object> paramMap);
	
	List<Place> selectList(Map<String, Object> paramMap) throws Exception;
	Place selectOne(int no) throws Exception; 
	String selectPlace(int value) throws Exception;  
	Place selectOneByPassword(Map<String, Object> paramMap) throws Exception;    
  int insert(Place place) throws Exception;
  int update(Place place) throws Exception;
  int delete(int no) throws Exception;
}
