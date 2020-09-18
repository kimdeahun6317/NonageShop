package nonageshop.service;

import java.util.ArrayList;

import nonageshop.dao.AddressDao;
import nonageshop.dao.impl.AddressDaoImpl;
import nonageshop.dto.Address;

public class AddressService {
	AddressDao dao = AddressDaoImpl.getInstance();
	
	public ArrayList<Address> selectAddressByDong(String dong){
		return dao.selectAddressByDong(dong);
		
	};
}
