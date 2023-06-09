package com.springbook.biz.board.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.springbook.biz.board.BoardVO;
import com.springbook.biz.common.JDBCUtil;

@Repository("boardDAO")
public class BoardDAO {
	
	private Connection conn=null;
	private PreparedStatement stmt=null;
	private ResultSet rs=null;
	
	private final String BOARD_INSERT="insert into board(seq,title,writer,content) values((select nvl(max(seq), 0)+1 from board),?,?,?)";
	//글을 추가할떄 사용하는 sql문 values의값 seq는 null이면 0으로만들고값을+1시켜준다 null이아니면 seq의최고값을가져온후+1을한다그리고 값은제목 작성일 내용을입력함
	private final String BOARD_UPDATE="update board set title=?,content? where seq=?";
	private final String BOARD_DELETE="delete from board where seq=?";
	private final String BOARD_GET="select * from board where seq=?";
	private final String BOARD_LIST="select * from board order by seq desc";
	//insert into board(seq,title,writer,content) values((select nvl(max(seq),0)+1 from board),?,?,?);
	//crud기능 메소드
	//글등록
	public void insertBoard(BoardVO vo) {
		try {
			conn=JDBCUtil.getConnection();
			stmt=conn.prepareStatement(BOARD_INSERT);
			stmt.setString(1, vo.getTitle());
			stmt.setString(2, vo.getWriter());
			stmt.setString(3, vo.getContent());
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();

			System.out.println("여긴거같은데2");
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	//글수정기능
	public void updateBoard(BoardVO vo) {
		try {
			conn=JDBCUtil.getConnection();
			stmt=conn.prepareStatement(BOARD_UPDATE);
			stmt.setString(1,vo.getTitle());
			stmt.setString(2, vo.getContent());
			stmt.setInt(3, vo.getSeq());
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	//글삭제기능
	public void deleteBoard(BoardVO vo) {
		try {
			conn=JDBCUtil.getConnection();
			stmt=conn.prepareStatement(BOARD_DELETE);
			stmt.setInt(1, vo.getSeq());
			stmt.executeUpdate();
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn);
		}
	}
	
	
	//글상세조회
	public BoardVO getBoard(BoardVO vo) {
		BoardVO board=null;
		try {
			conn=JDBCUtil.getConnection();
			stmt=conn.prepareStatement(BOARD_GET);
			stmt.setInt(1, vo.getSeq());
			rs=stmt.executeQuery();
			if(rs.next()) {
				board=new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
			}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn,rs);
		}
		return board;
	}
	
	//글목록조회
	public List<BoardVO> getBoardList(BoardVO vo) {
		List<BoardVO> boardList=new ArrayList<>();
		int a=0;
		try {
			conn=JDBCUtil.getConnection();
			stmt=conn.prepareStatement(BOARD_LIST);
			rs=stmt.executeQuery();
			while(rs.next()) {
			    BoardVO	board=new BoardVO();
				board.setSeq(rs.getInt("seq"));
				board.setTitle(rs.getString("title"));
				board.setWriter(rs.getString("writer"));
				board.setContent(rs.getString("content"));
				board.setRegDate(rs.getDate("regdate"));
				board.setCnt(rs.getInt("cnt"));
				boardList.add(board);
				}
		}catch (Exception e) {
			e.printStackTrace();
		}finally {
			JDBCUtil.close(stmt, conn,rs);
		}
		return boardList;
	}
}
