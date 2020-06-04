package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import domain.UserVO;

public class UserDAO {

	static {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static Connection getConnection() {
		String url = "jdbc:oracle:thin:@192.168.0.10:1521:orcl";
		String user = "javadb";
		String password = "12345";
		try {
			Connection con = DriverManager.getConnection(url, user, password);
			return con;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int userInsert(UserVO vo) {
		Connection con = getConnection();
		String sql = "insert into userTBL values(user_seq.nextVal,?,?,?,?)";
		PreparedStatement pstmt = null;
		int result = 0;
		try {
			pstmt = con.prepareStatement(sql);
			pstmt.setString(1, vo.getUsername());
			pstmt.setInt(2, vo.getBirthyear());
			pstmt.setString(3, vo.getAddr());
			pstmt.setString(4, vo.getMobile());
			
			//실행
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally { // 무조건 실행하는 구문 //보통 자원을 해제하는 작업
			try {
				if(pstmt!=null) pstmt.close();
				if(con!=null) con.close();
			} catch (Exception e2) {
				e2.printStackTrace();
			}
		}
		return result;
	}
	
	//전체 User 가져오기
	public List<UserVO> getList() {
		List<UserVO> list = new ArrayList<UserVO>();
		String sql = "select * from userTBL";
		try (Connection con = getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);){
			ResultSet rs = pstmt.executeQuery();
			while(rs.next()) {
				UserVO vo = new UserVO();
				vo.setNo(rs.getInt(1));
				vo.setUsername(rs.getString(2));
				vo.setBirthyear(rs.getInt(3));
				vo.setAddr(rs.getString(4));
				vo.setMobile(rs.getString(5));
				list.add(vo);
			}
					
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}
}



