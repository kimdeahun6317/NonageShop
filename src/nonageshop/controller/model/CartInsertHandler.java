package nonageshop.controller.model;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.Product;
import nonageshop.service.CartService;

public class CartInsertHandler implements Command {
	private CartService service = new CartService();

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
				Cart cart = new Cart();
				cart.setMember(new Member(loginUser.getId()));
				cart.setProduct(new Product(Integer.parseInt(request.getParameter("no"))));
				cart.setQuantity(Integer.parseInt(request.getParameter("quantity")));
				service.insertCart(cart);
				return "cartList.do";
			}
			
			
		}else {
			System.out.println("POST");
			
		}
		return null;
	}

}
