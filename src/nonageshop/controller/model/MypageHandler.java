package nonageshop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import nonageshop.controller.Command;
import nonageshop.dto.Member;
import nonageshop.dto.Orders;
import nonageshop.service.OrderServcie;

public class MypageHandler implements Command {
	private OrderServcie service = new OrderServcie();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "mypage/myPage.jsp";
		HttpSession session = request.getSession();
		Member loginUser = (Member) session.getAttribute("loginUser");
		if(loginUser == null) {
			url="login.do";
		}else {
			ArrayList<Integer> orderNoList = service.selectNoOrderIng(loginUser);
			ArrayList<Orders> orders = new ArrayList<Orders>();
			for(int orderNo : orderNoList) {
				orders.add(service.listOrderById(loginUser.getId(), "0", orderNo));
			}
			
			request.setAttribute("title", "진행 중인 주문 내역");
			request.setAttribute("ordersList", orders);
		}
		return url;
	}

}
