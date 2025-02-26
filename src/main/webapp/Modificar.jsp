<%@ page import="java.sql.*" %>
<%@ page import="project.DatabaseConnection" %>
<%@ page import="java.io.StringWriter, java.io.PrintWriter" %>
<%
    String idParam = request.getParameter("id");
    String titol = request.getParameter("titol");
    String isbn = request.getParameter("isbn");
    String anyPublicacioParam = request.getParameter("any_publicacio");

    if (idParam != null && !idParam.isEmpty() &&
        titol != null && !titol.isEmpty() &&
        isbn != null && !isbn.isEmpty() &&
        anyPublicacioParam != null && !anyPublicacioParam.isEmpty()) {
        
        int id = Integer.parseInt(idParam);
        int anyPublicacio = Integer.parseInt(anyPublicacioParam);
        try (Connection conn = DatabaseConnection.getConnectionInstance()) {

            String sql = "UPDATE llibres SET titol = ?, isbn = ?, any_publicacio = ? WHERE ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setString(1, titol);
                stmt.setString(2, isbn);
                stmt.setInt(3, anyPublicacio);
                stmt.setInt(4, id);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    out.println("<h1 class='text-success'>Libro modificado correctamente</h1>");
                } else {
                    out.println("<h1 class='text-warning'>No se ha encontrado un libro con el ID especificado</h1>");
                }
            }
        } catch (SQLException e) {
            out.println("<h1 class='text-danger'>Error al modificar el libro:</h1>");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            out.println("<pre>" + sw.toString() + "</pre>");
        }
    } else {
        out.println("<h1>Por favor, completa todos los campos</h1>");
    }
%>