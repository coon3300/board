package co.yedam.vo;

import java.util.Date;

public class UserVO {
	// User Value Object
	private int userNo;				// user_no
	private String userId;			// user_id
	private String userName;		// user_name
	private String userPwd;			// user_pwd
	private String email;			// email
	private String userGrant;		// user_grant
	private String tel;				// tel
	private String address;			// address
	private String userPoint;		// user_point
	private Date dateBirth; 		// date_birth
	private Date dateRecent; 		// date_recent
	private Date dateUpdated; 		// date_updated
	private Date dateDeleted;		// date_deleted
	private Date dateCreated;		// date_created
	
	@Override
	public String toString() {
		return "UserVO [userNo=" + userNo + ", userId=" + userId + ", userName=" + userName + ", userPwd=" + userPwd
				+ ", email=" + email + ", userGrant=" + userGrant + ", tel=" + tel + ", address=" + address
				+ ", userPoint=" + userPoint + ", dateBirth=" + dateBirth + ", dateRecent=" + dateRecent
				+ ", dateUpdated=" + dateUpdated + ", dateDeleted=" + dateDeleted + ", dateCreated=" + dateCreated
				+ "]";
	}
	public int getUserNo() {
		return userNo;
	}
	public void setUserNo(int userNo) {
		this.userNo = userNo;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPwd() {
		return userPwd;
	}
	public void setUserPwd(String userPwd) {
		this.userPwd = userPwd;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getUserGrant() {
		return userGrant;
	}
	public void setUserGrant(String userGrant) {
		this.userGrant = userGrant;
	}
	public String getTel() {
		return tel;
	}
	public void setTel(String tel) {
		this.tel = tel;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getUserPoint() {
		return userPoint;
	}
	public void setUserPoint(String userPoint) {
		this.userPoint = userPoint;
	}
	public Date getDateBirth() {
		return dateBirth;
	}
	public void setDateBirth(Date dateBirth) {
		this.dateBirth = dateBirth;
	}
	public Date getDateRecent() {
		return dateRecent;
	}
	public void setDateRecent(Date dateRecent) {
		this.dateRecent = dateRecent;
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
