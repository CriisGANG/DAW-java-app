package testing;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * Servlet que maneja las solicitudes GET y responde con un mensaje HTML.
 */
// Configuración del servlet usando @WebServlet
@WebServlet("/hello")
public class HelloServlet extends HttpServlet {

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
        // Establece el tipo de contenido de la respuesta
        response.setContentType("text/html");

        // Escribe la respuesta
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head><title>Hello Servlet</title></head>");
        out.println("<body>");
        out.println("<h1>Hello, World!</h1>");
        out.println("<p>This is a simple servlet example.</p>");
        out.println("</body>");
        out.println("</html>");
    }
}
