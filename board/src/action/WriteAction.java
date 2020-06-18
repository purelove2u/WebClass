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
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
//	String name = req.getParameter("name");
//	String title = req.getParameter("title");
//	String content = req.getParameter("content");
//	String password = req.getParameter("password");
//	String attach = req.getParameter("attach");
	FileUploadUtils upload = new FileUploadUtils();
	HashMap<String, String> uploadMap = upload.upload(req);
	
	String name = uploadMap.get("name");
	String title = uploadMap.get("title");
	String content = uploadMap.get("content");
	String password = uploadMap.get("password");
	String attach = uploadMap.get("attach");
	
	BoardDAO dao = new BoardDAO();
	BoardVO vo = new BoardVO();
	vo.setName(name);
	vo.setTitle(title);
	vo.setContent(content);
	vo.setPassword(password);
	//vo.setAttach(attach); 파일 첨부를 할 경우에만 값을 담음 null 방지
	if(uploadMap.containsKey("attach")) {
	    vo.setAttach(attach);
	}
	
	int result = dao.insertArticle(vo);
	
	if(result == 0) {
	    path = "view/qna_board_write.jsp";
	}
	return new ActionForward(path, true);
    }
}
