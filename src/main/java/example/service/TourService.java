package example.service;

import example.vo.Tour;

public interface TourService {
  StringBuffer importTourInfo(String place) throws Exception;
  String getPlaceName(int value) throws Exception;
  Tour xmlParser(String xml, String date)  throws Exception;
}
