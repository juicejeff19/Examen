package com.example.ExaPSW;/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.ServletConfig;

/**
 *
 */
@WebServlet(name = "helloServlet", value = "/hello-servlet")
public class HelloServlet extends HttpServlet {
    //variables globales

    private Connection con;
    private Statement set;
    private ResultSet rs;

    //el constructor del servlet
    //nos va a ayudar a inicializar la conexion con la bd

    public void init(ServletConfig cfg) throws ServletException{

        String URL = "jdbc:mysql:3306//localhost/examendb";
        String userName = "root";
        String password = "Patopato612:";

        try{
            Class.forName("com.mysql.jdbc.Driver");
            URL = "jdbc:mysql://localhost/examendb";
            con = DriverManager.getConnection(URL, userName, password);
            set = con.createStatement();
            System.out.println("Conexion exitosa");

        }catch(Exception e){

            System.out.println("Conexion no exitosa");
            System.out.println(e.getMessage());
            System.out.println(e.getStackTrace());

        }
    }

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request que sirve para peticiones del cliente
     * @param response servlet response que sirve para dar respuestas por parte del servidor
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request,
                                  HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */

            String nom, con;

            nom = request.getParameter("neim");
            con = request.getParameter("contr");
            /*
            Una vez que tengamos los datos vamos a insertarlos en la bd

            insert into nombre_tabla (definicion_atributo, definicion_atributo, ...)
            values ("valores_cadena", valores_numericos, ....);

            */

            try{

                String q = "insert into usuarios "
                        + "(nombre, contrsena) "
                        + "values ('"+nom+"', '"+con+"')";

                //ejecutar la sentencia
                set.executeUpdate(q);

                System.out.println("Registro exitoso");



                out.println("<!DOCTYPE html>");
                out.println("<html>");
                out.println("<head>");
                out.println("<title>Servlet Registro</title>");
                out.println("</head>");
                out.println("<body>"
                        + "Tu nombre es: " + nom);
                out.println("<br>"
                        + "Tu contraseña es: " + con
                        + "<br>");
                out.println("<h1>Registro Exitoso</h1>"
                        + "<a href='registro.html'>Regresar a la pagina principal</a>"
                        + "<br>"
                        + "<a href='Consultar'>Consultar Tabla General de Usuarios</a>");
                out.println("</body>");
                out.println("</html>");

            }catch(Exception e){

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
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */

    //hace falta un destructor el destructor libera las conexiones y la memoria de las variables
    public void destroy(){
        try{
            con.close();

        }catch(Exception e){
            super.destroy();

        }
    }



    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}