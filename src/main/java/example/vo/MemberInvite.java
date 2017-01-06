package example.vo;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class MemberInvite implements Serializable {
	private static final long serialVersionUID = 1L;
	static SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
	
	protected int no;
	protected int groupNo;
	protected int memberNo;
	protected int groupGroupNo;
	protected boolean status;
	protected String name;
	protected String inviteName;
	protected String inviteEmail;
	protected Date createdDate;
	protected String createdDate2;
	protected String color;
	protected String groupUserName;
	protected String groupName;
	protected String groupReplyGroupNo;
	protected String groupReplyNo;
	protected String gphoto;
	protected String sc_title;
	protected String groupText;
	protected String filename;
	public int getMemberNo() {
		return memberNo;
	}
	
	public void setMemberNo(int memberNo) {
		this.memberNo = memberNo;
	}
	public int getNo() {
		return no;
	}

	public void setNo(int no) {
		this.no = no;
	}
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	public int getGroupNo() {
		return groupNo;
	}

	public void setGroupNo(int groupNo) {
		this.groupNo = groupNo;
	}



	public String getInviteEmail() {
		return inviteEmail;
	}

	public void setInviteEmail(String inviteEmail) {
		this.inviteEmail = inviteEmail;
	}

	public Date getCreatedDate() {
    return createdDate;
  }
  public void setCreatedDate(Date createdDate) {
    this.createdDate = createdDate;
    this.createdDate2 = format.format(createdDate);
  }
  public String getCreatedDate2() {
    return createdDate2;
  }
  public void setCreatedDate2(String str) {
    this.createdDate = Date.valueOf(str);
    this.createdDate2 = str;
  }
		
  public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}
	public boolean isStatus() {
		return status;
	}

	public void setStatus(boolean status) {
		this.status = status;
	}
	
	public int getGroupGroupNo() {
		return groupGroupNo;
	}

	public void setGroupGroupNo(int groupGroupNo) {
		this.groupGroupNo = groupGroupNo;
	}

	public String getGroupUserName() {
		return groupUserName;
	}

	public void setGroupUserName(String groupUserName) {
		this.groupUserName = groupUserName;
	}

	public String getGroupName() {
		return groupName;
	}

	public void setGroupName(String groupName) {
		this.groupName = groupName;
	}
	
	public String getInviteName() {
		return inviteName;
	}

	public void setInviteName(String inviteName) {
		this.inviteName = inviteName;
	}

	
	
	public String getGroupReplyGroupNo() {
		return groupReplyGroupNo;
	}

	public void setGroupReplyGroupNo(String groupReplyGroupNo) {
		this.groupReplyGroupNo = groupReplyGroupNo;
	}

	public String getGroupReplyNo() {
		return groupReplyNo;
	}

	public void setGroupReplyNo(String groupReplyNo) {
		this.groupReplyNo = groupReplyNo;
	}

	public String getGphoto() {
		return gphoto;
	}

	public void setGphoto(String gphoto) {
		this.gphoto = gphoto;
	}
	
	public String getSc_title() {
		return sc_title;
	}

	public void setSc_title(String sc_title) {
		this.sc_title = sc_title;
	}

	
	public String getGroupText() {
		return groupText;
	}

	public void setGroupText(String groupText) {
		this.groupText = groupText;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	@Override
	public String toString() {
		return "MemberInvite [no=" + no + ", groupNo=" + groupNo + ", memberNo=" + memberNo + ", groupGroupNo="
				+ groupGroupNo + ", status=" + status + ", name=" + name + ", inviteName=" + inviteName + ", inviteEmail="
				+ inviteEmail + ", createdDate=" + createdDate + ", createdDate2=" + createdDate2 + ", color=" + color
				+ ", groupUserName=" + groupUserName + ", groupName=" + groupName + "]";
	}

	



	
}
