package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.Member;
import nonageshop.dto.Orders;

public interface OrderDao {

	public Orders listOrderById(String id, String result, int no);

	public ArrayList<Integer> selectNoOrderIng(Member member);

	public int maxNo();
}
