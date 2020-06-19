package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.ProductVO;
import persistence.ProductDAO;

public class ProductAddAction implements Action {
    private String path;
    public ProductAddAction(String path) {
	this.path = path;
    }
    
    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
	String name = req.getParameter("goods_name");
	String category = req.getParameter("goods_category");
	int price = Integer.parseInt(req.getParameter("goods_price"));
	String color = req.getParameter("goods_color");
	int amount = Integer.parseInt(req.getParameter("goods_amount"));
	String psize = req.getParameter("goods_size");
	String content = req.getParameter("content");
	
	ProductDAO dao = new ProductDAO();
	ProductVO vo = new ProductVO();
	vo.setName(name);
	vo.setCategory(category);
	vo.setPrice(price);
	vo.setColor(color);
	vo.setAmount(amount);
	vo.setPsize(psize);
	vo.setContent(content);
	
	int result = dao.insertProduct(vo);
	
	if(result == 0) {
	    path = "view/admin_goods_write.jsp";
	}
	
	return new ActionForward(path, true);
    }

}
