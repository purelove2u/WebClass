package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.BookDAO;

public class DeleteAction implements Action {
	private String path;
	
	public DeleteAction(String path) {
		super();
		this.path = path;
	}
	
	@Override
	public ActionFoward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// book_delete.jsp에서 넘긴 값 가져오기
		String code = req.getParameter("code");
		
		// DB 작업
		BookDAO dao = new BookDAO();
		if(!dao.bookDelete(code)) {
			path = "index.jsp?tab=delete";
		}
		
		// 경로와 방법 설정 후 리턴
		return new ActionFoward(path, true);  //redirect방식으로 이동
	}
}
