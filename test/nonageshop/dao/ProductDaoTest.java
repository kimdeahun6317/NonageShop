package nonageshop.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nonageshop.dao.impl.ProductDaoImpl;
import nonageshop.dto.Product;

public class ProductDaoTest {
	ProductDao dao;

	@Before
	public void setUp() throws Exception {
		dao = ProductDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void testListNewProduct() {
		System.out.println("testListNewProduct()");
		ArrayList<Product> list = dao.listNewProduct();
		Assert.assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void testListBestProduct() {
		System.out.println("testListBestProduct()");
		ArrayList<Product> list = dao.listBestProduct();
		Assert.assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void testGetProduct() {
		System.out.println("testGetProduct()");
		Product product = dao.getProduct(1);
		Assert.assertNotNull(product);
		System.out.println(product);
	}

	@Test
	public void testListKindProduct() {
		System.out.println("testListKindProduct()");
		ArrayList<Product> list = dao.listKindProduct("1");
		Assert.assertNotNull(list);
		System.out.println(list);
	}

}
