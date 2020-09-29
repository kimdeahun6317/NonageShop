package nonageshop.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;

import jdk.management.resource.internal.TotalResourceContext;
import nonageshop.dao.ProductDao;
import nonageshop.ds.JdbcUtil;
import nonageshop.dto.Product;
import sun.java2d.pipe.SpanClipRenderer;

public class ProductDaoImpl implements ProductDao {
	private static final ProductDaoImpl instance = new ProductDaoImpl();
	private static int counts = 5;
	private static int view_rows = 5;

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

	public Product getProduct(ResultSet rs) throws SQLException {
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

	@Override
	public ArrayList<Product> listProduct(int tpage, String product_name) {
		String sql = "SELECT NO,REG_DATE,NAME,PRICE,SALEPRICE,DEL_USEYN,BEST_USEYN FROM PRODUCT WHERE NAME LIKE ? ORDER BY NO DESC";
		int absolutepage = 1;
		absolutepage = (tpage - 1) * counts + 1;
		ArrayList<Product> productList = new ArrayList<Product>();

		try (Connection con = JdbcUtil.getConnection(); PreparedStatement pstmt = con.prepareStatement(sql)) {
			pstmt.setString(1, "%" + product_name + "%");
			try (ResultSet rs = pstmt.executeQuery()) {
				if (rs.next()) {
					rs.absolute(absolutepage);
					int count = 0;
					while (count < counts) {
						productList.add(getProduct(rs));
						if (rs.isLast()) {
							break;
						}
						rs.next();
						count++;
					}
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return productList;
	}

	@Override
	public String pageNumber(int tpage, String name) {
		String str = "";
		
		int total_pages = totalRecord(name);
		int page_count = (total_pages / counts) +1;
		
		if(total_pages % counts ==0) {
			page_count--;
		}
		
		if(tpage <1) {
			tpage =1;
		}
		int start_page = tpage - (tpage % view_rows) +1;
	    //현재 페이지가 1이면 1-(1%5) + 1 = 1, 2,3,4,5
        //현재 페이지가 2이면 2-(2%5) + 1 = 1, 
        //현재 페이지가 3이면 3-(3%5) + 1 = 1, 
        //현재 페이지가 4이면 4-(4%5) + 1 = 1, 
        //현재 페이지가 5이면 5-(5%5) + 1 = 6, 
        //현재 페이지가 6이면 6-(6%5) + 1 = 6,
        //현재 페이지가 7이면 7-(7%5) + 1 = 6,
        //현재 페이지가 8이면 8-(8%5) + 1 = 6,
        //현재 페이지가 9이면 9-(9%5) + 1 = 6,
        //현재 페이지가 10이면 10-(10%5) + 1 = 11,
        //현재 페이지가 11이면 11-(11%5) + 1 = 11,
		int end_page = start_page + (counts -1);
        //start페이지가 1이면 마지막 페이지는 5
        //start페이지가 2이면 마지막 페이지는 6
		
		
		
		
		
		
		
		
		
		
		return null;
	}

	private int totalRecord(String productName) {
		int total_pages = 0;
		String sql = "select count(*) from product where name like ?";
		try(Connection con = JdbcUtil.getConnection();
				PreparedStatement pstmt = con.prepareStatement(sql)){
			if(productName.equals("")) {
				pstmt.setString(1, "%%");
			}else {
				pstmt.setString(1, "%"+productName+"%");
			}
			try(ResultSet rs = pstmt.executeQuery()){
				if(rs.next()) {
					total_pages = rs.getInt(1);
				}
			}
		} catch (SQLException e) {
			throw new CustomSQLException(e);
		}
		return total_pages;
	}
}
