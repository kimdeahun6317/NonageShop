package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.Product;

public interface ProductDao {
	// 신상품 리스트 얻어오기
	ArrayList<Product> listNewProduct();
	
	// 베스트 상품 리스트 얻어오기
	ArrayList<Product> listBestProduct();
	
	// 상품번호로 상품 정보 한개 얻어오기
	Product getProduct(int no);
	
	// 상품종류별 상품 리스트 얻어오기
	ArrayList<Product> listKindProduct(String kind);
}
