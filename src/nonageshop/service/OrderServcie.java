package nonageshop.service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageshop.dao.CartDao;
import nonageshop.dao.OrderDao;
import nonageshop.dao.impl.CartDaoImpl;
import nonageshop.dao.impl.OrderDaoImpl;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Member;
import nonageshop.dto.OrderDetail;
import nonageshop.dto.Orders;

public class OrderServcie {
	OrderDao dao = OrderDaoImpl.getInstance();
	CartDao cdao = CartDaoImpl.getInstance();

	@SuppressWarnings("unused")
	private void rollbackUtil(Connection con, SQLException e) {

		try {
			System.out.println("roll back");
			con.rollback();
			throw new RuntimeException(e);
		} catch (SQLException e1) {
		}

	}

	@SuppressWarnings("unused")
	private void closeUtil(Connection con, PreparedStatement orderPstmt, PreparedStatement orderDetailPstmt) {
		try {
			if (orderPstmt != null) {
				orderPstmt.close();
			}
			if (orderDetailPstmt != null) {
				orderDetailPstmt.close();
			}
			if (con != null) {
				con.close();
			}

		} catch (SQLException e) {
		}

	}

	public int addOrderAndDetail(Orders order) {
		Connection con = null;
		PreparedStatement orderPstmt = null;
		PreparedStatement orderDetailPstmt = null;
		String sql = "INSERT INTO ORDERS(ID,ORDER_DATE) VALUES(?,SYSDATE)";
		String ssql = "INSERT INTO ORDER_DETAIL(ONO, PNO, QUANTITY,RESULT_USEYN) VALUES( ?,?,?,'0')";
		int maxNo = 0;
		try {
			con = JdbcUtil.getConnection();
			con.setAutoCommit(false);

			maxNo = dao.maxNo();
			orderPstmt = con.prepareStatement(sql);
			orderPstmt.setString(1, order.getMember().getId());
			orderPstmt.executeUpdate();

			orderDetailPstmt = con.prepareStatement(ssql);

			for (OrderDetail od : order.getDetails()) {
				orderDetailPstmt.setInt(1, maxNo);
				System.out.println("maxNo--------------------------"+maxNo);
				orderDetailPstmt.setInt(2, od.getCart().getProduct().getNo());
				System.out.println("pno----------------------------"+od.getCart().getProduct().getNo());
				orderDetailPstmt.setInt(3, od.getCart().getQuantity());
				System.out.println("quantity-----------------------"+od.getCart().getQuantity());
				orderDetailPstmt.executeUpdate(); 
				
				cdao.updateCart(od.getCart());
			}
			con.commit();
		} catch (SQLException e) {
			rollbackUtil(con, e);
		} finally {
			closeUtil(con, orderPstmt, orderDetailPstmt);
		}
		return maxNo;

	}

	public Orders listOrderById(String id, String result, int no) {
		return dao.listOrderById(id, result, no);
	};

	public ArrayList<Integer> selectNoOrderIng(Member member) {
		return dao.selectNoOrderIng(member);
	};

}
