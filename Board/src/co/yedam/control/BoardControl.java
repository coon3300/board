package co.yedam.control;

import java.util.List;
import java.util.Scanner;

import co.yedam.dao.BoardDAO;
import co.yedam.dao.CommentDAO;
import co.yedam.dao.LikeDAO;
import co.yedam.dao.UserDAO;
import co.yedam.vo.BoardVO;
import co.yedam.vo.CommentVO;
import co.yedam.vo.LikeVO;

/*
 * 사용자 입력을 가이드, 처리된 결과 출력.
 */
public class BoardControl {

	Scanner scn = new Scanner(System.in);
	BoardDAO bdao = new BoardDAO();
	LikeDAO ldao = new LikeDAO();
	UserDAO udao = new UserDAO();
	CommentDAO cdao = new CommentDAO();
	
	int userNo = 0;
	int boardNo = 0;
	String likeMark = null;
	
	public void login() {
		
		String userId = null;
		while (true) {
			System.out.print("ID 입력>");
			userId = scn.nextLine();

			if ((userNo = udao.selectExistsUserdId(userId)) != 0) {
				// ID 존재.
				break;
			}
			System.out.println("찾는 ID가 없음. ID 다시 입력>");
		}
		
		while (true) {
			String userPwd = null;
			System.out.print("비밀번호 입력>");
			userPwd = scn.nextLine();
			
//			System.out.println("userPwd : " + userPwd);
//			System.out.println("udao.selectUserPwd(userNo) : " + udao.selectUserPwd(userNo));
			
//			UserVO uvo= new UserVO();
//			uvo.setUserId(userId);
//			uvo.setUserPwd(userPwd);
			if (userPwd.equals(udao.selectUserPwd(userId))) {
				// ID 존재.
				break;
			}
			System.out.println("비밀번호가 다릅니다. 비밀번호 다시 입력>");
		}
	}
	
	
	public void run() {
		boolean isTrue = true;
		String page = "";

		bdao.setTotBoard();
		
		boardList();

		while (isTrue) {
			switch(page) {
			case "r":
				System.out.println("------------------------------------------------------");
				System.out.println(" (L)조회     (U)수정     (D)삭제     (C)댓글 쓰기    (X)종료  ");
				System.out.println("------------------------------------------------------");
				break;
			default :
				System.out.println("------------------------------------------------------");
				System.out.println("  (L)조회   (C)쓰기   (R)읽기   (U)수정   (D)삭제   (X)종료  ");
				System.out.println("------------------------------------------------------");
			}
		
			System.out.print("선택> ");
			String menu = scn.nextLine();

			switch (menu) {
			case "<":
				if(bdao.getCurrPage() == 1) {
					// do nothing
				}else {
					bdao.setCurrPage(bdao.getCurrPage() - 1 );
				}
				boardList();
				
				break;
			case ">":
				if(bdao.getCurrPage() == bdao.getTotPage()) {
					//do nothing
				}else {
					bdao.setCurrPage(bdao.getCurrPage() + 1);
				}
				boardList();
				
				break;
			case "0":
//				boardList();
				boardListAll();
				page = ""; 
				break;				
			case "L":
			case "l":
//				boardList();
				boardList();
				page = ""; 
				break;
			case "C":
			case "c":
				if(page.equals("r")) {
					addComment();
				}else {
					addBoard();
					page = ""; 
				}
				break;
			case "R":
			case "r":
				readBoard();
				page = "r";
				break;
			case "U":
			case "u":
				modifyBoard();
				page = ""; 
				break;
			case "D":
			case "d":
				removeBoard();
				page = ""; 
				break;
			case "+":
				if(page == "r") {
					likeBoard();
					page = ""; 
				}
				break;
			case "-":
				if(page == "r") {
					likeBoardCancel();
					page = ""; 
				}
				break;
			case "X":
			case "x":
				System.out.println("종료합니다.");
				isTrue = false;
			}
		} // end of run().
	}
	
	// 삭제 기능.
	void removeBoard() {
		int boardNo = 0; // 블럭 레벨 변수
		while (true) {
			System.out.println("삭제할 글 번호>");
			boardNo = Integer.parseInt(scn.nextLine());

			if (bdao.selectExistsBoardNo(boardNo) == 1) {
				// 학생번호 존재.
				break;
			}
			System.out.println("찾는 글 번호가 없음. 글 번호 다시 입력>");

		}
		BoardVO bvo = new BoardVO();
		bvo.setBoardNo(boardNo);
		
		if(bdao.deleteBoard(bvo)) {
			System.out.println("삭제완료");
		}		
	}
	

