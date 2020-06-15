package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVO;
import persistence.MemberDAO;

public class LoginACtion implements Action {
	private String path;
	
	public LoginACtion(String path) {
		super();
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//loginForm.jsp에서 넘긴 값 가져오기
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		
		//DB처리
		MemberDAO dao = new MemberDAO();
		MemberVO vo = dao.isLogin(userid, password);
		if(vo!=null) {
			//세션에 값을 담고 loginForm.jsp로 이동
			HttpSession session = req.getSession();
			session.setAttribute("login", vo);
		}
		
		return new ActionForward(path, true);
	}

}
