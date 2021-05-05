<%@ page import="java.sql.DriverManager" %>
<%@ page import="java.sql.*" %><%--
  Created by IntelliJ IDEA.
  User: juice
  Date: 5/2/2021
  Time: 6:05 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Helados</title>
    <link rel="stylesheet" href="css/style.css">
</head>
<body>
<nav>
    <div class="logo">
        <img src="css/Heladito2.jpg" alt="Logo">
        <br>
        <p class="logotext">
            BEN AND JERRY’S
        </p>
    </div>
    <ul>
        <li><a href="mostrarHelados.jsp">Productos</a></li>
        <li><a href="registro.html">Registro</a></li>
        <li><a href="loginUsuario.html">Iniciar sesion</a></li>
    </ul>
</nav>

<section id="main-content">
    <div class="login">
        <form method="post">

            <table border="2">
                <tr>
                    <td><h3>idhelados</h3></td>
                    <td><h3>Nombre</h3></td>
                    <td><h3>Sabor 1</h3></td>
                    <td><h3>Sabor 2</h3></td>
                    <td><h3>Sabor 3</h3></td>
                    <td><h3>Precio</h3></td>
                    <td><h3>Descuento</h3></td>
                    <td><h3>Total</h3></td>
                </tr>
                <%
                    try {
                        Class.forName("com.mysql.jdbc.Driver");
                        String url = "jdbc:mysql://localhost/examendb";
                        String username = "root";
                        String password = "Patopato612:";
                        String query = "select * from helados";
                        Connection conn = DriverManager.getConnection(url, username, password);
                        Statement stmt = conn.createStatement();
                        ResultSet rs = stmt.executeQuery(query);
                        while (rs.next()) {

                %>
                <tr>
                    <td><%=rs.getInt("idhelados") %>
                    </td>
                    <td><%=rs.getString("nombre") %>
                    </td>
                    <td><%=rs.getString("sabor1") %>
                    </td>
                    <td><%=rs.getString("sabor2") %>
                    </td>
                    <td><%=rs.getString("sabor3") %>
                    </td>
                    <td><%=rs.getString("precio") %>
                    </td>
                    <td><%=rs.getString("descuento") %>
                    </td>
                    <td><%=rs.getString("total") %>
                    </td>

                </tr>
                <%

                    }
                %>
            </table>
            <a href="index.html">Volver a la página Principal</a>
            <%
                    rs.close();
                    stmt.close();
                    conn.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }


            %>

        </form>
    </div>
</section>
<footer id="main-footer">
    <h3>Nombres: Alcalá Rangel Jeffrey Alexander, Villalobos Contreras Jesus, Veites Uruchurtu Diego</h3>
</footer>
</body>
</html>
