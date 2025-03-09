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
 * Servlet que maneja les sol·licituds GET i realitza una consulta a la base de dades.
 */
@WebServlet("/consulta")
public class Consulta extends HttpServlet {

    /**
     * Maneja les sol·licituds GET.
     *
     * @param request  l'objecte HttpServletRequest que conté la sol·licitud del client
     * @param response l'objecte HttpServletResponse que conté la resposta del servlet
     * @throws ServletException si ocorre un error específic del servlet
     * @throws IOException      si ocorre un error d'entrada/sortida
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");

        // Establir la connexió amb la base de dades i realitzar la consulta
        try (Connection conn = DatabaseConnection.getConnectionInstance();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM llibres")) {

            // Escriure la resposta HTML
            PrintWriter out = response.getWriter();
            out.println("<html><body><h1>Titol</h1><ul>");

            // Iterar sobre els resultats de la consulta
            while (rs.next()) {
                String titol = rs.getString("ID");
                String isbn = rs.getString("titol");
                out.println("<li>" + titol + " (" + isbn + ")</li>");
            }

            out.println("</ul></body></html>");
        } catch (Exception e) {
            throw new ServletException("Error al accedir a la base de dades", e);
        }
    }
}
