package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.EventStatus;

public interface GroupScheduleStatusDao {
	
	EventStatus selectOneByScheduleNoAndMemberNoAndGroupNo(Map<String, Object> paramMap);
	
	List<EventStatus> selectList(Map<String, Object> paramMap) throws Exception;
  int update(EventStatus eventStatus) throws Exception;
  int insert(EventStatus eventStatus) throws Exception;
  int delete(int no) throws Exception;
}
