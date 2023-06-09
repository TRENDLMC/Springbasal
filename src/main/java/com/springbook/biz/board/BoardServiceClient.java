package com.springbook.biz.board;

import java.util.List;

import org.springframework.context.support.AbstractApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

public class BoardServiceClient {
	
	public static void main(String[] args) {
		//1. spring 컨테이너를 구동한다
		AbstractApplicationContext container= new GenericXmlApplicationContext("applicationContext.xml");
		//2. spring 컨테이너로부터 boardServiceImpl 객체를 Lookup한다
		BoardService boardService=(BoardService) container.getBean("boardService");
		
		//3. 글등록 기능테스트
		BoardVO vo=new BoardVO();
		vo.setTitle("임시 제목");
		vo.setWriter("홍길동");
		vo.setContent("임시내용...");
		boardService.insertBoard(vo);
		
		//4. 글목록 검색 기능 테스트
		List<BoardVO> boardlist=boardService.getBoardList(vo);
		for(BoardVO board : boardlist) {
			System.out.println("--->"+board.toString());
		}
		
		//5. 스프링 컨테이너 종료
		container.close();
	}

}
