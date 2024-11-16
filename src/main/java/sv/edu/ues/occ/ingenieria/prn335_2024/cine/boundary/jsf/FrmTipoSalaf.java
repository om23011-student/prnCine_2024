package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.io.Serializable;
import java.util.List;


@Named
@ViewScoped
public class FrmTipoSalaf extends FrmAbstractDataPersist<TipoSala> implements Serializable {
    @Inject
     TipoSalaBean dataBean;



    public String getTituloDePagina() {
        return "Tipo de Sala";
    }

    @Override
    protected String getRowKeyFromEntity(TipoSala entity) {
        if (entity != null && entity.getIdTipoSala() != null) {
            return entity.getIdTipoSala().toString();
        }
        return null;
    }

    @Override
    protected TipoSala getRowDataFromKey(String rowKey) {
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
    protected List<TipoSala> loadRegistros(int desde, int max) {
        return dataBean.findRange(desde, max);
    }

    @Override
    protected void createRegistro(TipoSala registro) {
            dataBean.create(registro);
    }



    @Override
    protected TipoSala updateRegistro(TipoSala registro) {
        return dataBean.update(registro);
    }

    @Override
    protected void deleteRegistro(TipoSala registro) {
         dataBean.delete(registro);
    }

    @Override
    protected String tituloDePagina(TipoSala entity) {
        return "TipoSala";
    }

    @Override
    protected TipoSala createNewEntity() {
        return new TipoSala();
    }
    public TipoSalaBean getDataBean() {
        return dataBean;
    }
    public void setDataBean(TipoSalaBean dataBean) {
        this.dataBean = dataBean;
    }
    public LazyDataModel<TipoSala> getModelo() {
        return modelo;
    }
    public void setModelo(LazyDataModel<TipoSala> modelo) {
        this.modelo = modelo;
    }

}

