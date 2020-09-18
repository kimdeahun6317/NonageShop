package nonageshop.controller.model;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import nonageshop.controller.Command;
import nonageshop.dto.Kind;
import nonageshop.service.ProductService;

public class KindHandler implements Command {
	ProductService service = new ProductService();

	@Override
	public String process(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getMethod().equalsIgnoreCase("GET")) {
			System.out.println("KindHandler GET");
		} else {
			System.out.println("KindHandler POST");
			List<Kind> list = new ArrayList<Kind>();
			Kind kind1 = new Kind(1, "힐");
			Kind kind2 = new Kind(2, "부츠");
			Kind kind3 = new Kind(3, "슬리퍼");
			Kind kind4 = new Kind(4, "스니커즈");
			Kind kind5 = new Kind(5, "스니커즈2");

			list.add(kind1);
			list.add(kind2);
			list.add(kind3);
			list.add(kind4);
			list.add(kind5);

			Gson gson = new Gson();
			String result = gson.toJson(list, new TypeToken<List<Kind>>() {
			}.getType());
			
			System.out.println(result);

			response.setContentType("Application/json");
			response.setStatus(HttpServletResponse.SC_ACCEPTED);

			PrintWriter pw = response.getWriter();
			pw.print(result);
			pw.flush();
		}

		return null;
	}

}
