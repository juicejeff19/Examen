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

@WebServlet(name = "Actualizar", value = "/Actualizar")
public class Actualizar extends HttpServlet {

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
        try (PrintWriter out = response.getWriter()){

            try {
                //codigo java para la consulta
                String nom, cont;

                //tenemos que crear la querry
                String q = "update usuarios set ";
                //"update mregistro set apmat_usu =" apmat;

                set = con.createStatement();

                cont = request.getParameter("contcambiar");

                if (cont == "") {

                    out.println("No hay contraseña.");

                } else {
                    String cont1 = request.getParameter("contcambiar");

                    nom = request.getParameter("nombre");

                    if (nom != "") {

                        set.executeUpdate(q + "nombre ='" + nom + "' where contrsena = '"+cont1+"'");
                    }
                }
                //rs.close();
                set.close();

                System.out.println("Modificación Exitosa");

            } catch (Exception e) {
                System.out.println("Error al realizar la modificación");
                System.out.println(e.getMessage());
                System.out.println(e.getStackTrace());

            }
            out.println("</table>");

            out.println("<br>"
                    + "<a href='index.html'>Regresar a la pagina principal</a>");
            out.println("</body>");
            out.println("</html>");
        }
    }

}
