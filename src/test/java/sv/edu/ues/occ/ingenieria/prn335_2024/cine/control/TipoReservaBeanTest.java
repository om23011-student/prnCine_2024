package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import static org.junit.jupiter.api.Assertions.*;

class TipoReservaBeanTest {
    @Test
    void findById() {
        System.out.printf("TipoReservaBeanTest.findById\n");
        final Integer idEsperado=1;
        TipoReserva esperado= new TipoReserva(idEsperado);
        TipoReservaBean cut=new TipoReservaBean();
        assertThrows(IllegalStateException.class, ()->{
            cut.findById(idEsperado);
        });
        EntityManager mock = Mockito.mock(EntityManager.class);
        Mockito.when(mock.find(TipoReserva.class,idEsperado)).thenReturn(esperado);
        cut.em=mock;
        TipoReserva resultado =cut.findById(idEsperado);
        assertNotNull(resultado);
        assertEquals(esperado,resultado);
        assertThrows(IllegalArgumentException.class, ()->{
            cut.findById(null);
        });






        //fail("La prueba fallo con exito");
    }

}