package example.vo;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.List;

public class MemberInviteGroup implements Serializable {
	private static final long serialVersionUID = 1L;
	static SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
	
	protected int groupNo;
	protected String groupName;
	protected int memberNo;
	protected String memberName;
	
	protected List<ScheduleSimple> scheduleList;
	
	

	public List<ScheduleSimple> getScheduleList() {
		return scheduleList;
	}

	public void setScheduleList(List<ScheduleSimple> scheduleList) {
		this.scheduleList = scheduleList;
	}


	public int getGroupNo() {
		return groupNo;
	}


	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}


	public String getGroupName() {
		return groupName;
	}


	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}


	public int getMemberNo() {
		return memberNo;
	}


	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}


	public String getMemberName() {
		return memberName;
	}


	public void setMemberName(String memberName) {
		this.memberName = memberName;
	}

	@Override
	public String toString() {
		return "MemberInviteGroup [groupNo=" + groupNo + ", groupName=" + groupName + ", memberNo=" + memberNo
				+ ", memberName=" + memberName + ", scheduleList=" + scheduleList + "]";
	}


	
	
	
}
