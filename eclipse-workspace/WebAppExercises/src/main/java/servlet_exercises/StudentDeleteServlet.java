package servlet_exercises;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import dao.StudentDAO;
import models.Student;
import utility.Status;

/**
 * Servlet implementation class StudentDeleteServlet
 */
@WebServlet("/deleteStudent")
public class StudentDeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		doGet(request, response);
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		int id=-1;
		int errorCode=10;
		
		
		Gson gson =new Gson();
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		try {
			
		  id=Integer.parseInt(request.getParameter("id"));				
		  
		} catch (Exception e) {
			out.println("Invalid Inputs. Enter valid request!!");
		}
		
		StudentDAO studentDAO = new StudentDAO();
		errorCode=studentDAO.deleteStudent(id);
		
		String json = gson.toJson(new Status(errorCode)); // 3.
		 out.print(json);
		
	}

}
