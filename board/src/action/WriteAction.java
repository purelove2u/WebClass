package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import persistence.BoardDAO;
import utils.FileUploadUtils;

public class WriteAction implements Action {

	private String path;
	
	public WriteAction(String path) {
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse res) throws Exception {
		
		//qna_board_write.jsp넘긴 값 가져오기
//		String name = req.getParameter("name");
//		String title = req.getParameter("title");
//		String content = req.getParameter("content");
//		String password = req.getParameter("password");
//		String attach = req.getParameter("attach");
		
		FileUploadUtils upload1 = new FileUploadUtils();	
		HashMap<String, String> uploadMap = upload1.upload(req);
		
		String name = uploadMap.get("name");
		String title = uploadMap.get("title");
		String content = uploadMap.get("content");
		String password = uploadMap.get("password");		
		String attach = uploadMap.get("attach");	
		
		// 페이지 나누기 후 추가
		String page = "1";
		String criteria = "";
		String keyword = "";
		
		//DB작업
		BoardDAO dao = new BoardDAO();
		BoardVO vo = new BoardVO();
		vo.setName(name);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setPassword(password);
		if(uploadMap.containsKey("attach")) {
			vo.setAttach(attach);
		}
		
		int result=dao.insertArticle(vo);
		if(result==0) {
			path = "view/qna_board_write.jsp";
		}else {
		    path += "?page=" + page + "&criteria=" + criteria + "&keyword=" + keyword;
		}
		return new ActionForward(path, true);
	}
}










