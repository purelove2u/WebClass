package action;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import persistence.BoardDAO;

public class ListAction implements Action {
    private String path;
    
    public ListAction(String path) {
	this.path = path;
    }
    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	
	BoardDAO dao = new BoardDAO();
	
	List<BoardVO> list = dao.getList();
	
	req.setAttribute("list", list);
	
	return new ActionForward(path, false);
    }

}
