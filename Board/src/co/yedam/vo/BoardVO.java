package co.yedam.vo;

import java.util.Date;

public class BoardVO {
	// Board Value Object
	private int boardNo;			// board_no
	private int userNo;				// user_no
	private String title;			// title
	private String boardContent;	// board_content
	private String boardView;		// board_view
	private String boardLike;		// board_like
	private Date dateUpdated; 		// date_updated
	private Date dateDeleted;		// date_deleted
	private Date dateCreated;		// date_created
	
	private String userName;		// user_name
	
	public static int currPage = 0;

	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String briefShow() {
//		return boardNo + "  " + title + "  " + boardView + "  " + boardLike + "  " + dateCreated;
		String blank ="";
		for (int i = 0; i <= (20-this.title.length())/2; i++) {
			blank += " ";
		}
		return String.format("%4d %-20s %4s %4s %4s %12s", 
				this.boardNo,
				blank +	this.title,
				this.userName,
				this.boardView,
				this.boardLike,
				this.dateCreated
				);
	}
	
	@Override
	public String toString() {
		return "BoardVO [boardNo=" + boardNo + ", userNo=" + userNo + ", title=" + title + ", boardContent="
				+ boardContent + ", boardView=" + boardView + ", boardLike=" + boardLike + ", dateUpdated="
				+ dateUpdated + ", dateDeleted=" + dateDeleted + ", dateCreated=" + dateCreated + "]";
	}
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardView() {
		return boardView;
	}
	public void setBoardView(String boardView) {
		this.boardView = boardView;
	}
	public String getBoardLike() {
		return boardLike;
	}
	public void setBoardLike(String boardLike) {
		this.boardLike = boardLike;
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
