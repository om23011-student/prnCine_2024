package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoAsientoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoAsiento;

import java.io.Serializable;
import java.util.List;
@Named
@ViewScoped
public class FrmTipoAsientof extends FrmAbstractDataPersist<TipoAsiento> implements Serializable {
     @Inject
     TipoAsientoBean dataBean;

    public String getTituloDePagina() {
        return "Tipo de Asiento";
    }


    @Override
    protected String getRowKeyFromEntity(TipoAsiento entity) {
        if (entity != null && entity.getIdTipoAsiento() != null) {
            return entity.getIdTipoAsiento().toString();
        }
        return null;
    }

    @Override
    protected TipoAsiento getRowDataFromKey(String rowKey) {
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
    protected List<TipoAsiento> loadRegistros(int desde, int max) {
        return dataBean.findRange(desde,max);
    }

    @Override
    protected void createRegistro(TipoAsiento registro) {
         dataBean.create(registro);
    }

    @Override
    protected TipoAsiento updateRegistro(TipoAsiento registro) {
        return dataBean.update(registro);
    }

    @Override
    protected void deleteRegistro(TipoAsiento registro) {
      dataBean.delete(registro);
    }

    @Override
    protected String tituloDePagina(TipoAsiento entity) {
        return getTituloDePagina();
    }

    @Override
    protected TipoAsiento createNewEntity() {
        return new TipoAsiento();
    }

    public TipoAsientoBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(TipoAsientoBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<TipoAsiento> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoAsiento> modelo) {
        this.modelo = modelo;
    }
}
