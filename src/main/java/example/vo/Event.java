package example.vo;

import java.io.Serializable;

public class Event implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected int no;
	protected int memberNo;
	protected int groupNo;
	protected int groupPlaceNo;
	protected String start;
	protected String color;
	protected String end;
	protected String title;	
	protected String placeName;
	protected boolean status;
	protected int like;
	protected String lat;
	protected String lon;
	protected int titleNo;
	protected int id;
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	public int getMemberNo() {
		return memberNo;
	}
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getGroupNo() {
		return groupNo;
	}
	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}
	public int getGroupPlaceNo() {
		return groupPlaceNo;
	}
	public void setGroupPlaceNo(int groupPlaceNo) {
		this.groupPlaceNo = groupPlaceNo;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getPlaceName() {
		return placeName;
	}
	public void setPlaceName(String placeName) {
		this.placeName = placeName;
	}
	public int getLike() {
		return like;
	}
	public void setLike(int like) {
		this.like = like;
	}
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
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

	public int getTitleNo() {
		return titleNo;
	}
	public void setTitleNo(int titleNo) {
		this.titleNo = titleNo;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	@Override
	public String toString() {
		return "GroupSchedule [no=" + no + ", memberNo=" + memberNo + ", groupNo=" + groupNo + ", groupPlaceNo="
				+ groupPlaceNo + ", start=" + start + ", end=" + end + ", title=" + title + ", placeName=" + placeName
				+ ", like=" + like + "]";
	}

	
}
