/*
 * 2016.11.15 수정사항
 * 단기 예보 , 장기 예보를 추가로 인해  기존의 importWeather 메소드는 importMidTemWeather로 변경
 * 단기 예보를 불러오는 importShortTermWeather 메소드 추가
 * 장기 예보를 불러오는 importLongTermWeather 메소드 추
 */
package example.service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.StringReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.HashMap;

import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;

import example.dao.PlaceDao;
import example.service.ScheduleWeatherService;
import example.vo.TermWeather;

@Service
public class DefaultScheduleWeatherService implements ScheduleWeatherService {
	@Autowired PlaceDao placeDao;

	/*
	 * importShortTermWeather() Method
	 * 기상청으로부터 단기예보(2일내)를 XML로 받아서 StringBuffer로 반환 
	 */
	public StringBuffer importShortTermWeather(String place) throws Exception {
		String str = null;

		HashMap<String, Integer> province = new HashMap<String, Integer>();
		province.put("서울", 11); province.put("인천", 28); province.put("경기", 41);
		province.put("강원", 42); province.put("충청북도", 43); province.put("충청남도", 44);
		province.put("대전", 30); province.put("세종", 133); province.put("전라북도", 45);
		province.put("광주", 29); province.put("전라남도", 46); province.put("대구", 27);
		province.put("경상북도", 47); province.put("부산", 26); province.put("부산", 26);
		province.put("울산", 31); province.put("경상남도", 48); province.put("제주", 50);

		HttpURLConnection conn = (HttpURLConnection)new URL(
				"http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone="
						+ province.get(place)+"00000000").openConnection();
		conn.connect();

		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
		StringBuffer st = new StringBuffer();
		String line;

		while((line = reader.readLine()) != null) {
			st.append(line);
		}
		return st;
	}

	/* importMidTermWeather() Method
	 * 기상청으로 부터 중기예보(2~10일) XML로 받아서 StringBuffer로 반환 
	 */
	public StringBuffer importMidTermWeather(String place) throws Exception {
		String str = null;

		HashMap<String, Integer> province = new HashMap<String, Integer>();
		province.put("서울", 109); province.put("인천", 109); province.put("경기", 109);
		province.put("강원", 105); province.put("충청북도", 131); province.put("충청남도", 133);
		province.put("대전", 133); province.put("세종", 133); province.put("전북", 146);
		province.put("광주", 156); province.put("전라남도", 156); province.put("대구", 143);
		province.put("경상북도", 143); province.put("부산", 159); province.put("부산", 159);
		province.put("울산", 159); province.put("경상남도", 159); province.put("제주", 184);
		province.put("전라북도", 146); 

		HttpURLConnection conn = (HttpURLConnection)new URL(
				"http://www.kma.go.kr/weather/forecast/mid-term-rss3.jsp?stnId="
						+ province.get(place)).openConnection();
		conn.connect();

		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
		StringBuffer st = new StringBuffer();
		String line;

		while((line = reader.readLine()) != null) {
			st.append(line);
		}
		return st;
	}


	/* importMidTermWeather() Method
	 * 기상청으로 부터 장기예보(3개월) XML로 받아서 StringBuffer로 반환 
	 */
	public StringBuffer importLongTermWeather(String place) throws Exception {

		String str = null;

		HashMap<String, Integer> province = new HashMap<String, Integer>();
		province.put("서울", 109); province.put("인천", 109); province.put("경기", 109);
		province.put("강원", 105); province.put("충청북도", 131); province.put("충청남도", 133);
		province.put("대전", 133); province.put("세종", 133); province.put("전북", 146);
		province.put("광주", 156); province.put("전라남도", 156); province.put("대구", 143);
		province.put("경상북도", 143); province.put("부산", 159); province.put("부산", 159);
		province.put("울산", 159); province.put("경상남도", 159); province.put("제주", 184);
		province.put("전라북도", 146); 

		HttpURLConnection conn = (HttpURLConnection)new URL(
				"http://www.kma.go.kr/repositary/xml/fct/mon/img/fct_mon3rss_108_20161024.xml"
				).openConnection();
		conn.connect();

		BufferedInputStream bis = new BufferedInputStream(conn.getInputStream());
		BufferedReader reader = new BufferedReader(new InputStreamReader(bis));
		StringBuffer st = new StringBuffer();
		String line;

		while((line = reader.readLine()) != null) {
			st.append(line);
		}
		return st;
	}


