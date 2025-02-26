<%@ page import="java.sql.*" %>
<%@ page import="project.DatabaseConnection" %>
<%@ page import="java.io.StringWriter, java.io.PrintWriter" %>
<%
    String idParam = request.getParameter("id");

    if (idParam == null) {
%>
        <form method="get">
            <label for="id">ID del libro a eliminar:</label>
            <input type="text" name="id" id="id" required>
            <button type="submit">Eliminar</button>
        </form>
<%
    } else if (!idParam.isEmpty()) {
        int id = Integer.parseInt(idParam);
        try (Connection conn = DatabaseConnection.getConnectionInstance()) {

            String sql = "DELETE FROM llibres WHERE ID = ?";
            try (PreparedStatement stmt = conn.prepareStatement(sql)) {
                stmt.setInt(1, id);
                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    out.println("<h1 class='text-success'>Libro eliminado correctamente</h1>");
                } else {
                    out.println("<h1 class='text-warning'>No se ha encontrado un libro con el ID especificado</h1>");
                }
            }
        } catch (SQLException e) {
            out.println("<h1 class='text-danger'>Error al eliminar el libro:</h1>");
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            out.println("<pre>" + sw.toString() + "</pre>");
        }
    } else {
        out.println("<h1>Por favor, proporciona un ID válido</h1>");
    }
%>