package nonageshop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.service.CartService;
import nonageshop.service.OrderServcie;

public class OrderInsertHandler implements Command {
	private CartService cservice = new CartService();
	private OrderServcie oservice = new OrderServcie();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
			HttpSession session = request.getSession();
			Member loginUser = (Member) session.getAttribute("loginUser");
			if(loginUser == null) {
				return "login.do";
			}else {
				ArrayList<Cart> cartList = cservice.getCartList(loginUser.getId());
				
				oservice.transAddOrder(cartList, loginUser.getId(), cart, maxOrderNo);
				int maxNo = oservice.maxNo();
			}
		}else {
			System.out.println("POST");
		}
		return null;
	}

}
