package action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import domain.MemberVO;
import persistence.MemberDAO;

public class JoinAction implements Action {
	private String path;
	
	public JoinAction(String path) {
		super();
		this.path = path;
	}
	
	@Override
	public ActionForward execute(HttpServletRequest req, HttpServletResponse resp) throws Exception {
		// joinForm.jsp에서 넘긴 값 받아오기
		String userid = req.getParameter("userid");
		String password = req.getParameter("password");
		String confirm_password = req.getParameter("confirm_password");
		String name = req.getParameter("name");
		String gender = req.getParameter("gender");
		String email = req.getParameter("email");
		
		// DB작업
		if(password.equals(confirm_password)){
			//db작업한 후 회원가입 성공하면 로그인 페이지로 이동		
			MemberVO vo = new MemberVO();
			vo.setUserid(userid);
			vo.setPassword(password);
			vo.setName(name);
			vo.setGender(gender);
			vo.setEmail(email);
			MemberDAO dao = new MemberDAO();
			int result = dao.register(vo);
			
			if(result == 0){
				path = "/view/joinForm.jsp";
			}
		}else{
			//회원가입 실패하면 회원가입 페이지로 이동
			path = "/view/joinForm.jsp";
		}
		
		// 경로 설정
		return new ActionForward(path, true);
	}

}
