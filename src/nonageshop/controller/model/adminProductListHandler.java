package nonageshop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;
import sun.print.resources.serviceui;

public class adminProductListHandler implements Command {
	private ProductService service = new ProductService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String url = "admin/product/productList.jsp";
		
		String key = request.getParameter("key");
		key = (key==null)?"":key;
		
		String tpage = request.getParameter("tpage");
		tpage = (tpage ==null || tpage.equals("1"))?"1":tpage;
		
		request.setAttribute("key", key);
		request.setAttribute("tpage", tpage);

		ArrayList<Product> productList = service.listProduct(Integer.parseInt(tpage), key);
		
		String paging = service.pageNumber(Integer.parseInt(tpage),key);
		return null;
	}

}
