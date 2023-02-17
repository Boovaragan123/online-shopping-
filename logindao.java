package com.view;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author boovaragan
 */
@WebServlet(name = "Logindao", urlPatterns = {"/Logindao"})
public class logindao extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
           
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        PrintWriter out = response.getWriter();
              
        String uname=request.getParameter("user");
        String npass=request.getParameter("pass");
        
        try{
        
        Class.forName("org.apache.derby.jdbc.ClientDriver");
        Connection con=DriverManager.getConnection("jdbc:derby://localhost:1527/Rasmika","rasmika","12345");
        System.out.println("connected using Login page");
        String sql="select * from RASMIKA.REGISTER where uname=? and npass=?";
        PreparedStatement ps=con.prepareStatement(sql);
        
        ps.setString(1, uname);
        ps.setString(2, npass);
        
        String unameDB="";
        String npassDB="";
        
        ResultSet rs=ps.executeQuery();
        
        if(rs.next())
        {
        unameDB=rs.getString("uname");
        npassDB=rs.getString("npass");
        
        System.out.println("Database username : "+unameDB);
        System.out.println("Database password : "+npassDB);
        
        if((uname.equals(unameDB))&&(npass.equals(npassDB))){
        System.out.println("Inside If");
        
        RequestDispatcher rd=request.getRequestDispatcher("watches.jsp");
        rd.forward(request, response);
        }
        }
        else{
        System.out.println("Inside else");

        RequestDispatcher rd=request.getRequestDispatcher("Error.jsp");
        rd.forward(request, response);
        }
        
        }catch(ClassNotFoundException | SQLException e){
            System.out.println("Error From Login 1: "+e.getMessage());
        
        }
    }
    
}
