package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.service.MemberService;
import sun.security.ec.ECDSAOperations.Seed;

public class LoginHandler implements Command {
	private MemberService service = new MemberService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
			return "member/login.jsp";
		}else {
			System.out.println("POST");
			HttpSession session = request.getSession();
			
			String id = request.getParameter("id");
			String pwd = request.getParameter("pwd");
			
			Member member =  service.getMember(id);
			
			if(member!=null) {
				if(member.getPwd().equals(pwd)) {
					session.removeAttribute("id");
					session.setAttribute("loginUser", member);
					return "index.do";
				}
			}
		}
		return "member/login_fail.jsp";
	}

}
