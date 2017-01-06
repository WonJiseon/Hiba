package example.service;

import java.util.List;
import java.util.Map;

import example.vo.MySchedule;

public interface MyScheduleService {
 
  
  List<MySchedule> getMyScheduleList(int pageNo, int length) throws Exception;
  MySchedule getScheduleByScheduleNo(int groupscNo) throws Exception;   
  int getTotalPage(int pageSize) throws Exception;
  List<MySchedule> getMyScheduleListEFGroupName(int pageNo, int length) throws Exception;
}
