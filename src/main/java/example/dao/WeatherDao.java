package example.dao;

import java.util.List;
import java.util.Map;

import example.vo.Weather;

public interface WeatherDao {
  List<Weather> selectList(Map<String,Object> paramMap) throws Exception;
}
