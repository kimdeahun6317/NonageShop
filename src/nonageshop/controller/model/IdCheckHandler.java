package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.service.MemberService;

public class IdCheckHandler implements Command {
	MemberService service = new MemberService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
			
			String id = request.getParameter("id").trim();
			
			int message = service.confirmID(id);
			
			request.setAttribute("message", message);
			request.setAttribute("id", id);
			return "/member/idcheck.jsp";
		}else {
			System.out.println("POST");
		}
		return null;
	}

}
