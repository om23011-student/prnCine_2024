package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.AbstractDataPersist;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FrmTipoReservaf extends FrmAbstractDataPersist<TipoReserva> implements Serializable {
     @Inject
      TipoReservaBean dataBean;

    public String getTituloDePagina() {
    return "Tipo de Reserva";
    }

    @Override
    protected String getRowKeyFromEntity(TipoReserva entity) {
        if (entity != null && entity.getIdTipoReserva() != null) {
            return entity.getIdTipoReserva().toString();
        }
        return null;
    }

    @Override
    protected TipoReserva getRowDataFromKey(String rowKey) {
        if (rowKey != null) {
            return dataBean.findById(Integer.parseInt(rowKey));
        }
        return null;
    }


    @Override
    protected int countRegistros() {
        return dataBean.count().intValue();
    }

    @Override
    protected List<TipoReserva> loadRegistros(int desde, int max) {
        return dataBean.findRange(desde,max);
    }

    @Override
    protected void createRegistro(TipoReserva registro) {
             dataBean.create(registro);
    }

    @Override
    protected TipoReserva updateRegistro(TipoReserva registro) {
        return dataBean.update(registro);
    }

    @Override
    protected void deleteRegistro(TipoReserva registro) {
        dataBean.delete(registro);
    }

    @Override
    protected String tituloDePagina(TipoReserva entity) {
        return "";
    }


    @Override
    protected TipoReserva createNewEntity() {
        return new TipoReserva();
    }
}
