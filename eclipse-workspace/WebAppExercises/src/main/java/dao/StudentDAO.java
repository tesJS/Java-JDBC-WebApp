package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import com.google.gson.Gson;
import data_access.ConnectionParameters;
import data_access.DbUtils;
import models.Student;

public class StudentDAO {

	public StudentDAO() {
		
	}
	
	protected Connection getConnection() {
		
		Connection connection=null;
		try {
			// 1. Open a database connection
			
			try {
				Class.forName(ConnectionParameters.jdbcDriver);
			} catch (ClassNotFoundException nfe) {
				nfe.printStackTrace();
			}
			 
			connection = DriverManager.getConnection(ConnectionParameters.connectionString,
					ConnectionParameters.username, ConnectionParameters.password);
				
			} catch (SQLException sqle) {
				// 7. If any JDBC operation fails, we display an error message here
				System.out.println("\n[ERROR] Database error. " + sqle.getMessage());
		
			}
				return connection;	
		
	}
	
	
	public List<Student> getAllStudents(){
		
		List<Student> students=new ArrayList<Student>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		
	
		try {
			// 1. Open a database connection
			connection = getConnection();

			// 2. Define the SQL query text
			String sqlText = "SELECT * FROM Student ";

			// 3. Create a prepared statement based on the SQL query text
			preparedStatement = connection.prepareStatement(sqlText);

			// 4. Execute the SQL query with the PreparedStatement object
			resultSet = preparedStatement.executeQuery();

			// 5. Iterate the results.
			// NB! The next() method moves the cursor to the next available row in the
			// results. Initially, the cursor is 'before the first row'.
			// The next() method returns false if there are no more rows.
			while (resultSet.next()) {

				// 6. Each column value has to be retrieved separately as below
				int id=resultSet.getInt("id");				
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");

				students.add(new Student(id,firstname
						,lastname,streetaddress,postcode,postoffice));
			}

		} catch (SQLException sqle) {
			// 7. If any JDBC operation fails, we display an error message here
			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

		} finally {
			// 8. The resources should be closed as soon as we don't need them anymore
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		
		return students;
		
	}
	public Student getStudentByID(int studentID) {
		int id=studentID;
		Student student=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		

		

		try {
			// 1. Open a database connection
			connection = getConnection();

			// 2. Define the SQL query text (NB! Exclamation mark is used as a place-holder
			// for a parameter value)
			String sqlText = "SELECT * FROM Student WHERE id=?";

			// 3. Create a prepared statement based on the SQL query text
			preparedStatement = connection.prepareStatement(sqlText);

			// 4. Set the query parameter value(s) based on the user input
			
			preparedStatement.setInt(1, id);

			// 5. Execute the SQL query with the PreparedStatement object
			resultSet= preparedStatement.executeQuery();
			
				
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");
				
				 student= new Student(id,firstname
						,lastname,streetaddress,postcode,postoffice);
			
							
			
			

		} catch (SQLException sqle) {
			
			

		} finally {
			
			// 9. The resources should be closed as soon as we don't need them anymore
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
			
		}
		return student;
		
	}
	public String getAllStudentsJSON() {		
		
		List<Student> students=new ArrayList<Student>();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;

		
	
		try {
			// 1. Open a database connection
			connection = getConnection();

			// 2. Define the SQL query text
			String sqlText = "SELECT * FROM Student ";

			// 3. Create a prepared statement based on the SQL query text
			preparedStatement = connection.prepareStatement(sqlText);

			// 4. Execute the SQL query with the PreparedStatement object
			resultSet = preparedStatement.executeQuery();

			// 5. Iterate the results.
			// NB! The next() method moves the cursor to the next available row in the
			// results. Initially, the cursor is 'before the first row'.
			// The next() method returns false if there are no more rows.
			while (resultSet.next()) {

				// 6. Each column value has to be retrieved separately as below
				int id=resultSet.getInt("id");				
				String firstname = resultSet.getString("firstname");
				String lastname = resultSet.getString("lastname");
				String streetaddress = resultSet.getString("streetaddress");
				String postcode = resultSet.getString("postcode");
				String postoffice = resultSet.getString("postoffice");

				students.add(new Student(id,firstname
						,lastname,streetaddress,postcode,postoffice));
			}

		} catch (SQLException sqle) {
			// 7. If any JDBC operation fails, we display an error message here
			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

		} finally {
			// 8. The resources should be closed as soon as we don't need them anymore
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		Gson gson = new Gson();
		String studentJson= gson.toJson(students);
		
		return studentJson;
	}
	
	
	
	
	public int insertStudent(Student student) {
		
		int result=-1;		
		Connection connection=null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			// 1. Open a database connection
			connection = getConnection();

			// 2. Define the SQL query text (NB! Exclamation mark is used as a place-holder
			// for a parameter value)
			String sqlText = "INSERT INTO Student (id, firstname, lastname, streetaddress,postcode,postoffice) VALUES (?, ?, ?, ?, ?, ?)";

			// 3. Create a prepared statement based on the SQL query text
			preparedStatement = connection.prepareStatement(sqlText);			
			
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstname());
			preparedStatement.setString(3, student.getLastname());
			preparedStatement.setString(4, student.getStreetaddress());
			preparedStatement.setString(5, student.getPostcode());
			preparedStatement.setString(6, student.getPostoffice());
			result= preparedStatement.executeUpdate();

			
		} catch (SQLException sqle) {
			// 8. If any JDBC operation fails, we display an error message here
			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

		} finally {
			
			// 9. The resources should be closed as soon as we don't need them anymore
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		if(result>0)
			result=1;
		
		return result;
		
	}
	public int deleteStudent(int studentId) {
		int result=-1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			// 1. Open a database connection
			connection = getConnection();
			// 2. Define the SQL query text (NB! Exclamation mark is used as a place-holder
			// for a parameter value)
			String sqlText = "DELETE FROM Student WHERE id=?";

			// 3. Create a prepared statement based on the SQL query text
			preparedStatement = connection.prepareStatement(sqlText);					
			preparedStatement.setInt(1, studentId);
			

			// 5. Execute the SQL query with the PreparedStatement object
			 result=preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			// 8. If any JDBC operation fails, we display an error message here
			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

		} finally {
			
			// 9. The resources should be closed as soon as we don't need them anymore
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		if(result>0)
			result=1;
		
		return result;
		
	}
	public int updateStudent(Student student) {
		int result=-1;
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		
		try {
			
			// 1. Open a database connection
			connection = getConnection();
			// 2. Define the SQL query text (NB! Exclamation mark is used as a place-holder
			// for a parameter value)
			String sqlText = "UPDATE  Student SET id=?,firstname=?, lastname=?, "
									+ "streetaddress=?,postcode=?,postoffice=? WHERE id=?";

			// 3. Create a prepared statement based on the SQL query text
			
			preparedStatement = connection.prepareStatement(sqlText);
			preparedStatement.setInt(1, student.getId());
			preparedStatement.setString(2, student.getFirstname());
			preparedStatement.setString(3, student.getLastname());
			preparedStatement.setString(4, student.getStreetaddress());
			preparedStatement.setString(5, student.getPostcode());
			preparedStatement.setString(6, student.getPostoffice());
			preparedStatement.setInt(7, student.getId());

			// 5. Execute the SQL query with the PreparedStatement object
			 result=preparedStatement.executeUpdate();

		} catch (SQLException sqle) {
			// 8. If any JDBC operation fails, we display an error message here
			System.out.println("\n[ERROR] Database error. " + sqle.getMessage());

		} finally {
			
			// 9. The resources should be closed as soon as we don't need them anymore
			DbUtils.closeQuietly(resultSet, preparedStatement, connection);
		}
		if(result>0)
			result=1;
		
		return result;
		
		
	}
}
