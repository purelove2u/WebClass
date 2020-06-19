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

import domain.ProductVO;

public class ProductDAO {
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
    
    // 상품 전체 정보 리턴하는 메소드
    public List<ProductVO> getList(){
	List<ProductVO> list = new ArrayList<ProductVO>();
	String sql = "select pno, category, name, amount, price, regdate "
		+ "from Product order by pno desc";
	try (Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql)){
	    ResultSet rs = pstmt.executeQuery();
	    while(rs.next()) {
		ProductVO vo = new ProductVO();
		vo.setPno(rs.getInt(1));
		vo.setCategory(rs.getString(2));
		vo.setName(rs.getString(3));
		vo.setAmount(rs.getInt(4));
		vo.setPrice(rs.getInt(5));
		vo.setRegdate(rs.getDate(6));
		
		list.add(vo);
		
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return list;
    }
    
    // 상품을 등록하는 메소드
    public int insertProduct(ProductVO vo) {
	int result = 0;
	String sql = "insert into product(pno, category, name, content, psize, color, amount, price)"
		+ "values(product_seq.nextVal,?,?,?,?,?,?,?)";
	try (Connection con = getConnection();
		PreparedStatement pstmt = con.prepareStatement(sql)){
	    pstmt.setString(1, vo.getCategory());
	    pstmt.setString(2, vo.getName());
	    pstmt.setString(3, vo.getContent());
	    pstmt.setString(4, vo.getPsize());
	    pstmt.setString(5, vo.getColor());
	    pstmt.setInt(6, vo.getAmount());
	    pstmt.setInt(7, vo.getPrice());
	    
	    result = pstmt.executeUpdate();
	} catch (Exception e) {
	    e.printStackTrace();
	}
	return result;
    }
}
