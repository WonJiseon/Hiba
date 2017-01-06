package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.MySchedule;

public interface MyScheduleDao {
  MySchedule selectOneByEmailAndPassword(Map<String, Object> paramMap);
  
  List<MySchedule> selectList(Map<String, Object> paramMap) throws Exception;
  MySchedule selectOneByScheduleNo(int groupscNo) throws Exception;   
  int countAll() throws Exception;
  List<MySchedule> selectListefgroupName(Map<String,Object> paramMap) throws Exception;
}
