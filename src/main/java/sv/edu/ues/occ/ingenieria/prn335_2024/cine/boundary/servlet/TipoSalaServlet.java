package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.servlet;

import jakarta.inject.Inject;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.util.List;

@WebServlet(name = "TipoSalaServlet",urlPatterns = "TipoSala")

public class TipoSalaServlet extends HttpServlet {

    @Inject
    public TipoSalaBean tsBean;

@Override
public void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();

    if(req.getParameter("nombre") != null) {
        TipoSala nuevo = new TipoSala();
        nuevo.setNombre(req.getParameter("nombre"));
        nuevo.setActivo(Boolean.valueOf(req.getParameter("activo")));
        nuevo.setComentarios(req.getParameter("comentarios"));
        nuevo.setExpresionRegular(req.getParameter("expresionRegular"));
        try {
            tsBean.create(nuevo);
            out.println("<html>");
            out.println("<body>");
            out.println("<h1>Tipo de sala</h1>");
            out.println("<br>");
            out.println("Tipo Sala guardado correctamente " + nuevo.getIdTipoSala());
            out.println("</body>");
            out.println("</html>");
            return;

        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(500);
            return;
        }
    }
        resp.setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);

}
@Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    resp.setContentType("text/html");
    PrintWriter out = resp.getWriter();
    List<TipoSala> registros = tsBean.findRange(0,100000);
    out.println("<html>");
    out.println("<body>");
    out.println("<h1>Tipo de sala</h1>");
    out.println("<table>");
    out.println("<tr>");
    out.println("<td>ID</td>");
    out.println("<td>Nombre</td>");
    out.println("<td>Activo</td>");
    out.println("<td>Comentarios</td>");
    out.println("<td>Expresion Regular</td>");
    out.println("</tr>");
     StringWriter sw = new StringWriter();
    for (TipoSala registro : registros) {
        sw.append("<tr>").append("<td>").append(registro.getIdTipoSala().toString()).append("</td><td>").append(registro.getNombre()).append("</td><td>").append(registro.getActivo()?"Activo":"Inactivo").append("</td><td>").append(registro.getComentarios()).append("</td><td>").append(registro.getExpresionRegular()).append("</td><tr>");
}
     out.println(sw.toString());
     out.println("</table>");
     out.println("<hr>");
     out.println("</body>");
    out.println("</html>");


}

}
