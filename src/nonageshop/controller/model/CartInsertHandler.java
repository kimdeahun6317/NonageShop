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
		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
			System.out.println("야광돼지 ");

		} else {
			System.out.println("POST");
			System.out.println("야광돼지 ");
			HttpSession session = request.getSession();
			Member loginUser = (Member) session.getAttribute("loginUser");
			if (loginUser == null) {
				response.sendRedirect("login.do");
			} else {
				Cart cart = getCart(request, loginUser);
				System.out.println("cart >" + cart);
				service.insertCart(cart);
				response.sendRedirect("cartList.do");
			}
		}
		return null;

	}

	private Cart getCart(HttpServletRequest request, Member loginUser) {
		Cart cart = new Cart();
		cart.setMember(loginUser);
		Product product = new Product();
		product.setNo(Integer.parseInt(request.getParameter("no")));
		cart.setProduct(product);
		cart.setQuantity(Integer.parseInt(request.getParameter("quantity")));
		return cart;
	}

}
