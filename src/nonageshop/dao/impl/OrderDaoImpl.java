package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import nonageshop.dao.OrderDao;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.OrderDetail;
import nonageshop.dto.Orders;
import nonageshop.dto.Product;

public class OrderDaoImpl implements OrderDao {
	private static final OrderDaoImpl instance = new OrderDaoImpl();
	private Connection con;

	public void setCon(Connection con) {
		this.con = con;
	}

	public static OrderDaoImpl getInstance() {
		return instance;
	}

	private OrderDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public Orders listOrderById(String id, String result, int no) {
		String sql = "SELECT ONO, MID, MNAME, PHONE, ZIP_NUM, ADDRESS, DNO, ORDER_DATE, RESULT, "
				+ "PNO, PNAME, QUANTITY, SALEPRICE " + "FROM ORDER_VIEW " + "WHERE MID=? AND RESULT LIKE ? AND ONO=? ";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, result);
			pstmt.setInt(3, no);
			try (ResultSet rs = pstmt.executeQuery()) {
				Orders orders = new Orders();
				orders.setNo(rs.getInt("ONO"));
				Member member = new Member(rs.getString("MID"), rs.getString("MNAME"));
				member.setPhone(rs.getString("PHONE"));
				member.setZipNo(rs.getString("ZIP_NO"));
				member.setAddress(rs.getString("ADDRESS"));
				orders.setMember(member);

				ArrayList<OrderDetail> detail = new ArrayList<OrderDetail>();
				do {
					detail.add(getOrderDetail(rs));
				} while (rs.next());

				orders.setDetails(detail);
				orders.setOrderDate(detail.get(0).getOrderDate());
				return orders;
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}

	private OrderDetail getOrderDetail(ResultSet rs) throws SQLException {
		OrderDetail orderDetatil = new OrderDetail();
		orderDetatil.setNo(rs.getInt("NO"));
		orderDetatil.setOrderDate(rs.getDate("ORDER_DATE"));

		ProductDaoImpl pdao = ProductDaoImpl.getInstance();
		Product product = pdao.getProduct(rs.getInt("PNO"));

		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setQuantity(rs.getInt("QUANTITY"));

		orderDetatil.setCart(cart);

		/*
		 * int no = rs.getInt("ONO"); int orderDetailNo = rs.getInt("NO"); MemberDaoImpl
		 * mdao = MemberDaoImpl.getInstance(); Member member =
		 * mdao.getMember(rs.getString("ID"));
		 * 
		 * String result = rs.getString("RESULT_USEYN");
		 */

		return orderDetatil;

	}

	@Override
	public ArrayList<Integer> selectNoOrderIng(String id) {
		String sql = "select distinct ono " + "  from order_view " + " where mid=? and result='n' order by ono DESC";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			try (ResultSet rs = pstmt.executeQuery()) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				if (rs.next()) {
					do {
						list.add(rs.getInt("n"));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	@Override
	public int maxNo() {
		String sql = "SELECT MAX(no) FROM ORDERS";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt("MAX(no)");
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return 0;
	}

}
