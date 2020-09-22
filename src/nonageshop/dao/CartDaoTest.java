package nonageshop.dao;

import static org.junit.Assert.fail;

import java.util.ArrayList;
import java.util.Date;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import nonageshop.dao.impl.CartDaoImpl;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;
import nonageshop.dto.Product;

public class CartDaoTest {
	private CartDao dao;

	@Before
	public void setUp() throws Exception {
		dao = CartDaoImpl.getInstance();
	}

	@After
	public void tearDown() throws Exception {
		dao = null;
	}

	@Test
	public void testInsertCart() {
		System.out.println("testInsertCart()");
		Cart cart = new Cart( new Member("test"), new Product(21), 3, new Date());
		int res = dao.insertCart(cart);
		Assert.assertEquals(1, res);
		System.out.println(res);
	}

	@Test
	public void testListCart() {
		System.out.println("testListCart()");
		ArrayList<Cart> list = dao.listCart("test");
		Assert.assertNotNull(list);
		System.out.println(list);
	}

	@Test
	public void testDeleteCart() {
		System.out.println("testDeleteCart()");
		int res = dao.deleteCart(1);
		Assert.assertEquals(1, res);
		System.out.println(res);
	}

}