	/* 2016.11.01 수정사항: xmlToJSon 메서드 사용안함
  /* xmlToJson(StringBuffer st) Method
	 * XML데이터를 JSON으로 변환하고 String으로 반환 

  public String xmlToJson (StringBuffer st) throws Exception {
    JSONObject xmlJSONObj = XML.toJSONObject(st.toString());
    String resultJSON = xmlJSONObj.toString(4);
    System.out.println(resultJSON);

    return resultJSON;
  }
	 */

	/*
	 * getPlaceName(int value) Method
	 * placeDao에서 그룹번호를 통해 장소이름을 가져와서 날씨정보에 맞게 잘라낸다.
	 * ex) 서울특별시 강남구 -> 서울 
	 */
	public String getPlaceName(int value) throws Exception {
		String place = placeDao.selectPlace(value);
		String tempStr = place.substring(0,4);
		String resultStr = null;
		if (tempStr.equals("충청남도") || tempStr.equals("충청북도") || tempStr.equals("경상북도") || tempStr.equals("경상남도") || tempStr.equals("전라남도") || tempStr.equals("전라북도")) {
			resultStr = tempStr;
		} else {
			resultStr = place.substring(0,2);
		}

		return resultStr;
	}

	///rss/channel/item/description/body  

	/* 
	 *  calculateTime()
	 *  단기 예보 조회시 현재시간을 
	 *  구간별로 나누기 위한 메소드 
	 */
	public int calculateTime(int time) {
		int resultTime = 0;
		if(time >= 0 && time < 3) {
			resultTime = 24;
		} else if (time >= 3 && time < 6) {
			resultTime = 3;
		} else if (time >= 6 && time < 9) {
			resultTime = 6;
		} else if (time >= 9 && time < 12) {
			resultTime = 9;
		} else if (time >= 12 && time < 15) {
			resultTime = 12;
		} else if (time >= 15 && time < 18) {
			resultTime = 15;
		} else if (time >= 18 && time < 21) {
			resultTime = 18;
		} else if (time >= 21 && time < 24) {
			resultTime = 21;
		}
		
		return resultTime;
	}
	
	/*
	 * shortTermXmlParser()
	 * 단기예보 xml을 알맞게 parser하는 method
	 */
	public TermWeather shortTermXmlParser(String xml, String place, String date, int dday) throws Exception {
		TermWeather termWeather = new TermWeather();
		
		InputSource is = new InputSource(new StringReader(xml));
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);

		String time = date.split(" ")[1].split(":")[0];
		System.out.println("time" + time);
		int resultTime = calculateTime(Integer.parseInt(time));
		System.out.println("resultTime" + resultTime);
		
		Element element = document.getDocumentElement();
		Node channelNode = element.getElementsByTagName("channel").item(0);

