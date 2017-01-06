package example.vo;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class Album implements Serializable {

	private static final long serialVersionUID = 1L;
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	protected int No;
	protected int groupNo;
	protected int memberNo;
	protected int groupScheduleNo;
	protected String groupScheduleName;
	protected String groupName;
	protected String filename;
	protected Date createdDate;
	protected String createdDate2;
	protected String title;
	protected List<Event> scheduleList;

	public static SimpleDateFormat getFormat() {
		return format;
	}

	public static void setFormat(SimpleDateFormat format) {
		Album.format = format;
	}

	public int getNo() {
		return No;
	}

	public void setNo(int no) {
		No = no;
	}

	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}

	public int getMemberNo() {
		return memberNo;
	}

	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}

	public int getGroupScheduleNo() {
		return groupScheduleNo;
	}

	public void setGroupScheduleNo(int groupScheduleNo) {
		this.groupScheduleNo = groupScheduleNo;
	}

	public String getGroupScheduleName() {
		return groupScheduleName;
	}

	public void setGroupScheduleName(String groupScheduleName) {
		this.groupScheduleName = groupScheduleName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public Date getCreatedDate() {
		return createdDate;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public String getCreatedDate2() {
		return createdDate2;
	}

	public void setCreatedDate2(String createdDate2) {
		this.createdDate2 = createdDate2;
	}

	

	public List<Event> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<Event> scheduleList) {
		this.scheduleList = scheduleList;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Override
	public String toString() {
		return "Album [No=" + No + ", groupNo=" + groupNo + ", memberNo=" + memberNo + ", groupScheduleNo="
				+ groupScheduleNo + ", groupScheduleName=" + groupScheduleName + ", groupName=" + groupName
				+ ", filename=" + filename + ", createdDate=" + createdDate + ", createdDate2=" + createdDate2
				+ ", scheduleList=" + scheduleList + "]";
	}
	

	
	
	
}