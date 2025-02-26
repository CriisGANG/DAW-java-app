package project;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

/**
 * Servlet que maneja las solicitudes GET y realiza una consulta a la base de datos.
 */
@WebServlet("/consulta")
public class Consulta extends HttpServlet {

    /**
     * Maneja las solicitudes GET.
     *
     * @param request  el objeto HttpServletRequest que contiene la solicitud del cliente
     * @param response el objeto HttpServletResponse que contiene la respuesta del servlet
     * @throws ServletException si ocurre un error específico del servlet
     * @throws IOException      si ocurre un error de entrada/salida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        try (Connection conn = DatabaseConnection.getConnectionInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM llibres")) {

            PrintWriter out = response.getWriter();
            out.println("<html><body><h1>Titol</h1><ul>");

            while (rs.next()) {
                String titol = rs.getString("ID");
                String isbn = rs.getString("titol");
                out.println("<li>" + titol + " (" + isbn + ")</li>");
            }

            out.println("</ul></body></html>");
        } catch (Exception e) {
            throw new ServletException("Error al acceder a la base de datos", e);
        }
    }
}
