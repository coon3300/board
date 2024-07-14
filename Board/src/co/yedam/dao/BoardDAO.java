package co.yedam.dao;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.yedam.vo.BoardVO;

/*
 * 목록(R), 등록(C), 수정(U), 삭제(D)
 * 주의: DAO 메시지(System.out.println)가 없음.
 */
public class BoardDAO extends DAO{
	
	private int rowBoard = 5; // 게시글 5개
	private int totBoard = 0; // 게시글 수 초기화
	private int currPage = 1; // 시작 1페이지 
	private int totPage = 1;  // 시작 시 총 1페이지
	
	public int getRowBoard() {
		return rowBoard;
	}

	public void setRowBoard(int rowBoard) {
		this.rowBoard = rowBoard;
	}


	public int getTotBoard() {
		return totBoard;
	}


	public int getTotPage() {
		return totPage;
	}

	public void setTotBoard() {
		// selectListLikeAll() 참고
		// select 후 저장
		String sql = "	select 	count(*) "
				+ "			from 	tbl_board "
				+ "			where 	date_deleted is null";
		try {
			conn = getConn();
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			
			while(rs.next()) {
				this.totBoard = Integer.parseInt(rs.getString("count(*)"));
				this.totPage = (int)Math.ceil((double)totBoard / rowBoard);
			}
			conn.close();
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}


	public int getCurrPage() {
		return currPage;
	}


	public void setCurrPage(int currPage) {
		this.currPage = currPage;
	}
	

	// 등록 기능.
	public boolean insertBoard(BoardVO bvo) {
		
		String sql = " INSERT INTO tbl_board ("
				+ "    board_no,"
				+ "    user_no,"
				+ "    title,"
				+ "    board_content"
				+ ")";
		sql += " values(seq_board_no.NEXTVAL, ?, ?, ?)";
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, bvo.getUserNo());
			psmt.setString(2, bvo.getTitle());
			psmt.setString(3, bvo.getBoardContent());
			
			int r = psmt.executeUpdate(); // 쿼리 실행.
			conn.close();
			if(r == 1) {
				return true; // 정상 처리
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false; // 비 정상 처리
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
	

	
	public List<BoardVO> selectListLikeAll(){
		String sql = "	SELECT  * "
				+ "		FROM("
				+ "		SELECT   * "
				+ "		FROM"    
				+ "    	("
				+ "    	select	rownum as rn,"
				+ "    			b.board_no      as board_no,"
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
				+ "		WHERE   ROWNUM <=?)" // 정렬 후 처음부터 ?개만 조회 : row * (enPage) --> 5* 3
				+ "WHERE   rn >=?" // 앞에서 부터 ? 만큼 자름 : row * (endPage-1) --> 5 * 2
				+ "";

//		String sqlCount = "select count(*) from tbl_board where date_deleted is null";
		
		List<BoardVO> list = new ArrayList<>();
		
		try {
			conn = getConn();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sqlCount);
//			while(rs.next()) {
//				totBoard = rs.getInt("count(*)");
////				System.out.println("게시글 수 : " + totBoard);
//			}
			
			// setBoartConut 메소드 따로 만들기
			// totBoard / rowBoard : 20 / 5 = 4 총 페이지 수
			// 햔제 페이지 표시
			
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, rowBoard * (currPage) );
			psmt.setInt(2, rowBoard * (currPage-1));
			rs = psmt.executeQuery();
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
	public List<BoardVO> selectLike(int boardNo){
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
				+ "            	b.board_content as board_content,"
				+ "            	b.date_updated  as date_updated"
				+ "    	from    tbl_user    u"
				+ "    	join    tbl_board   b"
				+ "    	on      u.user_no = b.user_no"
				+ "    	where   b.date_deleted is null"
				+ "    	and		b.board_no = ?"
				+ "    	order by b.date_created desc"
				+ "    	)"
				+ "		WHERE   ROWNUM <=1";
		List<BoardVO> list = new ArrayList<>();
		
		try {
			conn = getConn();
//			stmt = conn.createStatement();
//			rs = stmt.executeQuery(sql);
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);			
			rs = psmt.executeQuery();
			while(rs.next()) {
				BoardVO bvo = new BoardVO();
				bvo.setBoardNo(rs.getInt("board_no"));
				bvo.setTitle(rs.getString("title"));
				bvo.setUserName(rs.getString("user_name"));
				bvo.setBoardView(rs.getString("board_view"));
				bvo.setBoardLike(rs.getString("board_like"));
				bvo.setBoardContent(rs.getString("board_content"));
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
	
	// 단건 조회.
	public int selectExists(int boardNo) {
		String sql = "select count(1) from tbl_board";
		sql += "      where board_no = ?";
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setInt(1, boardNo);
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
			conn = getConn();
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
	public boolean updateBoard(BoardVO bvo) {
		String sql = " update tbl_board";
		sql += " 	   set	  title = ?";
		sql += " 	     	 ,board_content = ?";
		sql += " 	     	 ,date_updated = sysdate";
		sql += "       where  board_no = ?";
		try {
			conn = getConn();
			psmt = conn.prepareStatement(sql);
			psmt.setString(1, bvo.getTitle());
			psmt.setString(2, bvo.getBoardContent());
			psmt.setInt(3, bvo.getBoardNo());
			
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
