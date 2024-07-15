package co.yedam.vo;

import java.util.Date;

public class CommentVO {
	// Comment Value Object
	private int CommentNo;
	private int userNo;		
	private int boardNo;	
	private int cmtNo;		
	private String cmtType;	
	private int typeNo;		
	private String cmtContent;	
	private Date dateUpdated; 		// date_updated
	private Date dateDeleted;		// date_deleted
	private Date dateCreated;		// date_created
	
	private String userName;
	

	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public int getCommentNo() {
		return CommentNo;
	}
	public void setCommentNo(int commentNo) {
		CommentNo = commentNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getCmtNo() {
		return cmtNo;
	}
	public void setCmtNo(int cmtNo) {
		this.cmtNo = cmtNo;
	}
	public String getCmtType() {
		return cmtType;
	}
	public void setCmtType(String cmtType) {
		this.cmtType = cmtType;
	}
	public int getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
	}
	public String getCmtContent() {
		return cmtContent;
	}
	public void setCmtContent(String cmtContent) {
		this.cmtContent = cmtContent;
	}
	public Date getDateUpdated() {
		return dateUpdated;
	}
	public void setDateUpdated(Date dateUpdated) {
		this.dateUpdated = dateUpdated;
	}
	public Date getDateDeleted() {
		return dateDeleted;
	}
	public void setDateDeleted(Date dateDeleted) {
		this.dateDeleted = dateDeleted;
	}
	public Date getDateCreated() {
		return dateCreated;
	}
	public void setDateCreated(Date dateCreated) {
		this.dateCreated = dateCreated;
	}

	
	

}
