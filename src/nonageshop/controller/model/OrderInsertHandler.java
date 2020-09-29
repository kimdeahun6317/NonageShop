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
import nonageshop.dto.OrderDetail;
import nonageshop.dto.Orders;
import nonageshop.service.CartService;
import nonageshop.service.OrderServcie;

public class OrderInsertHandler implements Command {
	private CartService cservice = new CartService();
	private OrderServcie oservice = new OrderServcie();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "orderList,do";
		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");

		} else {
			System.out.println("POST");
			HttpSession session = request.getSession();
			Member loginUser = (Member) session.getAttribute("loginUser");
			if (loginUser == null) {
				url="login.do";
			} else {
				Orders orders = getOrders(loginUser);
				System.out.println("oooooooooooooooooooooooooooooooooooo"+orders);
				System.out.println("222222222222222222222222222222222222"+orders.getDetails());
				int maxNo = oservice.addOrderAndDetail(orders);
				url="orderList.do?no="+maxNo;
			}
			response.sendRedirect(url);
		}
		return null;
	}

	private Orders getOrders(Member member) {
		ArrayList<OrderDetail> detail = new ArrayList<OrderDetail>();
		for (Cart c : cservice.getCartList(member)) {
			detail.add(new OrderDetail(c));
		}
		Orders orders = new Orders();
		orders.setDetails(detail);
		orders.setMember(member);
		return orders;
	}

}
