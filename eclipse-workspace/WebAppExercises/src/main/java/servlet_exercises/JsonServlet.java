package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import models.Student;

/**
 * Servlet implementation class JsonServlet
 */
@WebServlet("/JsonServlet")
public class JsonServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		List<Student> students= new ArrayList<>();
		response.setContentType("application/json"); 
		 response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		Gson gson =new Gson();
		
		Student student1= new Student(112,"James","Bond","London 31","0012348","London");
		students.add(student1);
		Student student2= new Student(113,"Arjen","Robert","Amsterdam 54 ","0014948","Amsterdam");
		students.add(student2);

		
		out.println(gson.toJson(students));
	}
}
