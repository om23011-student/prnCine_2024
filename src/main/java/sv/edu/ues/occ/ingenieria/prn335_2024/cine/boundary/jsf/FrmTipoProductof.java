package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoProductoBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoProducto;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.util.List;
@Named
@ViewScoped
public class FrmTipoProductof extends FrmAbstractDataPersist<TipoProducto> implements Serializable {

    @Inject
    TipoProductoBean dataBean;

    public String getTituloDePagina() {
        return "Tipo de Producto";
    }

    @Override
    protected String getRowKeyFromEntity(TipoProducto entity) {
        if (entity != null && entity.getIdTipoProducto() != null) {
            return entity.getIdTipoProducto().toString();
        }
        return null;
    }

    @Override
    protected TipoProducto getRowDataFromKey(String rowKey) {
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
    protected List<TipoProducto> loadRegistros(int desde, int max) {
            return dataBean.findRange(desde,max);
    }

    @Override
    protected void createRegistro(TipoProducto registro) {
        dataBean.create(registro);
    }

    @Override
    protected TipoProducto updateRegistro(TipoProducto registro) {
        return dataBean.update(registro);
    }

    @Override
    protected void deleteRegistro(TipoProducto registro) {
        dataBean.delete(registro);
    }

    @Override
    protected String tituloDePagina(TipoProducto entity) {
        return "TipoProducto";
    }

    @Override
    protected TipoProducto createNewEntity() {
        return new TipoProducto();
    }


    public TipoProductoBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(TipoProductoBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<TipoProducto> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoProducto> modelo) {
        this.modelo = modelo;
    }
}

