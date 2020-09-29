package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.dto.QnA;
import nonageshop.service.QnAService;

public class QnAWriteHandler implements Command {
	private QnAService service = new QnAService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		 String url = "qnaList.do";

	        HttpSession session = request.getSession();
	        Member loginUser = (Member) session.getAttribute("loginUser");

	        if (loginUser == null) {
	            url = "loginform.do";
	        } else {
	            QnA qna = new QnA();
	            qna.setSubject(request.getParameter("subject"));
	            qna.setContent(request.getParameter("content"));
	            qna.setId(loginUser.getId());
	            service.insertqna(qna);
	        }
	        response.sendRedirect(url);
	        return null;
	}

}
