package servlet_exercises;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

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
 * Servlet implementation class StudentAddServlet
 */
@WebServlet("/addStudent")
public class StudentAddServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		
		int id=-1;
		int errorCode=10;
		String firstname="", lastname="", streetaddress="", postcode="", postoffice="";
		PrintWriter out = response.getWriter();
		 response.setContentType("application/json;charset=UTF-8");
		 response.setCharacterEncoding("UTF-8");
		
		/*
		 * response.setContentType("text/json"); response.setCharacterEncoding("UTF-8");
		 */
		
		
		
		try {
			
		  id=Integer.parseInt(request.getParameter("id"));				
		  firstname=request.getParameter("firstname") ;
		  lastname=request.getParameter("lastname") ; 
		  streetaddress=request.getParameter("streetaddress") ; 
		  postcode=request.getParameter("postcode") ; 
		  postoffice=request.getParameter("postoffice") ; 
		  
		} catch (Exception e) {
			//out.println("Invalid Inputs. Enter valid request!!");
		}
		
		

		Student std= new Student(id, firstname, lastname, streetaddress, postcode, postoffice);
		
		
		StudentDAO studentDAO = new StudentDAO();
		if(std.getId()!=-1) {
			errorCode=studentDAO.insertStudent(std);
		}
		
		 Gson gson = new Gson();
		 String json = gson.toJson(new Status(errorCode));
		 out.print(json);
		
		
		
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		
		doGet(req, resp);		
	}
	
	

}
