package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BookVO;
import persistence.BookDAO;

public class SelectAction implements Action {
	private String path;
	
	public SelectAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionFoward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// 도서목록 전체 가져오기
		BookDAO dao = new BookDAO();
		List<BookVO> list = dao.getList();
		
		req.setAttribute("list", list);
		//request에 담으면 무조건 forward로 이동한다.
		return new ActionFoward(path, false);
	}

}
