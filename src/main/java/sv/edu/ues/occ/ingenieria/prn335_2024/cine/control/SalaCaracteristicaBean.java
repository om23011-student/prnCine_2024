package sv.edu.ues.occ.ingenieria.prn335_2024.cine.control;

import jakarta.ejb.LocalBean;
import jakarta.ejb.Stateless;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.SalaCaracteristica;

import java.io.Serializable;

@Stateless
@LocalBean
public class SalaCaracteristicaBean extends AbstractDataPersist<SalaCaracteristica> implements Serializable {
    @PersistenceContext(unitName = "CinePU")
    private EntityManager em;

    public SalaCaracteristicaBean() {
        super(SalaCaracteristica.class);
    }

    @Override
    public EntityManager getEntityManager() {
        return em;
    }
}