	void modifyBoard() {
		int boardNo = 0; // 블럭 레벨 변수
		while (true) {
			System.out.println("변경할 글 번호>");
			boardNo = Integer.parseInt(scn.nextLine());

			if (bdao.selectExistsBoardNo(boardNo) == 1) {
				// 글 번호 존재.
				break;
			}
			System.out.println("찾는 글 번호 없음. 글 번호 다시 입력>");

		}
		System.out.println("변경할 제목>");
		String title = scn.nextLine();
		System.out.println("변경할 내용>");
		String boartContent = scn.nextLine();
		
		BoardVO bvo = new BoardVO();
		bvo.setBoardNo(boardNo);
		bvo.setTitle(title);
		bvo.setBoardContent(boartContent);
		
		if(bdao.updateBoard(bvo)) {
		System.out.println("수정완료");
		}
	}
	
	// ㅊ천
	void likeBoard() {
//		int boardNo = 0; // 블럭 레벨 변수
//		while (true) {
//			System.out.println("추천할 글 번호>");
//			BoardNo = Integer.parseInt(scn.nextLine());
//			
//			if (bdao.selectExistsBoardNo(BoardNo) == 1) {
//				// 글 번호 존재.
//				break;
//			}
//			System.out.println("찾는 글 번호 없음. 글 번호 다시 입력>");
//		}
//		int userNo = 0;
//		System.out.println("추천할 사용자 번호>");
//		userNo = Integer.parseInt(scn.nextLine());
		
		LikeVO lvo = new LikeVO();
		lvo.setBoardNo(boardNo);
		lvo.setUserNo(userNo);
		
		if (ldao.selectExists(lvo) != 1) { // 추천 기록 없으면
			// Like
			if(ldao.insertLike(lvo)) {
				// Board
				if(bdao.updateBoardLike(boardNo)) {
				}
				System.out.println("추천 했습니다.");
			}
		}else {
			System.out.println("이미 추천 했습니다.");
		}
	}
	// 추천 취소
	void likeBoardCancel() {
//		int boardNo = 0; // 블럭 레벨 변수
//		while (true) {
//			System.out.println("추천 취소할 글 번호>");
//			boardNo = Integer.parseInt(scn.nextLine());
//			
//			if (ldao.selectExistsBoardNo(boardNo) == 1) {
//				// tbl_board 글 번호 존재.
//				break;
//			}
//			System.out.println("찾는 글 번호 없음. 글 번호 다시 입력>");
//		}
//		
//		int userNo = 0;
//		while (true) {
//			System.out.println("추천 취소할 사용자 번호>");
//			userNo = Integer.parseInt(scn.nextLine());
//			
////			if (bdao.selectExists(BoardNo) == 1) {
//			if (ldao.selectExistsUserNo(userNo) == 1) {
//				// tbl_like 사용자 번호 존재.
//				break;
//			}
//			System.out.println("찾는 사용자 번호 없음. 사용자 번호 다시 입력>");
//		}
		
		LikeVO lvo = new LikeVO();
		lvo.setBoardNo(boardNo);
		lvo.setUserNo(userNo);
		
		if (ldao.selectExists(lvo) == 1) { // 추천 기록 있으면
			// Like
			if(ldao.updateLikeDateDeleted(lvo)) {	// tbl_like update date_deleted
				// Board
				if(bdao.updateBoardLike(boardNo)) {
				}
				System.out.println("추천 취소 했습니다.");
			}
		}else {
			System.out.println("이미 추천취소 했습니다.");
		}		
	}


	void addBoard() {
//		System.out.println(">사용자 번호 입력 : ");
//		int userNo = Integer.parseInt(scn.nextLine());
		System.out.println(">제목 입력 : ");
		String title = scn.nextLine();
		System.out.println(">내용 입력 : ");
		String boardContent = scn.nextLine();
		
		BoardVO bvo = new BoardVO();
		bvo.setUserNo(userNo);
		bvo.setTitle(title);
		bvo.setBoardContent(boardContent);
		
		// 입력기능 호출.
		if (bdao.insertBoard(bvo)) {
			System.out.println("저장완료.");
		} else {
			System.out.println("처리중 예외발생!");
		}
	}
	void addComment() {
//		System.out.println(">사용자 번호 입력 : ");
//		int userNo = Integer.parseInt(scn.nextLine());
//		System.out.println(">제목 입력 : ");
//		String title = scn.nextLine();
		System.out.println(">내용 입력 : ");
		String commentContent = scn.nextLine();
		
//		BoardVO bvo = new BoardVO();
		CommentVO cvo = new CommentVO();
		cvo.setUserNo(userNo);
		cvo.setBoardNo(boardNo);
		cvo.setCmtContent(commentContent);
		
		// 입력기능 호출.
		if (cdao.insertComment(cvo)) {
			System.out.println("댓글 등록 완료.");
		} else {
			System.out.println("처리중 예외발생!");
		}
	}

