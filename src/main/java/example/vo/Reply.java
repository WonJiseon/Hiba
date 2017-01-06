package example.vo;

import java.io.Serializable;

public class Reply implements Serializable {
	private static final long serialVersionUID = 1L;
  
  protected int no;
  protected int groupNo;
	protected int memberNo;
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
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "Reply [no=" + no + ", groupNo=" + groupNo + ", memberNo=" + memberNo + ", name=" + name + "]";
	}
	
  
}
