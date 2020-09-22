package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.Cart;

public interface CartDao {
	int insertCart(Cart cart);
	
	ArrayList<Cart> listCart(String userId);
	
	int deleteCart(int no);
	
	int updateCart(Cart cart);
}
