package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;

import nonageshop.dao.CartDao;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.Product;
import nonageshop.service.MemberService;
import nonageshop.service.ProductService;

public class CartDaoImpl implements CartDao {
	private static final CartDaoImpl instance = new CartDaoImpl();
	private MemberService mservice = new MemberService();
	private ProductService pservice = new ProductService();

	public static CartDaoImpl getInstance() {
		return instance;
	}

	private CartDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public int insertCart(Cart cart) {
		String sql = "insert into cart(MEMBERID,PNO,QUANTITY,REG_DATE) values(?, ?,?,?)";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, cart.getMember().getId());
			pstmt.setInt(2, cart.getProduct().getNo());
			pstmt.setInt(3, cart.getQuantity());
			pstmt.setTimestamp(4, new Timestamp(cart.getRegDate().getTime()));
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}

	@Override
	public ArrayList<Cart> listCart(String userId) {
		String sql = "SELECT NO,MEMBERID,PNO,QUANTITY,RESULT_USEYN,REG_DATE FROM CART WHERE memberid = ? ORDER BY NO ASC";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, userId);
			try (ResultSet rs = pstmt.executeQuery()) {
				ArrayList<Cart> list = new ArrayList<Cart>();
				if (rs.next()) {
					do {
						list.add(getCart(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	private Cart getCart(ResultSet rs) throws SQLException {

		int no = rs.getInt("NO");
		Member member = mservice.getMember(rs.getString("MEMBERID"));
		Product product = pservice.getProduct(rs.getInt("PNO"));
		int quantity = rs.getInt("QUANTITY");
		String useyn = rs.getString("RESULT_USEYN");
		Date regDate = rs.getDate("REG_DATE");
		return new Cart(no, member, product, quantity, useyn, regDate);

	}

	@Override
	public int deleteCart(int no) {
		String sql = "DELETE FROM CART WHERE NO = ?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			return pstmt.executeUpdate();
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
	}

	@Override
	public int updateCart(Cart cart) {
		String sql = "";
		return 0;
	}
	
	

}
