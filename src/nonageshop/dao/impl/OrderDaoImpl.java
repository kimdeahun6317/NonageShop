package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.sun.scenario.effect.impl.prism.PrDrawable;

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
		String sql = "SELECT DNO,ONO,MID,ORDER_DATE,PNO,QUANTITY,MNAME,ZIP_NO,ADDRESS,PHONE,PNAME,SALEPRICE,RESULT "
				+ "FROM ORDER_VIEW WHERE MID=? AND RESULT LIKE ? AND ONO=? ";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, id);
			pstmt.setString(2, "%" + result + "%");
			pstmt.setInt(3, no);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
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
					}while(rs.next());
					
					orders.setDetails(detail);
					orders.setOrderDate(detail.get(0).getOrderDate());
					return orders;
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	private OrderDetail getOrderDetail(ResultSet rs) throws SQLException {
		OrderDetail orderDetatil = new OrderDetail();
		orderDetatil.setNo(rs.getInt("DNO"));
		orderDetatil.setOrderDate(rs.getDate("ORDER_DATE"));
		orderDetatil.setResult(rs.getString("RESULT"));


		Product product = new Product(rs.getInt("PNO"),rs.getString("PNAME"));
		product.setSalePrice(rs.getInt("SALEPRICE"));
		
		
		Cart cart = new Cart();
		cart.setProduct(product);
		cart.setQuantity(rs.getInt("QUANTITY"));

		orderDetatil.setCart(cart);
		return orderDetatil;
	}

	@Override
	public ArrayList<Integer> selectNoOrderIng(Member member) {
		String sql = "select distinct ono " + "  from order_view " + " where mid=? and result='0' order by ono DESC";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, member.getId());
			try (ResultSet rs = pstmt.executeQuery()) {
				ArrayList<Integer> list = new ArrayList<Integer>();
				if (rs.next()) {
					do {
						list.add(rs.getInt(1));
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
		String sql = "SELECT LAST_NUMBER AS LAST FROM  USER_SEQUENCES WHERE SEQUENCE_NAME='ORDER_NO_SEQ'";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				return rs.getInt("LAST");
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return 0;
	}

}
