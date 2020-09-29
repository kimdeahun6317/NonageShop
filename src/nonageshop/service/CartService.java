package nonageshop.service;

import java.util.ArrayList;

import nonageshop.dao.CartDao;
import nonageshop.dao.impl.CartDaoImpl;
import nonageshop.dto.Cart;
import nonageshop.dto.Member;

public class CartService {
	private CartDao dao = CartDaoImpl.getInstance();

	public int insertCart(Cart cart) {
		return dao.insertCart(cart);
	}

	public ArrayList<Cart> getCartList(Member member) {
		return dao.listCart(member);
	}

	public int deleteCart(int no) {
		return dao.deleteCart(no);
	}

}
