package example.vo;

import java.io.Serializable;

public class ReplyContent implements Serializable {
	private static final long serialVersionUID = 1L;
  
  protected int no;
  protected int memberNo;
  protected int groupNo;
  protected String name;
	protected String content;
	protected String fileName;
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
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
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
	

	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	@Override
	public String toString() {
		return "ReplyContent [no=" + no  + ", groupNo=" + groupNo + ", memberNo="
				+ memberNo + ", name=" + name + ", content=" + content + "]";
	}

	
 
	
	
  
}
