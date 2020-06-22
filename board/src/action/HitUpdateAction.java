package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.BoardDAO;

public class HitUpdateAction implements Action {
    private String path;
    public HitUpdateAction(String path) {
	this.path = path;
    }
    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	int bno = Integer.parseInt(req.getParameter("bno"));
	BoardDAO dao = new BoardDAO();
	int result = dao.hitUpdate(bno);
	
	if(result > 0) {
	    path += "?bno="+bno;
	}
	//view.do
	return new ActionForward(path, true);
    }
}
