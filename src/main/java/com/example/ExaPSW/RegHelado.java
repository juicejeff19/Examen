package com.example.ExaPSW;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

@WebServlet(name = "RegHelado", value = "/RegHelado")
public class RegHelado extends HttpServlet {
    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void init(ServletConfig cfg) throws ServletException {

        String URL = "jdbc:mysql://us-cdbr-east-03.cleardb.com/heroku_a5028b8063649ce";
        String userName = "root";
        String password = "Patopato612:";

        try {
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
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            String nom, sab1, sab2, sab3, desc, prec, total = "";
            nom = request.getParameter("neim");
            sab1 = request.getParameter("sabor1");
            sab2 = request.getParameter("sabor2");
            sab3 = request.getParameter("sabor3");
            prec = request.getParameter("precio");
            desc = request.getParameter("descuento");
            if(desc.equals("")){
            desc = "0";
            }
            if(!desc.equals("")){
                total = Float.toString(Float.parseFloat(prec)-Float.parseFloat(desc));
            }else if(desc.equals("")){
                total = prec;
            }
            try {
                String q = "insert into helados "
                        + "(nombre, sabor1, sabor2, sabor3, precio, descuento, total) "
                        + "values ('" + nom + "','" + sab1 + "','" + sab2 + "','" + sab3 + "','"+prec+"','"+desc+"','"+total+"')";

                //ejecutar la sentencia
                set.executeUpdate(q);

                System.out.println("Registro exitoso");


                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Registro</title>");
                out.println("</head>");
                out.println("<body>"
                        + "El nombre del helado es: " + nom);
                out.println("<h1>Registro Exitoso</h1>"
                        + "<a href='registro.html'>Regresar a la pagina principal</a>"
                        + "<br>"
                        + "<a href='Consultar'>Consultar Tabla General de Usuarios</a>");
                out.println("</body>");
                out.println("</html>");

            } catch (Exception e) {


                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Registro</title>");
                out.println("</head>");
                out.println("<body>");
                out.println("<h1>Registro No Exitoso, vuelva a intentarlo</h1>"
                        + "<a href='registro.html'>Regresar a la pagina principal</a>");
                out.println("</body>");
                out.println("</html>");

                System.out.println("No se registro en la tabla");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());

            }
        }
    }
}

