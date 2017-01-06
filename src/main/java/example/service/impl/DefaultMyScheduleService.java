package example.service.impl;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import example.dao.MyScheduleDao;
import example.service.MyScheduleService;
import example.vo.MySchedule;


@Service
public class DefaultMyScheduleService implements MyScheduleService{
  @Autowired MyScheduleDao myscheduleDao;

 public List<MySchedule> getMyScheduleList(int pageNo, int length) throws Exception {
   HashMap<String,Object> map = new HashMap<>();
   map.put("startIndex", (pageNo - 1) * length);
   map.put("length", length);
   
   return myscheduleDao.selectList(map);
 }

 public MySchedule getScheduleByScheduleNo(int groupscNo) throws Exception {
   return myscheduleDao.selectOneByScheduleNo(groupscNo);
 }
 
 @Override
public int getTotalPage(int pageSize) throws Exception {
   int countAll = myscheduleDao.countAll();
   int totalPage = countAll / pageSize;
   if ((countAll % pageSize) > 0) {
     totalPage++;
   }
   
   return totalPage;
}
 
 public List<MySchedule> getMyScheduleListEFGroupName(int pageNo, int length) throws Exception {
   HashMap<String,Object> map = new HashMap<>();
   map.put("startIndex", (pageNo - 1) * length);
   map.put("length", length);
   
   return myscheduleDao.selectListefgroupName(map);
 }
}
