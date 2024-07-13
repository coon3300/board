package co.yedam.vo;

import java.util.Date;

public class LikeVO {
	// Like Value Object
	private int likeNo;				// like_no
	private int userNo;				// user_no
	private int boardNo;			// board_no
	private int commentNo;			// comment_no
	private String likeType;		// like_type
	private int typeNo;				// type_no
	private Date dateUpdated; 		// date_updated
	private Date dateDeleted;		// date_deleted
	private Date dateCreated;		// date_created
	
	
	@Override
	public String toString() {
		return "LikeVO [likeNo=" + likeNo + ", userNo=" + userNo + ", boardNo=" + boardNo + ", commentNo=" + commentNo
				+ ", likeType=" + likeType + ", typeNo=" + typeNo + ", dateUpdated=" + dateUpdated + ", dateDeleted="
				+ dateDeleted + ", dateCreated=" + dateCreated + "]";
	}
	public int getLikeNo() {
		return likeNo;
	}
	public void setLikeNo(int likeNo) {
		this.likeNo = likeNo;
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
	public int getCommentNo() {
		return commentNo;
	}
	public void setCommentNo(int commentNo) {
		this.commentNo = commentNo;
	}
	public String getLikeType() {
		return likeType;
	}
	public void setLikeType(String likeType) {
		this.likeType = likeType;
	}
	public int getTypeNo() {
		return typeNo;
	}
	public void setTypeNo(int typeNo) {
		this.typeNo = typeNo;
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
