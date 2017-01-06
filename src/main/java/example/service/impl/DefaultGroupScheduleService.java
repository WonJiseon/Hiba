package example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.dao.GroupMemberDao;
import example.dao.GroupScheduleDao;
import example.dao.GroupScheduleStatusDao;
import example.dao.PlaceDao;
import example.service.GroupScheduleService;
import example.vo.Event;

@Service 
public class DefaultGroupScheduleService implements GroupScheduleService{
	@Autowired GroupScheduleDao groupScheduleDao;
	@Autowired GroupScheduleStatusDao groupScheduleStautsDao;
	@Autowired GroupMemberDao groupMemberDao;
	@Autowired PlaceDao placeDao;
	
	public List<Event> getEventList() throws Exception {
		return groupScheduleDao.selectList();
				
	}
		
	public void insertEvent(Event event) throws Exception {
		
			System.out.println(event);
			groupScheduleDao.insert(event);
						
				
	}
	
	public Event getEvent(int no) throws Exception{		
		return groupScheduleDao.selectOne(no);		
	}
	

	public void updateEvent(Event event) throws Exception{
			groupScheduleDao.update(event);

	}
	
	public void deleteEvent(int no) throws Exception {
			groupScheduleDao.delete(no);
			placeDao.delete(no);
	}

	
}
