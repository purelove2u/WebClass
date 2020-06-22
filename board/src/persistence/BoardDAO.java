package persistence;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;

import domain.BoardVO;

public class BoardDAO {
    public static Connection getConnection() {
	Connection con = null;
	try {
		Context ctx = new InitialContext();
		DataSource ds = (DataSource)ctx.lookup("java:comp/env/jdbc/Oracle");
		return ds.getConnection();			
	} catch (NamingException e) {
		e.printStackTrace();
	}catch (SQLException e) {
	    e.printStackTrace();
	}
	return con;
    }
    
    //게시글 등록
    public int insertArticle(BoardVO vo) {
	String sql = "insert into board(bno, name, password, title, content, attach, "
		+ "re_ref, re_lev, re_seq) "
		+ "values(board_seq.nextVal,?,?,?,?,?,board_seq.currVal,?,?)";
	int result = 0;
	try (Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql)){
	    pstmt.setString(1, vo.getName());
	    pstmt.setString(2, vo.getPassword());
	    pstmt.setString(3, vo.getTitle());
	    pstmt.setString(4, vo.getContent());
	    pstmt.setString(5, vo.getAttach());
	    pstmt.setInt(6, 0);
	    pstmt.setInt(7, 0);
	    
	    result = pstmt.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }
    
