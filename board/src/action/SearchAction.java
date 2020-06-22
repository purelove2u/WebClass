package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import domain.SearchVO;
import persistence.BoardDAO;

public class SearchAction implements Action {
    private String path;
    public SearchAction(String path) {
	this.path = path;
    }
    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	String criteria = req.getParameter("criteria");
	String keyword = req.getParameter("keyword");
	
	BoardDAO dao = new BoardDAO();
	List<BoardVO> list = dao.getSearchList(criteria, keyword);
	req.setAttribute("list", list);
	
	SearchVO vo = new SearchVO(criteria, keyword);
	req.setAttribute("search", vo);
	
	return new ActionForward(path, false);
    }

}
