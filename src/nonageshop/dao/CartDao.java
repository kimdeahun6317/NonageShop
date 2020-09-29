package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.Cart;
import nonageshop.dto.Member;

public interface CartDao {
	int insertCart(Cart cart);
	
	ArrayList<Cart> listCart(Member member);
	
	int deleteCart(int no);
	
	int updateCart(Cart cart);
}
