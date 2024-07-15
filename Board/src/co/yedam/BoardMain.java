package co.yedam;

import co.yedam.control.BoardControl;

public class BoardMain {
	public static void main(String[] args) {
		// 프로그램 시작.
		BoardControl bcontrol = new BoardControl();
		
		bcontrol.login();
		
		bcontrol.run();
		
		/**
		*
		*
		*
		
		to did list
		게시판 쓰기, 읽기, 수정, 삭제
		조회수
		추천
		페이지 이동
		로그인(userNo 저장)
		
		to do list
		달력
		DB 새로 생성 후 테스트
		추천 취소
		댓글
		쪽지
		회원 가입
		 */
	}
}
