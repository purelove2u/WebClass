package action;

import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import persistence.BoardDAO;

public class HitUpdateAction implements Action {
	
	private String path;
	
	public HitUpdateAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		//  bno 가져오기
		int bno = Integer.parseInt(req.getParameter("bno"));
		
		// 페이지 나누기 후 추가
		String page = req.getParameter("page");
		String criteria = req.getParameter("criteria");
		String keyword = URLEncoder.encode(req.getParameter("keyword"),"utf-8");		
		
		
		//  bno 에 해당하는 게시물 DB 에서 가져오기
		BoardDAO dao = new BoardDAO();
		//조회수 업데이트
		int result = dao.hitUpdate(bno);
		
		if(result>0){ //view.do?bno=3
			path+="?bno="+bno+"&page="+page+"&criteria="+criteria+"&keyword="+keyword;
		}
		
		return new ActionForward(path, true);
	}
}









