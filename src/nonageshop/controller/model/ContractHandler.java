package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;

public class ContractHandler implements Command {

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getMethod().contentEquals("GET")) {
			System.out.println("GET");
			return "/member/contract.jsp";
		}else {
			System.out.println("POST");
		}
		return null;
	}

}
