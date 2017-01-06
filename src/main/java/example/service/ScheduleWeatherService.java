package example.service;

import example.vo.TermWeather;

public interface ScheduleWeatherService {
  StringBuffer importShortTermWeather(String place) throws Exception;
  StringBuffer importMidTermWeather(String place) throws Exception;
  StringBuffer importLongTermWeather(String place) throws Exception;
  //String xmlToJson(StringBuffer st) throws Exception; 2016.11.02 수정사항: xmlToJson 사용안함
  String getPlaceName(int value) throws Exception;
  TermWeather midTermXmlParser(String xml, String date) throws Exception;
  TermWeather shortTermXmlParser(String xml, String place, String date, int dday) throws Exception;
}
