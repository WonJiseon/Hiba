package example.vo;

import java.io.Serializable;

public class EventStatus implements Serializable {
	private static final long serialVersionUID = 1L;
	
	protected int no;
	protected int memberNo;
	protected int groupNo;
	protected boolean status;
	protected int addGroupNo;
	protected int addMemberNo;
	protected String name;
	
	
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

		
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public int getAddGroupNo() {
		return addGroupNo;
	}
	public void setAddGroupNo(int addGroupNo) {
		this.addGroupNo = addGroupNo;
	}
	public int getAddMemberNo() {
		return addMemberNo;
	}
	public void setAddMemberNo(int addMemberNo) {
		this.addMemberNo = addMemberNo;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "EventStatus [no=" + no + ", memberNo=" + memberNo + ", groupNo=" + groupNo + ", status=" + status + "]";
	}
	


	
}
