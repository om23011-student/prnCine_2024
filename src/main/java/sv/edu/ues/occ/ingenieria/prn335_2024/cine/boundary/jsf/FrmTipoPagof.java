package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPagoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPago;
import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FrmTipoPagof extends FrmAbstractDataPersist<TipoPago> implements Serializable {
    @Inject
    TipoPagoBean dataBean;


    @Override
    protected String getRowKeyFromEntity(TipoPago entity) {
        if (entity != null && entity.getIdTipoPago() != null) {
            return entity.getIdTipoPago().toString();
        }
        return null;
    }

    @Override
    protected TipoPago getRowDataFromKey(String rowKey) {
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
    protected List<TipoPago> loadRegistros(int desde, int max) {
        return dataBean.findRange(desde,max);
    }

    @Override
    protected void createRegistro(TipoPago registro) {
        dataBean.create(registro);
    }

    @Override
    protected TipoPago updateRegistro(TipoPago registro) {
        return dataBean.update(registro);
    }

    @Override
    protected void deleteRegistro(TipoPago registro) {
      dataBean.delete(registro);
    }
    public String getTituloDePagina() {
        return "Tipo de Pago";
    }
    @Override
    protected String tituloDePagina(TipoPago entity) {
        return getTituloDePagina();
    }

    @Override
    protected TipoPago createNewEntity() {
        return new TipoPago();
    }

    public TipoPagoBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(TipoPagoBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<TipoPago> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoPago> modelo) {
        this.modelo = modelo;
    }


}
