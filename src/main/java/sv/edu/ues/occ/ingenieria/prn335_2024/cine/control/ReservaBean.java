package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Reserva;

import java.io.Serializable;
@Stateless
@LocalBean
public class ReservaBean extends AbstractDataPersist<Reserva> implements Serializable {
     @PersistenceContext(name = "CinePU")
     private EntityManager em;
    public ReservaBean() {
        super(Reserva.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
