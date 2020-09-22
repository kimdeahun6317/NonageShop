package nonageshop.dao;

import java.util.ArrayList;

import nonageshop.dto.Orders;

public interface OrderDao {

	public Orders listOrderById(String id, String result, int no);

	public ArrayList<Integer> selectNoOrderIng(String id);

	public int maxNo();
}
