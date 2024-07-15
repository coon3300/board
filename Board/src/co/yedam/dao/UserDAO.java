package co.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.vo.BoardVO;
import co.yedam.vo.UserVO;

/*
 * 목록(R), 등록(C), 수정(U), 삭제(D)
 * 주의: DAO 메시지(System.out.println)가 없음.
 */
public class UserDAO extends DAO{
	
	public int selectExistsUserdNo(int userNo) {
		String sql = "	select	count(1) "
				+ "		from 	tbl_user"
				+ "     where	user_no = ?"
				+ "     and	 	date_deleted is null";
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, userNo);
			rs = psmt.executeQuery();
			int cnt = 0;
			if(rs.next()) {
//				return rs.getInt(1);
				cnt = rs.getInt(1);
			}
			conn.close();
			return cnt;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}
	
	// 단건 조회.
	public int selectExistsUserdId(String userId) {
		String sql = "	select	user_no"
				+ "		from 	tbl_user"
				+ "     where	user_id = ?"
				+ "     and	 	date_deleted is null";
		String id = null;
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			int userNo = 0;
			if(rs.next()) {
//				return rs.getInt(1);
				userNo = rs.getInt("user_no");
			}
			conn.close();
			return userNo;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return 0;
	}	
	
	// 단건 조회.
	public String selectUserPwd(String userId) {
		String sql = "	select	user_pwd "
				+ "		from 	tbl_user"
				+ "     where	user_id = ?"
				+ "     and	 	date_deleted is null";
		String userPwd = null;
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, userId);
			rs = psmt.executeQuery();
			if(rs.next()) {
//				return rs.getInt(1);
				userPwd = rs.getString("user_pwd");
			}
			conn.close();
			return userPwd;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return userPwd;
	}
	



	}
