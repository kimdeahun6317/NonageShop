package nonageshop.controller.model;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class IndexHandler implements Command {
	ProductService service = new ProductService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if(request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
		System.out.println("IndexHandler");
		
		List<Product> newProductList = service.ListNewAll();
		List<Product> bestProductList = service.ListBestAll();
		
		request.setAttribute("newProductList", newProductList);
		request.setAttribute("bestProductList", bestProductList);
		return "index.jsp";
		}else {
			System.out.println("POST");
			return null;
		}
	}

}
