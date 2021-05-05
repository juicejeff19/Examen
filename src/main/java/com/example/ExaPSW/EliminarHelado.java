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

@WebServlet(name = "EliminarHelado", value = "/EliminarHelado")
public class EliminarHelado extends HttpServlet {

    private Connection con;
    private Statement set;
    private ResultSet rs;

    public void init(ServletConfig cfg) throws ServletException {

        String URL = "jdbc:mysql:3306//localhost/examendb";
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
            /* TODO output your page here. You may use following sample code. */
            //para eliminar necesitamos obtener el id

            String nom;

            nom = request.getParameter("nomeliminar");

            //preparo mi sentencia
            //delete from tabla where atributo = valor

            String q = "delete from helados where nombre =  '"+nom+"'";



            ((PrintWriter) out).println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Eliminar</title>");
            out.println("<link rel='stylesheet' href='./css/maistrostyle.css'>");
            out.println("</head>");
            out.println("<body>");


            try{

                set.executeUpdate(q);
                System.out.println("Registro eliminado");
                out.println("<h1>Usuario Eliminado</h1>");

            }catch(Exception e){
                out.println("<h1>Usuario No Eliminado</h1>");
                System.out.println("No se pudo eliminar el usuario");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());

            }



            out.println("<br>"
                    + "<a href='index.html'>Regresar a la pagina principal</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    public void destroy(){
        try{
            con.close();
            System.out.println("Se destruyo wiiii");

        }catch(Exception e){
            super.destroy();

        }
    }

}
