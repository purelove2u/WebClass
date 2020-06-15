package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.BookDAO;

public class ModifyAction implements Action {

	private String path;

	public ModifyAction(String path){
		super();
		this.path = path;
	}
	
	@Override
	public ActionFoward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// book_modify.jsp에서 넘긴 값 가져오기
		String code = req.getParameter("code");
		int price = Integer.parseInt(req.getParameter("price"));
		
		// DB작업
		BookDAO dao = new BookDAO();
		if(dao.modify(code, price) == 0) { //쿼리 리턴 값이 0 이면 실패
			path = "index.jsp?tab=modify";
		}
		
		// 경고 및 방법 설정 후 리턴
		return new ActionFoward(path, true);
	}

}