	void boardList() {
//		List<BoardVO> boards = bdao.selectListAll();
		List<BoardVO> boards = bdao.selectList(); // 4페이지 앞까지, 3페이지 부터 출력
		System.out.println("------------------------------------------------------");
		System.out.printf("%4s %-20s %4s %4s %4s %12s\n","글번호", "        제목", "작성자", "조회수", "추천", "작성일시    ");
		System.out.println("------------------------------------------------------");
		for (BoardVO bvo : boards) {
			System.out.println(bvo.briefShow());
		}
		System.out.printf("(<)이전 페이지         [ %d페이지 / 총 %d페이지 ]        (>)다음 페이지\n", bdao.getCurrPage(), bdao.getTotPage());
	} // end of boardListAll()
	void boardListAll() {
//		List<BoardVO> boards = bdao.selectListAll();
		List<BoardVO> boards = bdao.selectListAll(); // 4페이지 앞까지, 3페이지 부터 출력
		System.out.println("------------------------------------------------------");
		System.out.printf("%4s %-20s %4s %4s %4s %12s\n","글번호", "        제목", "작성자", "조회수", "추천", "작성일시    ");
		System.out.println("------------------------------------------------------");
		for (BoardVO bvo : boards) {
			System.out.println(bvo.briefShow());
		}
		System.out.printf("(<)이전 페이지         [ %d페이지 / 총 %d페이지 ]        (>)다음 페이지\n", bdao.getCurrPage(), bdao.getTotPage());
	} // end of boardListAll()
	void readBoard() {
//		int boardNo = 0; // 블럭 레벨 변수
		boardNo = 0;
		while (true) {
			System.out.println("읽을 글 번호>");
			boardNo = Integer.parseInt(scn.nextLine());
			if (bdao.selectExistsBoardNo(boardNo) == 1) {
				// 글 번호 존재.
				break;
			}
			System.out.println("찾는 글 번호 없음. 글 번호 다시 입력>");
		}
		
		BoardVO bvo = new BoardVO();
		bvo.setBoardNo(boardNo);
		String boardView = bdao.currBoardView(boardNo);	// boardNo에 해당하는 글의 조회수 가져오기
		bvo.setBoardView(boardView);					// 조회수 set
		bdao.updateBoardView(bvo);						// 조회수 + 1
		
//		List<BoardVO> boards = bdao.selectBoard(bvo);
		List<BoardVO> boards = bdao.selectLike(boardNo);//
		
		System.out.println("uesrNo : " + userNo);
		
		LikeVO lvo = new LikeVO();
		lvo.setUserNo(userNo);
		lvo.setBoardNo(boardNo);
		
		for (BoardVO b : boards) {	// 1번 실행
			if(ldao.selectExists(lvo) == 1) {
				likeMark = "(-)";
			}else {
				likeMark = "(+)";
			}
			System.out.println("------------------------------------------------------");
			System.out.printf("%4s %-20s %4s %4s %s%s %12s\n","글번호", "        제목", "작성자", "조회수", "추천", likeMark, "작성일시    ");
			System.out.println("------------------------------------------------------");
			System.out.println();
			System.out.println(b.briefShow());
			
			System.out.println();
			System.out.println("------------------------------------------------------");
			System.out.println();
			System.out.println(b.getBoardContent());
			System.out.println();
			break; // boards 리스트에 1개의 객체만 리턴 : 1번 실행 후 for문 탈출
		}
		
		// 댓글 리스트
		List<CommentVO> comments = cdao.selectList(boardNo);//
		CommentVO cvo = new CommentVO();
		cvo.setUserNo(userNo);
		cvo.setBoardNo(boardNo);
		
		System.out.println("-----");
		System.out.println("댓 글 |");
		for (CommentVO c : comments) {	// 1번 실행
			System.out.println("------------------------------------------------------");
			System.out.println(c.getUserName() + " | " + c.getCmtContent());
		}
		
	} // end of readBoard()
}
