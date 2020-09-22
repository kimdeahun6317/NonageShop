package nonageshop.service;

import java.util.ArrayList;

import nonageshop.dao.CartDao;
import nonageshop.dao.impl.CartDaoImpl;
import nonageshop.dto.Cart;

public class CartService {
	private CartDao dao = CartDaoImpl.getInstance();

	public int insertCart(Cart cart) {
		return dao.insertCart(cart);
	}

	public ArrayList<Cart> getCartList(String userId) {
		return dao.listCart(userId);
	}

	public int deleteCart(int no) {
		return dao.deleteCart(no);
	}

}
