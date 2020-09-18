package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.service.MemberService;

public class JoinHandler implements Command {
	private MemberService service = new MemberService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
			return "/member/join.jsp";
		} else {
			System.out.println("POST");
			HttpSession session = request.getSession();
			
			Member member = new Member();
			member.setId(request.getParameter("id"));
			member.setPwd(request.getParameter("pwd"));
			member.setName(request.getParameter("name"));
			member.setEmail(request.getParameter("email"));
			member.setZipNo(request.getParameter("zipNum"));
			member.setAddress(request.getParameter("addr1")+request.getParameter("addr2"));
			member.setPhone(request.getParameter("phone"));
			
			session.setAttribute("id", request.getParameter("id"));
			service.insertMember(member);
			
			return "/member/login.jsp";
		}
	}

}
