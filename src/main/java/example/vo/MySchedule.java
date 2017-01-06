package example.vo;

import java.io.Serializable;

public class MySchedule implements Serializable {
  private static final long serialVersionUID = 1L;
  
  protected int gpno;
  protected int no;
  protected int groupNo;
  protected int groupscNo;
  protected int MyScheduleStatus;
  protected String name;
  protected String nickName;
  protected String groupName;
  protected String title;
  protected String start;
  protected String end;
  protected String placeName;
  protected String groupPhoto;
  
  
	public int getMyScheduleStatus() {
    return MyScheduleStatus;
  }
  public void setMyScheduleStatus(int myScheduleStatus) {
    MyScheduleStatus = myScheduleStatus;
  }
  public String getGroupPhoto() {
    return groupPhoto;
  }
  public void setGroupPhoto(String groupPhoto) {
    this.groupPhoto = groupPhoto;
  }
  
  public int getGpno() {
    return gpno;
  }
  public void setGpno(int gpno) {
    this.gpno = gpno;
  }
  public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getGroupNo() {
    return groupNo;
  }
  public void setGroupNo(int groupNo) {
    this.groupNo = groupNo;
  }
  public int getGroupscNo() {
    return groupscNo;
  }
  public void setGroupscNo(int groupscNo) {
    this.groupscNo = groupscNo;
  }
  public String getName() {
    return name;
  }
  public void setName(String name) {
    this.name = name;
  }
  public String getNickName() {
    return nickName;
  }
  public void setNickName(String nickName) {
    this.nickName = nickName;
  }
  public String getGroupName() {
    return groupName;
  }
  public void setGroupName(String groupName) {
    this.groupName = groupName;
  }
  public String getTitle() {
    return title;
  }
  public void setTitle(String title) {
    this.title = title;
  }
  public String getStart() {
    return start;
  }
  public void setStart(String start) {
    this.start = start;
  }
  public String getEnd() {
    return end;
  }
  public void setEnd(String end) {
    this.end = end;
  }
  public String getPlaceName() {
    return placeName;
  }
  public void setPlaceName(String placeName) {
    this.placeName = placeName;
  }
  
  
}
