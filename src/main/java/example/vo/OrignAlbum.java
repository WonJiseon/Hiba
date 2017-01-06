package example.vo;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.List;

public class OrignAlbum implements Serializable {

	private static final long serialVersionUID = 1L;
	static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
	protected int No;
	protected int groupNo;
	protected int memberNo;
	protected int groupScheduleNo;
	protected String filename;
	protected String name;
	protected String memberFilename;
	protected Date createdDate;
	protected String createdDate2;
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
		
	public String getMemberFilename() {
		return memberFilename;
	}
	public void setMemberFilename(String memberFilename) {
		this.memberFilename = memberFilename;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return "OrignAlbum [No=" + No + ", groupNo=" + groupNo + ", memberNo=" + memberNo + ", groupScheduleNo="
				+ groupScheduleNo + ", filename=" + filename + ", createdDate=" + createdDate + ", createdDate2=" + createdDate2
				+ "]";
	}

	
	

	
	
	
}