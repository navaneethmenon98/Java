/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author wad47
 */
@WebServlet(urlPatterns = {"/dbaccess"})
public class dbaccess extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet dbaccess</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet dbaccess at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String db="postgres";
        String url="jdbc:postgresql://localhost:5432/postgres";
        String password="root";
        String driver="org.postgresql.Driver";
        String uname=request.getParameter("uname");
        String pass=request.getParameter("pass");
        Boolean found = false;
        PrintWriter out=response.getWriter();
        
        try{
            Class.forName(driver);
        } catch(ClassNotFoundException e)
        {out.println(e.getMessage());
        }
        
        try {
            Connection con=DriverManager.getConnection(url, db, password);
            out.println("Got Connection");
            Statement stmt=con.createStatement();
            ResultSet rs = stmt.executeQuery("select * from public.\"reg\"");
            while (rs.next())
            {
                String user=rs.getString("uname");
                String pswd=rs.getString("pass");
                if(user.equals(uname)&&pass.equals(pswd))
                {
                    found=true;
                    break;
                }
            }
            if(true==found)
            {
                out.println("Login success");
                Statement _stmt=con.createStatement();
                ResultSet rs1 = _stmt.executeQuery("select * from public.\"reg\" where uname='"+uname+"' ");
                out.println("First Name : "+rs.getString("fname"));
                out.println("Last Name : "+rs.getString("lname"));
                out.println("Gender : "+rs.getString("gen"));
                out.println("Email : "+rs.getString("email"));
                out.println("Username : "+rs.getString("uname"));
            }
            else
            {
                out.println("Login failed");
            }
            con.close();
        }
        catch(SQLException ex)
        {
            out.println(ex.getMessage());
        }   
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
