package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.servlet.TipoSalaServlet;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

class TipoSalaServletTest {

    @Test
    void doPost() throws IOException {
        System.out.println("TipoSalaServlet.doPost");
       TipoSalaServlet cut = new TipoSalaServlet();
       HttpServletRequest mockReq= Mockito.mock(HttpServletRequest.class);
       HttpServletResponse mockResp= Mockito.mock(HttpServletResponse.class);
       Mockito.when(mockReq.getParameter("nombre")).thenReturn("Chepe");
       TipoSalaBean mockTS=  Mockito.mock(TipoSalaBean.class);
       cut.tsBean= mockTS;
        StringWriter sm= new StringWriter();
        PrintWriter printWriter = new PrintWriter(sm);

        Mockito.when(mockResp.getWriter()).thenReturn(printWriter);
        try {
            cut.doPost(mockReq,mockResp);
        } catch (ServletException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
     Mockito.when(mockReq.getParameter("nombre")).thenReturn(null);
        try{
            cut.doPost(mockReq,mockResp);
        }catch (ServletException e) {
            throw new RuntimeException(e);
        }

        Mockito.verify(mockResp).setStatus(HttpServletResponse.SC_PRECONDITION_FAILED);


        //fail("Esto no funciona!");
    }

    @Test
    void doGet() {
    }
}