package com.example.ExaPSW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.sql.*;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.DriverManager;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;

@WebServlet(name = "UserLogin", value = "/UserLogin")
public class UserLogin extends HttpServlet {

    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void init(ServletConfig cfg) throws ServletException {
        String URL = "jdbc:mysql:3306//localhost/examendb";
        String userName = "root";
        String password = "Patopato612:";

        try {
            //colocamos el tipo de driver
            Class.forName("com.mysql.jdbc.Driver");
            URL = "jdbc:mysql://localhost/examendb";
            con = DriverManager.getConnection(URL, userName, password);
            set = con.createStatement();
            System.out.println("Conexion exitosa");
        } catch (Exception e) {

            System.out.println("Conexion no exitosa");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());

        }
    }


    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String databaseUsername = "";
        String databasePassword = "";

        // Check Username and Password
        String name = request.getParameter("name");
        String password = request.getParameter("pass");
        // Create SQL Query
        try {
            Statement stmt = con.createStatement();
            String SQL = "SELECT * FROM usuarios WHERE nombre='" + name + "' && contrsena='" + password + "'";

            ResultSet rs = set.executeQuery(SQL);

            // Check Username and Password
            while (rs.next()) {
                databaseUsername = rs.getString("nombre");
                databasePassword = rs.getString("contrsena");
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

        if (name.equals(databaseUsername) && password.equals(databasePassword)) {
            System.out.println("Successful Login!\n----");
            response.sendRedirect("mostrarHelados.jsp");
        } else {
            System.out.println("Incorrect Password\n----");
            System.out.println("Nombre: "+databaseUsername);
            System.out.println("Contrase??a: "+databasePassword);
        }
    }
}
