package example.vo;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class GroupMember implements Serializable {
	private static final long serialVersionUID = 1L;
	static SimpleDateFormat format = new SimpleDateFormat("yyy-MM-dd");
	
	protected int no;
	protected int groupNo;
	protected Date createdDate;
	protected String createdDate2;
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
	@Override
	public String toString() {
		return "GroupMember [no=" + no + ", groupNo=" + groupNo + ", createdDate=" + createdDate + ", createdDate2="
				+ createdDate2 + "]";
	}
	
	
	
	
}
