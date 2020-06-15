package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import domain.MemberVO;
import persistence.MemberDAO;

public class ModifyAction implements Action {
	private String path;
	
	public ModifyAction(String path) {
		super();
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		//modifyForm.jsp에서 넘긴 값 가져오기
		String current_password = req.getParameter("current_password");
		String new_password = req.getParameter("new_password");
		String confirm_password = req.getParameter("confirm_password");
		HttpSession session = req.getSession(false);
		//DB작업
		if(new_password.equals(confirm_password)){
			MemberVO login = (MemberVO)session.getAttribute("login");
			String userid = login.getUserid();
			MemberDAO dao = new MemberDAO();
			int result = dao.passwordUpdate(userid, new_password, current_password);
			if(result > 0){		
			//현재 비밀번호가 일치하면
			//새로운 비밀번호로 수정 후 
			//로그인 페이지로 이동
				session.invalidate();
			} else{	
			//현재 비밀번호가 틀린경우
			//다시 modifyForm.jsp를 보여주기
			path = "/view/modifyForm.jsp";
			//path를 "/modify.do"로 줬을 때 nullpointException발생
			} 
		}else{	
		//new_password 와 confirm_password가 같은지 확인 후
		//같지 않으면 modifyForm.jsp 돌려보내기
			path = "/view/modifyForm.jsp";	
			//path를 "/modify.do"로 줬을 때 nullpointException발생
		}
		//경로설정 및 방법 설정 후 리턴
		return new ActionForward(path, true);
	}
}
