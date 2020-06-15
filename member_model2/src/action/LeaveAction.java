package action;

import java.io.PrintWriter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import persistence.MemberDAO;

public class LeaveAction implements Action {
	
	private String path;
	
	public LeaveAction(String path) {
		super();
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// leaveForm.jsp 에서 넘긴 값 받아오기
		String userid = req.getParameter("userid");
		String current_password = req.getParameter("current_password");
		
		// DB처리
		MemberDAO dao = new MemberDAO();
		int result = dao.leave(userid, current_password);
		HttpSession session = req.getSession();
		
		// 세션해제하기
		if(result > 0) {
			session.removeAttribute("login");
		}else {
			path = "../view/leaveForm.jsp";
		}
		
		// 경로 및 전환 방법 설정
		return new ActionForward(path, true);
	}
}
