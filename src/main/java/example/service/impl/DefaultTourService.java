//package example.service.impl;
//
//import java.io.BufferedInputStream;
//import java.io.BufferedReader;
//import java.io.InputStreamReader;
//import java.io.StringReader;
//import java.net.HttpURLConnection;
//import java.net.URL;
//import java.util.HashMap;
//
//import javax.xml.parsers.DocumentBuilderFactory;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.w3c.dom.Document;
//import org.w3c.dom.Element;
//import org.w3c.dom.Node;
//import org.w3c.dom.NodeList;
//import org.xml.sax.InputSource;
//
//import example.dao.PlaceDao;
//import example.service.TourService;
//import example.vo.Tour;
//
//public class DefaultTourService implements TourService {
//  @Autowired PlaceDao placeDao;
//
//
//  /* importTourInfo() Method
//  사이트로부터 관광정보를 XML로 받아서 StringBuffer로 반환*/
//  public StringBuffer importTourInfo(String place) throws Exception {
//    String str = null;
//
//    HashMap<String, Integer> province = new HashMap<>();
//    province.put("서울", 1);  province.put("인천", 2);  province.put("대전", 3);
//    province.put("대구", 4);  province.put("광주", 5);  province.put("부산", 6);
//    province.put("울산", 7);  province.put("세종특별자치시", 8);  province.put("경기도", 31);
//    province.put("강원도", 32);  province.put("충청북도", 33);  province.put("충청남도", 34);
//    province.put("경상북도", 35);  province.put("경상남도", 36);  province.put("전라북도", 37);
//    province.put("전라남도", 38);  province.put("제주도", 39);
//
//    HttpURLConnection conn = (HttpURLConnection)new URL(
//        "http://api.visitkorea.or.kr/openapi/service/rest/KorService/areaCode="
//            + province.get(place) +
//        "?ServiceKey=OdaqOYG5yh3hxY4TPYcvp3AMSAUwYjRdEYSibyUuD3PYYjK%2BR%2FfyUSnEyCas2KQDZmHtFIZ8Ir1gC4XIddpJtA%3D%3D&numOfRows=10&pageNo=1&MobileOS=ETC&MobileApp=TestApp").openConnection();
//    conn.connect();
//
//    BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
//    BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
//    StringBuffer st = new StringBuffer();
//    String line;
//
//    while((line = reader.readLine()) != null) {
//      st.append(line);
//    }
//    return st;
//  }
//
//  public String getPlaceName(int value) throws Exception {
//    String place = placeDao.selectPlace(value);
//    String tempStr = place.substring(0,4);
//    String resultStr = null;
//    if (tempStr.equals("충청남도") || tempStr.equals("충청북도") || tempStr.equals("경상북도") || tempStr.equals("경상남도") || tempStr.equals("전라남도") || tempStr.equals("전라북도")) {
//      resultStr = tempStr;
//    } else {
//      resultStr = place.substring(0,2);
//    }
//    return resultStr;
//  }
//  
//  public Tour xmlParser(String xml, String date) throws Exception {
//    Tour tour = new Tour();
//    InputSource is = new InputSource(new StringReader(xml));
//    Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
//   
//    Element element = document.getDocumentElement();
//    Node channelNode = element.getElementsByTagName("channel").item(0);
//    
//    NodeList clist = channelNode.getChildNodes();
//    NodeList ilist = clist.item(7).getChildNodes();
//    NodeList blist = ilist.item(5).getChildNodes();
//    NodeList list = blist.item(3).getChildNodes();
//    
//      
//    }
//  }
//}
