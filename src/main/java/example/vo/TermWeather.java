/*
 *  2016.11.15 변경 및 추가사항 
 *  단기 장기 예보 추가를 위해 클래스명 변경
 *  MidTermWeather -> TermWeather
 *  단기 예보에서 현재 날씨를 알려주기 위해 currentTemp 추가 
 */

package example.vo;

public class TermWeather {
  protected String province;
  protected String city;
  protected String mode;
  protected String date;
  protected String maxTemp;
  protected String minTemp;
  protected String currentTemp;
  protected String state;
  public String getProvince() {
    return province;
  }
  public String getCurrentTemp() {
    return currentTemp;
  }
  public void setCurrentTemp(String currentTemp) {
    this.currentTemp = currentTemp;
  }
  public void setProvince(String province) {
    this.province = province;
  }
  public String getCity() {
    return city;
  }
  public void setCity(String city) {
    this.city = city;
  }
  public String getMode() {
    return mode;
  }
  public void setMode(String mode) {
    this.mode = mode;
  }
  public String getDate() {
    return date;
  }
  public void setDate(String date) {
    this.date = date;
  }
  public String getMaxTemp() {
    return maxTemp;
  }
  public void setMaxTemp(String maxTemp) {
    this.maxTemp = maxTemp;
  }
  public String getMinTemp() {
    return minTemp;
  }
  public void setMinTemp(String minTemp) {
    this.minTemp = minTemp;
  }
  public String getState() {
    return state;
  }
  public void setState(String state) {
    this.state = state;
  }



}
