package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BookVO;
import persistence.BookDAO;

public class InsertAction implements Action {
	private String path;
	
	public InsertAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionFoward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//사용자가 요청한 작업 처리하기
		//사용자가 입력한 값 가져오기
		req.setCharacterEncoding("utf-8");
		String code = req.getParameter("code");
		String title = req.getParameter("title");
		String writer = req.getParameter("writer");
		String price = req.getParameter("price");
		
		//db작업 후 성공하면 전체 리스트 보여주기
		BookDAO dao = new BookDAO();
		BookVO vo = new BookVO();
		vo.setCode(code);
		vo.setTitle(title);
		vo.setWriter(writer);
		vo.setPrice(Integer.parseInt(price));
		
		int result = dao.bookInsert(vo);
		
		//실패하면 path 재설정(현재는 select.do상태)
		if(result == 0) {
			path = "index.jsp";
		}
		return new ActionFoward(path, true);
	}

}
