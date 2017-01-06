package example.vo;

import java.io.Serializable;

public class ScheduleSimple implements Serializable {
	private static final long serialVersionUID = 1L;

	protected int groupNo;
	protected int groupscNo;
	protected String title;
	protected String fileName;
	
	
	

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
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
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	@Override
	public String toString() {
		return "ScheduleSimple [groupNo=" + groupNo + ", groupscNo=" + groupscNo + ", title=" + title + ", fileName="
				+ fileName + "]";
	}
	

	


}
