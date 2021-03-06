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

public class CartListHandler implements Command {
	private CartService service = new CartService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		if (loginUser == null) {
			return "login.do";
		} else {
			ArrayList<Cart> list = service.getCartList(loginUser);
			int totalPrice = 0;
			if (list != null) {
				for (Cart cart : list) {
					totalPrice += cart.getProduct().getSalePrice() * cart.getQuantity();
				}

			} else {
				list = new ArrayList<Cart>();
			}
			request.setAttribute("cartList", list);
			request.setAttribute("totalPrice", totalPrice);
			return "mypage/cartList.jsp";
		}

	}

}
