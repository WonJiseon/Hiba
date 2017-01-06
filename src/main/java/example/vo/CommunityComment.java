package example.vo;

import java.io.Serializable;
import java.sql.Date;
import java.text.SimpleDateFormat;

public class CommunityComment implements Serializable {
  private static final long serialVersionUID = 1L;
  static SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
  
  
  protected int no;                       // 글번호
  protected int userNo;                   // 회원번호
  protected String userNicName ;          // 회원닉네임
  protected String userPhoto;             // 회원사진
  
  
  protected String communityBoardNo;      // 커뮤니티 게시물 번호
  protected String commentNo;             // 댓글번호
  protected String commentUserNo;         // 댓글회원번호
  protected String commentUserNicName;    // 댓글작성자 
  protected String commentUserPhoto;      // 댓글작성자사진 

  protected String comment;               // 댓글
  protected Date commentRegisterDate;     // 댓글 등록날짜
  protected String commentRegisterDate2;  
  
  
	
	public int getNo() {
		return no;
	}
	public void setNo(int no) {
		this.no = no;
	}
	
	public String getCommentUserPhoto() {
		return commentUserPhoto;
	}
	public void setCommentUserPhoto(String commentUserPhoto) {
		this.commentUserPhoto = commentUserPhoto;
	}
	
	public String getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(String commentNo) {
		this.commentNo = commentNo;
	}
	
	public String getCommentUserNo() {
		return commentUserNo;
	}
	public void setCommentUserNo(String commentUserNo) {
		this.commentUserNo = commentUserNo;
	}
	
	
	public String getCommunityBoardNo() {
		return communityBoardNo;
	}
	public void setCommunityBoardNo(String communityBoardNo) {
		this.communityBoardNo = communityBoardNo;
	}
	
	
	
	public String getCommentUserNicName() {
		return commentUserNicName;
	}
	public void setCommentUserNicName(String commentUserNicName) {
		this.commentUserNicName = commentUserNicName;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	
	
	public Date getCommentRegisterDate() {
		return commentRegisterDate;
	}
	public void setCommentRegisterDate(Date commentRegisterDate) {
		this.commentRegisterDate = commentRegisterDate;
		this.commentRegisterDate2 = format.format(commentRegisterDate);
	}
	public String getCommentRegisterDate2() {
		return commentRegisterDate2;
	}
	public void setCommentRegisterDate2(String str) {
		this.commentRegisterDate = Date.valueOf(str);
		this.commentRegisterDate2 = str;
	}
	
	

}