    // 전체 리스트 가져오기
    // 번호, 제목, 작성자, 날짜, 조회수
    public List<BoardVO> getList(){
	List<BoardVO> list = new ArrayList<BoardVO>();
	String sql = "select bno, title, name, regdate, readcount, re_lev "
		+ "from board order by re_ref desc, re_seq asc";
	try (Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql)){
	    ResultSet rs = pstmt.executeQuery();
	    while(rs.next()) {
		BoardVO vo = new BoardVO();
		vo.setBno(rs.getInt(1));
		vo.setTitle(rs.getString(2));
		vo.setName(rs.getString(3));
		vo.setRegdate(rs.getDate(4));
		vo.setReadcount(rs.getInt(5));
		vo.setRe_lev(rs.getInt(6));
		list.add(vo);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return list;
    }
    
    
    // bno 에 해당하는 게시물 가져오기
    // 작성자, 제목, 내용, 파일첨부
    public BoardVO getRow(int bno) {
	String sql = "select bno, name, title, content, attach, re_ref, re_lev, re_seq from board where bno = ?";
	BoardVO vo = null;
	try (Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql)){
	    pstmt.setInt(1, bno);
	    ResultSet rs = pstmt.executeQuery();
	    if (rs.next()) {		
		vo = new BoardVO();
		vo.setBno(rs.getInt(1));
		vo.setName(rs.getString(2));
		vo.setTitle(rs.getString(3));
    	    	vo.setContent(rs.getString(4));
    	    	vo.setAttach(rs.getString(5));
    	    	//댓글 작업으로 인해 추가(sql문도 수정함)
    	    	vo.setRe_ref(rs.getInt(6));
    	    	vo.setRe_lev(rs.getInt(7));
    	    	vo.setRe_seq(rs.getInt(8));
	    }
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return vo;
    }
    
    
    // 글 수정
    public int updateRow(BoardVO vo) {
	int result = 0;
	if(vo.getAttach() == null) {
	    String sql = "update board set title=?, content=? where bno=? and password=?";	    
	    try (Connection con = getConnection();
		    PreparedStatement pstmt = con.prepareStatement(sql)){
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setInt(3, vo.getBno());
		pstmt.setString(4, vo.getPassword());
		
		result = pstmt.executeUpdate();
	    } catch (Exception e) {
		e.printStackTrace();
	    }
	}else {
	    String sql = "update board set title=?, content=?, attach=? where bno=? and password=?";	    
	    try (Connection con = getConnection();
		    PreparedStatement pstmt = con.prepareStatement(sql)){
		pstmt.setString(1, vo.getTitle());
		pstmt.setString(2, vo.getContent());
		pstmt.setString(3, vo.getAttach());
		pstmt.setInt(4, vo.getBno());
		pstmt.setString(5, vo.getPassword());

		result = pstmt.executeUpdate();
	    } catch (Exception e) {
		e.printStackTrace();
	    }   
	}
	return result;
    }
    
    // 조회수 업데이트
    // update board set readcount = readcount + 1 where bno=?
    public int hitUpdate(int bno) {
	int result = 0;
	String sql = "update board set readcount = readcount + 1 where bno = ?";
	try(Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql)) {
	    pstmt.setInt(1, bno);
	    
	    result = pstmt.executeUpdate();
	    
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }
    
    
   // 글 삭제
    public int deleteRow(BoardVO vo) {
	int result = 0;
	String sql = "delete from board where bno = ? and password = ?";
	try (Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql)){
	    pstmt.setInt(1, vo.getBno());
	    pstmt.setString(2, vo.getPassword());
	    
	    result = pstmt.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }
    
    
    //댓글처리
    public int replyArticle(BoardVO vo) {
	int result = 0;
	Connection con = null;
	PreparedStatement pstmt = null;
	try {
	    con = getConnection();
	    // 댓글 작업을 위한 원본글의  re_ref, re_lev, re_lev 가져오기
	    int re_ref = vo.getRe_ref();
	    int re_seq = vo.getRe_seq();
	    int re_lev = vo.getRe_lev();
	    
	    // 댓글 삽입하기 전 현재 원본글에 달려있는 댓글들의 re_seq값 변경하기
	    String sql = "update board set re_seq = re_seq + 1";
	    sql += "where re_ref=? and re_seq>?";
	    pstmt = con.prepareStatement(sql);
	    pstmt.setInt(1, re_ref);
	    pstmt.setInt(2, re_seq);
	    result = pstmt.executeUpdate();
	    if(!pstmt.isClosed()) {
		pstmt.close();
	    }
	    
	    // 댓글 insert 작업하기
	    sql = "insert into board(bno, name, password, title, content, attach, "
			+ "re_ref, re_lev, re_seq) "
			+ "values(board_seq.nextVal,?,?,?,?,?,?,?,?)";
	    pstmt = con.prepareStatement(sql);
	    
	    pstmt.setString(1, vo.getName());
	    pstmt.setString(2, vo.getPassword());
	    pstmt.setString(3, vo.getTitle());
	    pstmt.setString(4, vo.getContent());
	    pstmt.setString(5, vo.getAttach());
	    pstmt.setInt(6, re_ref);	// 원본글의 re_ref와 동일
	    pstmt.setInt(7, re_lev+1);	// 원본글의 re_lev+1
	    pstmt.setInt(8, re_seq+1);	// 원본글의 re_seq+1
	    result = pstmt.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	} finally {
	    try {
		if(!pstmt.isClosed()) {
		    pstmt.close();
		}
		if(!con.isClosed()) {
		    con.close();
		}
	    } catch (Exception e2) {
		e2.printStackTrace();
	    }
	}
	return result;
    }
    
    
    // 검색
    // select * from board where name like '%홍길동%'
    // select * from board where title like '%홍길동%'
    // select * from board where content like '%홍길동%'
    public List<BoardVO> getSearchList(String criteria, String keyword){
	List<BoardVO> list = new ArrayList<BoardVO>();
	String sql = "select bno, title, name, regdate, readcount, re_lev"
		+ " from board where "+ criteria + " like ?"	//필드명 변수명은 ? 처리안됨
			+ " order by re_ref desc, re_seq asc";	//값 만 ?로 처리할 수 있음
	try(Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql)) {
	    
	    pstmt.setString(1, "%" + keyword + "%");
	    
	    ResultSet rs = pstmt.executeQuery();
	    while(rs.next()) {
		BoardVO vo = new BoardVO();
		vo.setBno(rs.getInt(1));
		vo.setTitle(rs.getString(2));
		vo.setName(rs.getString(3));
		vo.setRegdate(rs.getDate(4));
		vo.setReadcount(rs.getInt(5));
		vo.setRe_lev(rs.getInt(6));
		list.add(vo);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return list;
    }
   
    
    
}







