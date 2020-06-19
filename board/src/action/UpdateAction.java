package action;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.BoardVO;
import persistence.BoardDAO;
import utils.FileUploadUtils;

public class UpdateAction implements Action {
    private String path;
    public UpdateAction(String path) {
	this.path = path;
    }
    @Override
    public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
	//qna_board_modify.jsp에서 넘긴 값 가져오기
	//enctype 들어있음 (request.getparameter 안됨) -> FileUploadUtils사용
	FileUploadUtils upload = new FileUploadUtils();
	HashMap<String, String> uploadMap = upload.upload(req);
	
	int bno = Integer.parseInt(uploadMap.get("bno"));
	String title = uploadMap.get("title");
	String content = uploadMap.get("content");
	String password = uploadMap.get("password");
	String attach = uploadMap.get("attach");
	
	BoardDAO dao = new BoardDAO();
	BoardVO vo = new BoardVO();
	vo.setBno(bno);
	vo.setTitle(title);
	vo.setContent(content);
	vo.setPassword(password);
	vo.setAttach(attach);
	

	//DB수정작업
	int result = dao.updateRow(vo);
	if(result == 0) {
	    path = "/modify.do?bno=" + bno;
	}else {
	    path += "?bno=" + bno;
	}
	
	return new ActionForward(path, true);
    }

}
