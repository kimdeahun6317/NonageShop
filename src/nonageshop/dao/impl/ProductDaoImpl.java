package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import nonageshop.dao.ProductDao;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Product;

public class ProductDaoImpl implements ProductDao {
	private static final ProductDaoImpl instance = new ProductDaoImpl();

	public static ProductDaoImpl getInstance() {
		return instance;
	}

	private ProductDaoImpl() {
		super();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ArrayList<Product> listNewProduct() {
		String sql = "SELECT NO,NAME,SALEPRICE,IMAGE FROM  new_pro_view";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				ArrayList<Product> list = new ArrayList<Product>();
				do {
					list.add(getViewProduct(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		// TODO Auto-generated method stub
		return null;
	}

	private Product getViewProduct(ResultSet rs) throws SQLException {
		int no = rs.getInt("NO");
		String name = rs.getString("NAME");
		int salePrice = rs.getInt("SALEPRICE");
		String image = rs.getString("IMAGE");
		return new Product(no, name, salePrice, image);
	}

	@Override
	public ArrayList<Product> listBestProduct() {
		String sql = "SELECT NO,NAME,SALEPRICE,IMAGE FROM best_pro_view";
		try (Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql);
				ResultSet rs = pstmt.executeQuery()) {
			if (rs.next()) {
				ArrayList<Product> list = new ArrayList<Product>();
				do {
					list.add(getViewProduct(rs));
				} while (rs.next());
				return list;
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product getProduct(int no) {
		String sql = "SELECT NO,NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,DEL_USEYN,BEST_USEYN,REG_DATE FROM PRODUCT WHERE NO =?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setInt(1, no);
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					return getProduct(rs);
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;
	}

	private Product getProduct(ResultSet rs) throws SQLException {
		int no = rs.getInt("NO");
		String name = rs.getString("NAME");
		String kind = rs.getString("KIND");
		int price = rs.getInt("PRICE");
		int salePrice = rs.getInt("SALEPRICE");
		int magin = rs.getInt("MARGIN");
		String content = rs.getString("CONTENT");
		String image = rs.getString("IMAGE");
		String delYn = rs.getString("DEL_USEYN");
		String bestYn = rs.getString("BEST_USEYN");
		Date regDate = rs.getDate("REG_DATE");
		return new Product(no, name, kind, price, salePrice, magin, content, image, delYn, bestYn, regDate);
	}

	@Override
	public ArrayList<Product> listKindProduct(String kind) {
		String sql = "SELECT NO,NAME,KIND,PRICE,SALEPRICE,MARGIN,CONTENT,IMAGE,DEL_USEYN,BEST_USEYN,REG_DATE FROM PRODUCT WHERE kind =?";
		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, kind);
			try (ResultSet rs = pstmt.executeQuery()) {
				ArrayList<Product> list = new ArrayList<Product>();
				if (rs.next()) {
					do {
						list.add(getProduct(rs));
					} while (rs.next());
					return list;
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return null;

	}

}
