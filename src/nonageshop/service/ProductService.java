package nonageshop.service;

import java.util.ArrayList;
import java.util.List;

import nonageshop.dao.ProductDao;
import nonageshop.dao.impl.ProductDaoImpl;
import nonageshop.dto.Product;

public class ProductService {
	ProductDao dao = ProductDaoImpl.getInstance();

	public List<Product> ListBestAll() {
		return dao.listBestProduct();
	}

	public List<Product> ListNewAll() {
		return dao.listNewProduct();
	}
	
	public Product getProduct(int no) {
		return dao.getProduct(no);
	}
	
	public ArrayList<Product> ListKindProduct(String kind){
		return dao.listKindProduct(kind);
	}
	
	public ArrayList<Product> listProduct(int tpage, String product_name){
		return dao.listProduct(tpage,product_name);
	}

	public String pageNumber(int tpage, String name) {
		return dao.pageNumber(tpage,name);
	}
}
