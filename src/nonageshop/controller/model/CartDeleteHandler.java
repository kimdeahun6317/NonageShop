package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.service.CartService;

public class CartDeleteHandler implements Command {
	private CartService service = new CartService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			return null;
		} else {
			System.out.println("POST");
			System.out.println("GET");
			System.out.println("나는 덩어리, 김덩어리 ");
			String[] noArr = request.getParameterValues("no");

			for (String cno : noArr) {
				int no = Integer.parseInt(cno);
				service.deleteCart(no);
			}
			return "mypage/cartList.jsp";
		}

	}

}
