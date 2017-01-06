package example.vo;

public class Weather {
  public static final String VERSION = "1";
  
  protected int no;
  protected String lat;
  protected String lon;
  protected String placeName;
  protected  int stnid;
  

  public static Weather version() {
    return new Weather();
  }


  public int getNo() {
    return no;
  }


  public void setNo(int no) {
    this.no = no;
  }


  public String getLat() {
    return lat;
  }


  public void setLat(String lat) {
    this.lat = lat;
  }


  public String getLon() {
    return lon;
  }


  public void setLon(String lon) {
    this.lon = lon;
  }


  public String getPlaceName() {
    return placeName;
  }


  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }


  public int getStnid() {
    return stnid;
  }


  public void setStnid(int stnid) {
    this.stnid = stnid;
  }


 
  
}