		NodeList clist = channelNode.getChildNodes(); // channel의 자식 노드 리스트 
		NodeList ilist = clist.item(7).getChildNodes(); // item의 자식 노드 리스트 
		NodeList dlist = ilist.item(5).getChildNodes(); // description의 자식 노드 리스트 
		NodeList blist = dlist.item(3).getChildNodes(); // body의 자식노드 리스트 
		for(int i = 0; i < blist.getLength(); i++) {
//			System.out.println("["+i+"]"+blist.item(i).getNodeName());
			if(blist.item(i).getNodeName().equals("data")) {
				NodeList data = blist.item(i).getChildNodes();
				for(int j = 0; j < data.getLength(); j++) {
					if(Integer.parseInt(data.item(1).getChildNodes().item(0).getNodeValue()) == resultTime
							&& 
					Integer.parseInt(data.item(3).getChildNodes().item(0).getNodeValue()) == (dday-1)) {
//						System.out.println(data.item(1).getChildNodes().item(0).getNodeValue());
//						System.out.println(data.item(3).getChildNodes().item(0).getNodeValue());
						System.out.println("["+j+"]" + data.item(j).getNodeName());
						String hour = data.item(1).getTextContent();
						String day = data.item(3).getTextContent();
						String currentTemp = data.item(5).getTextContent();
						String minTemp = data.item(9).getTextContent();
						String maxTemp = data.item(7).getTextContent();
						String state =  data.item(15).getTextContent();
						
						System.out.println("hour" + hour);
						System.out.println("day" + day);
						System.out.println("currentTemp: " + currentTemp);
						System.out.println("minTemp: " + minTemp);
						System.out.println("maxTemp: " + maxTemp);
						System.out.println("state: " + state);
						
						termWeather.setMaxTemp(maxTemp);
						termWeather.setMinTemp(minTemp);
						termWeather.setCurrentTemp(currentTemp);
						termWeather.setState(state);
						termWeather.setCity(place);
					}
				}
			}
		}
		return termWeather;

	}
	/*
	 * 2016.11.15 수정사항 : 단기 장기 예보 추가로 인해 메소드 이름 변경 
	 * xmlParser -> midTermXmlParser
	 */
	public TermWeather midTermXmlParser(String xml, String date) throws Exception {
		TermWeather termWeather = new TermWeather();
		InputSource is = new InputSource(new StringReader(xml));
		Document document = DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(is);
		
		date = date.split(" ")[0];
		date += " 00:00";
		System.out.println(date);

		Element element = document.getDocumentElement();
		Node channelNode = element.getElementsByTagName("channel").item(0);

		NodeList clist = channelNode.getChildNodes();
		NodeList ilist = clist.item(7).getChildNodes();
		NodeList blist = ilist.item(5).getChildNodes();
		NodeList list = blist.item(3).getChildNodes();

		if (list.item(1).getNodeName().equals("location")) {
			System.out.println("test");
			NodeList list2 = list.item(1).getChildNodes();
			for(int i = 0; i < list2.getLength(); i++) {
				if(list2.item(i).getNodeName().equals("data")) {
					
			System.out.println("test1");
					NodeList list3 = list2.item(i).getChildNodes();
					//String content = list2.item(i).getTextContent();
					//System.out.println(i);
					//System.out.println("value" + content);
					String city = list2.item(3).getTextContent();
					termWeather.setCity(city);
					
			System.out.println("test1.5");
					for(int j = 0; j <list3.getLength(); j++) {
						if(list3.item(j).getNodeName().equals("tmEf")) {
							if(list3.item(j).getTextContent().equals(date)) {
								
			System.out.println("test2");
								String mode = list2.item(i).getChildNodes().item(1).getTextContent();
								String time = list2.item(i).getChildNodes().item(3).getTextContent();
								String state = list2.item(i).getChildNodes().item(5).getTextContent();
								String minTemp = list2.item(i).getChildNodes().item(7).getTextContent();
								String maxTemp = list2.item(i).getChildNodes().item(9).getTextContent();

								System.out.println("mode:"+mode);
								System.out.println("time:"+time);
								System.out.println("state:"+state);
								System.out.println("minTemp:"+minTemp);
								System.out.println("maxTemp:"+maxTemp);
								
								termWeather.setDate(time);
								termWeather.setMode(mode);
								termWeather.setMaxTemp(maxTemp);
								termWeather.setMinTemp(minTemp);
								termWeather.setState(state);

							}
						}
					}
				}
			}
		} else {
			termWeather = null;
		}
		return termWeather;
	}
	
}
