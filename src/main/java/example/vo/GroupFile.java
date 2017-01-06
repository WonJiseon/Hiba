package example.vo;

import java.io.Serializable;

public class GroupFile implements Serializable {
	private static final long serialVersionUID = 1L;
	protected int gpno;	
	protected int no;
	protected int memberNo;
  protected String filename;
  
	public int getGpno() {
		return gpno;
	}
	public void setGpno(int gpno) {
		this.gpno = gpno;
	}
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
	public String getFilename() {
		return filename;
	}
	public void setFilename(String filename) {
		this.filename = filename;
	}
	@Override
	public String toString() {
		return "GroupFile [no=" + no + ", memberNo=" + memberNo + ", filename=" + filename + "]";
	}



	
	
}
