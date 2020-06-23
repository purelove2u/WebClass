package action;

import java.net.URLEncoder;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import persistence.BoardDAO;

public class ReplyAction implements Action {

	private String path;
	
	public ReplyAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//qna_board_reply.jsp에서 넘긴 값 가져오기
		
		//댓글 내용과 관련된 것들
		String name = req.getParameter("name");
		String title = req.getParameter("title");
		String content = req.getParameter("content");
		String password = req.getParameter("password");
		
		//원본글에 대한 내용
		int bno = Integer.parseInt(req.getParameter("bno"));
		int re_ref = Integer.parseInt(req.getParameter("re_ref"));
		int re_seq = Integer.parseInt(req.getParameter("re_seq"));
		int re_lev = Integer.parseInt(req.getParameter("re_lev"));
			
		// 페이지 나누기 후 추가
		String page = req.getParameter("page");
		String criteria = req.getParameter("criteria");
		String keyword = req.getParameter("keyword");
				
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPassword(password);
		vo.setRe_ref(re_ref);
		vo.setRe_lev(re_lev);
		vo.setRe_seq(re_seq);
		
		int result = dao.replyArticle(vo);
		if(result==0) {
			path = "replyView.do?bno="+bno + "&page=" + page + "&criteria=" + criteria + "&keyword=" + keyword;
		}else {
		    path += "?page=" + page + "&criteria=" + criteria + "&keyword=" + keyword;
		}
		
		return new ActionForward(path, true);
	}
}




















