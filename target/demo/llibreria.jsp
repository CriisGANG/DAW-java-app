<%@ page import="java.sql.Connection, java.sql.DriverManager, java.sql.ResultSet, java.sql.Statement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Llibres</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
    <style>
        body {
            padding: 20px;
        }
    </style>
</head>
<body>
    <div class="container">
        <h1 class="my-4">Llibres</h1>
        <div>
            <a href="Insertar.html" class="btn btn-success">Añadir Nuevo Libro</a>
            <a href="Modificar.html" class="btn btn-warning">Modificar Libro</a>
            <a href="Eliminar.jsp" class="btn btn-danger">Eliminar Libro</a>
        </div>
        <div class="table-responsive">
            <table class="table table-striped">
                <thead class="thead-dark">
                    <tr>
                        <th>ID</th>
                        <th>Títol</th>
                        <th>isbn</th>
                        <th>Any Publicacio</th>
                        <th>ID Editorial</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                        // Database connection parameters
                        String url = "jdbc:mariadb://192.168.0.201:3306/la_meva_llibreria";
                        String user = "cristofol";
                        String password = "cristofol";

                        Connection conn = null;
                        Statement stmt = null;
                        ResultSet rs = null;

                        String titol = request.getParameter("titol");
                        String isbn = request.getParameter("isbn");
                        String anyPublicacioParam = request.getParameter("any_publicacio");
                        String idEditorialParam = request.getParameter("ID");

                        try {
                            // Load the MariaDB driver
                            Class.forName("org.mariadb.jdbc.Driver");

                            // Establish the connection
                            conn = DriverManager.getConnection(url, user, password);
                            stmt = conn.createStatement();
                            String query = "SELECT id, titol, isbn, any_publicacio, ID FROM llibres";
                            rs = stmt.executeQuery(query);

                            // Iterate through the result set and print the data
                            while (rs.next()) {
                                int id = rs.getInt("id");
                                titol = rs.getString("titol");
                                isbn = rs.getString("isbn");
                                String anyPublicacio = rs.getString("any_publicacio");
                                String idEditorial = rs.getString("ID");
                    %>
                    <tr>
                        <td><%= id %></td>
                        <td><%= titol %></td>
                        <td><%= isbn %></td>
                        <td><%= anyPublicacio %></td>
                        <td><%= idEditorial %></td>
                    </tr>
                    <%
                            }
                        } catch (Exception e) {
                            e.printStackTrace();
                        } finally {
                            if (rs != null) try { rs.close(); } catch (Exception e) { }
                            if (stmt != null) try { stmt.close(); } catch (Exception e) { }
                            if (conn != null) try { conn.close(); } catch (Exception e) { }
                        }
                    %>
                </tbody>
            </table>
        </div>
    </div>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
</body>
</html>

