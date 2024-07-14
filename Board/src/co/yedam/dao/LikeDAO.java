package co.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.vo.BoardVO;
import co.yedam.vo.LikeVO;
import oracle.net.aso.i;

/*
 * 목록(R), 등록(C), 수정(U), 삭제(D)
 * 주의: DAO 메시지(System.out.println)가 없음.
 */
public class LikeDAO extends DAO{
	
	// 추천.
	public boolean insertLike(LikeVO lvo) {

		String sql = " 	INSERT INTO tbl_like ("
				+ "    	like_no,"
				+ "    	board_no,"
				+ "    	user_no"
				+ "		)"
				+ " 	values(seq_like_no.NEXTVAL, ?, ?)";
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, lvo.getBoardNo());
			psmt.setInt(2, lvo.getUserNo());
			
			int r = psmt.executeUpdate(); // 쿼리 실행.
			conn.close();
			if(r == 1) {
				return true; // 정상 처리
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return false; // 비정상 처리.
	}
	
	
	// 목록 반환 기능.
	public List<BoardVO> selectBoard(BoardVO b){
		String sql = ""
				+ "    select  b.board_no      as board_no,"
				+ "            b.title         as title,"
				+ "            u.user_name     as user_name,"
				+ "            b.board_view    as board_view,"
				+ "            b.board_like    as board_like,"
				+ "            b.board_content as board_content,"
				+ "            b.date_updated  as date_updated"
				+ "    from    tbl_user    u"
				+ "    join    tbl_board   b"
				+ "    on      u.user_no = b.user_no"
				+ "    where   b.date_deleted is null"
				+ "    and	   b.board_no = ?"
				+ "    order by b.date_created desc";

		List<BoardVO> list = new ArrayList<>();
		
		try {
			
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, b.getBoardNo());
			rs = psmt.executeQuery();
			
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBoardNo(rs.getInt("board_no"));
				bvo.setTitle(rs.getString("title"));
				bvo.setUserName(rs.getString("user_name"));
				bvo.setBoardView(rs.getString("board_view"));
				bvo.setBoardLike(rs.getString("board_like"));
				bvo.setDateUpdated(rs.getDate("date_updated"));
				
				bvo.setBoardContent(rs.getString("board_content"));
				
				list.add(bvo);
			}
			conn.close();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return list;
	} // end of selectBoard().
	
	// 목록 반환 기능.
	public List<BoardVO> selectList(){
		String sql = "	SELECT  * "
				+ "		FROM"    
				+ "    	("
				+ "    	select	b.board_no      as board_no,"
				+ "            	b.title         as title,"
				+ "            	u.user_name     as user_name,"
				+ "            	b.board_view    as board_view,"
				+ "            	b.board_like    as board_like,"
				+ "            	b.date_updated  as date_updated"
				+ "    	from    tbl_user    u"
				+ "    	join    tbl_board   b"
				+ "    	on      u.user_no = b.user_no"
				+ "    	where   b.date_deleted is null"
				+ "    	order by b.date_created desc"
				+ "    	)"
				+ "		WHERE   ROWNUM <=9";
		List<BoardVO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBoardNo(rs.getInt("board_no"));
				bvo.setTitle(rs.getString("title"));
				bvo.setUserName(rs.getString("user_name"));
				bvo.setBoardView(rs.getString("board_view"));
				bvo.setBoardLike(rs.getString("board_like"));
				bvo.setDateUpdated(rs.getDate("date_updated"));
				
				list.add(bvo);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	} // end of selectList().
	// 목록 반환 기능.
	public List<BoardVO> selectListLike(){
		String sql = "	SELECT  * "
				+ "		FROM"    
				+ "    	("
				+ "    	select	b.board_no      as board_no,"
				+ "            	b.title         as title,"
				+ "            	u.user_name     as user_name,"
				+ "            	b.board_view    as board_view,"
				+ "        		("
				+ "            	SELECT COUNT(*)"
				+ "            	FROM tbl_like l"
				+ "            	WHERE l.board_no = b.board_no"
				+ "        		) as board_like,"
				+ "            	b.date_updated  as date_updated"
				+ "    	from    tbl_user    u"
				+ "    	join    tbl_board   b"
				+ "    	on      u.user_no = b.user_no"
				+ "    	where   b.date_deleted is null"
				+ "    	order by b.date_created desc"
				+ "    	)"
				+ "		WHERE   ROWNUM <=9";
		List<BoardVO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBoardNo(rs.getInt("board_no"));
				bvo.setTitle(rs.getString("title"));
				bvo.setUserName(rs.getString("user_name"));
				bvo.setBoardView(rs.getString("board_view"));
				bvo.setBoardLike(rs.getString("board_like"));
				bvo.setDateUpdated(rs.getDate("date_updated"));
				
				list.add(bvo);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	} // end of selectList().

	
	public List<BoardVO> selectListAll(){
		String sql = ""
				+ "    select  b.board_no      as board_no,"
				+ "            b.title         as title,"
				+ "            u.user_name     as user_name,"
				+ "            b.board_view    as board_view,"
				+ "            b.board_like    as board_like,"
				+ "            b.date_updated  as date_updated"
				+ "    from    tbl_user    u"
				+ "    join    tbl_board   b"
				+ "    on      u.user_no = b.user_no"
				+ "    where   b.date_deleted is null"
				+ "    order by b.date_created desc";
		List<BoardVO> list = new ArrayList<>();
		
		try {
			conn = getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBoardNo(rs.getInt("board_no"));
				bvo.setTitle(rs.getString("title"));
				bvo.setUserName(rs.getString("user_name"));
				bvo.setBoardView(rs.getString("board_view"));
				bvo.setBoardLike(rs.getString("board_like"));
				bvo.setDateUpdated(rs.getDate("date_updated"));

				list.add(bvo);
			}
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return list;
	} // end of selectListAll().	
	
	// 추천 사용자 조회
	public int selectExists(LikeVO lvo) {
		String sql = "select count(1) from tbl_like";
		sql += "      where user_no = ?";
		sql += "      and	board_no = ?";
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, lvo.getUserNo());
			psmt.setInt(2, lvo.getBoardNo());
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

	// 추천 사용자 죠회
	public int selectExistsUserNo(int userNo) {
		String sql = "select count(1) from tbl_like";
		sql += "      where user_no = ?";
		sql += "      and 	date_deleted is null";
		try {
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
	
	// 조휘수 업데이트.
	public String currBoardView(int boardNo) {
		String sql = "select board_view "
				+ "	  from	tbl_board"
				+ "   where board_no = ?";
		try {
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
			rs = psmt.executeQuery();
			String bv = null;
			if(rs.next()) {
				bv = rs.getString("board_view");
			}

			conn.close();
			
			return bv;
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return null;
	}	
	
	
	// 수정 기능.
//	public boolean updateBoard(LikeVO lvo) {
//		String sql = " update tbl_board";
//		sql += " 	   set	  title = ?";
//		sql += " 	     	 ,board_content = ?";
//		sql += " 	     	 ,date_updated = sysdate";
//		sql += "       where  board_no = ?";
//		try {
//			conn = getConn();
//			psmt = conn.prepareStatement(sql);
//			psmt.setString(1, lvo.getTitle());
//			psmt.setString(2, lvo.getBoardContent());
//			psmt.setInt(3, lvo.getBoardNo());
//			
//			int r = psmt.executeUpdate(); // 쿼리 실행.
//			if(r == 1) {
//				return true; // 정상 처리
//			}
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		
//		return false; // 비정상 처리.
//	}
	// 수정 기능.
	public boolean updateBoardView(BoardVO bvo) {
		
		String sql = " update tbl_board";
		sql += " 	   set	  board_view = ?";
		sql += "       where  board_no = ?";
		sql += "       and	  date_deleted is null";
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, String.valueOf(Integer.parseInt(bvo.getBoardView()) + 1) ); // 현재 조회수 + 1
			psmt.setInt(2, bvo.getBoardNo());
			
			int r = psmt.executeUpdate(); // 쿼리 실행.
			conn.close();
			if(r == 1) {
				return true; // 정상 처리
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false; // 비정상 처리.
	}
	
	// 삭제
	public boolean deleteBoard(BoardVO bvo) {
		String sql = " update tbl_board";
		sql += " 	   set	  date_deleted = sysdate";
		sql += "       where  board_no = ?";
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bvo.getBoardNo());
			
			int r = psmt.executeUpdate(); // 쿼리 실행.
			conn.close();
			if(r == 1) {
				return true; // 정상 처리
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return false; // 비정상 처리.
	}

}
