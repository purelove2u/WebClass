package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BookVO;
import persistence.BookDAO;

public class SearchAction implements Action {
	private String path;
	
	public SearchAction(String path) {
		super();
		this.path = path;
	}
	
	@Override
	public ActionFoward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// book_search.jsp에서 넘긴 값 가져오기
		String criteria = req.getParameter("criteria");
		String keyword = req.getParameter("keyword");
		
		// DB작업
		BookDAO dao = new BookDAO();
		List<BookVO> search = dao.searchList(criteria, keyword);
		
		req.setAttribute("search", search);
		
		// 경로 설정 및 방법 설정 후 리턴
		return new ActionFoward(path, false);
	}

}
