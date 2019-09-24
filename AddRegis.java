package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class AddRegis
 */
@WebServlet("/AddRegis")
public class AddRegis extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddRegis() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
	
        String name = request.getParameter("Name");
        String email = request.getParameter("Email");
        String mobno = request.getParameter("Mobile No");
        String pass = request.getParameter("Pass");
        try {
        
            // loading drivers for mysql
            Class.forName("com.mysql.jdbc.Driver");
            
            //creating connection with the database 
            Connection con = DriverManager.getConnection
                        ("jdbc:mysql://localhost:3306/registration","root","pass@123");

            PreparedStatement ps = con.prepareStatement
                        ("insert into bookshowregis values(?,?,?,?)");

            ps.setString(1, name);
            ps.setString(2, email);
            ps.setString(3, mobno );
            ps.setString(4, pass);
            
            int i = ps.executeUpdate();
            
            if(i > 0) {
                out.println("You are sucessfully registered.");
            }
        
        }
        catch(Exception se) {
            se.printStackTrace();
        }
	}

}
