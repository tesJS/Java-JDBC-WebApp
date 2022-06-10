package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class CalculatorServlet
 */
@WebServlet("/calculator")
public class CalculatorServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		int x = 0, y = 0;
		String result = "";
		PrintWriter out = response.getWriter();

		try {
			x = Integer.parseInt((String) request.getParameter("x"));
			y = Integer.parseInt((String) request.getParameter("y"));
			String operString = (String) request.getParameter("operation");

			if (operString.equals("multiply")) {
				result = x + " * " + y + " = " + x * y;
			} else {
				result = x + " + " + y + " = " + (x + y);
			}

		} catch (Exception e) {
			out.println("Invalid Inputs. Enter valid request!!");
		}

		out.println(result);
	}

}
