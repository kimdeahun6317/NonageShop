package nonageshop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Address;
import nonageshop.service.AddressService;

public class FindZipHandler implements Command {
	AddressService service = new AddressService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
			return "member/findZip.jsp";

		} else {
			System.out.println("POST");

			String dong = request.getParameter("dong");

			if (dong != null && dong.trim().equals("") == false) {
				ArrayList<Address> list = service.selectAddressByDong(dong.trim());
				request.setAttribute("addressList", list);
			}

			return "member/findZip.jsp";
		}
	}

}
