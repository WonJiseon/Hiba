package example.service;

import java.util.List;

import example.vo.Event;

public interface GroupScheduleService {
	
	
	List<Event> getEventList() throws Exception;
	void insertEvent(Event event) throws Exception;
	Event getEvent(int no) throws Exception;  
  void updateEvent(Event event) throws Exception;
  void deleteEvent(int no) throws Exception;
  
}
