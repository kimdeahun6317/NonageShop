package nonageshop.controller.model;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import nonageshop.controller.Command;
import nonageshop.dto.Product;
import nonageshop.service.ProductService;

public class CatagoryHandler implements Command {
	ProductService service = new ProductService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("GET");
			String kind = request.getParameter("kind").trim();

			ArrayList<Product> list = service.ListKindProduct(kind);
			System.out.println(list);
			request.setAttribute("productKindList", list);

			return "product/productKind.jsp";
		} else {
			System.out.println("POST");
			return null;
		}
	}

}
