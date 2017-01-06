package example.vo;

import java.io.Serializable;

public class CommunityFile implements Serializable{
  private static final long serialVersionUID = 1L;
  
  protected int no;
  protected int communityNo;
  protected int fileUpMember;
  protected String filename;
  

	public int getNo() {
    return no;
  }
  public void setNo(int no) {
    this.no = no;
  }
  public int getCommunityNo() {
    return communityNo;
  }
  public void setCommunityNo(int communityNo) {
    this.communityNo = communityNo;
  }
  public String getFilename() {
    return filename;
  }
  public void setFilename(String filename) {
    this.filename = filename;
  }
  
  public int getFileUpMember() {
		return fileUpMember;
	}
	public void setFileUpMember(int fileUpMember) {
		this.fileUpMember = fileUpMember;
	}
  
}
