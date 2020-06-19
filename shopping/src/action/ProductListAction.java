package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductVO;
import persistence.ProductDAO;

public class ProductListAction implements Action {
    private String path;
    public ProductListAction(String path) {
	this.path = path;
    }
    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	
	ProductDAO dao = new ProductDAO();
	
	List<ProductVO> list = dao.getList();
	
	req.setAttribute("list", list);
	
	return new ActionForward(path, false);
    }

}
