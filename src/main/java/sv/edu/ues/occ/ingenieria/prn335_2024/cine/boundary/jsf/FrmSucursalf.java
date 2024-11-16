package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SucursalBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sucursal;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FrmSucursalf extends FrmAbstractDataPersist<Sucursal> implements Serializable {

    @Inject
    private SucursalBean dataBean;

    public String getTituloDePagina() {
        return "Sucursal";
    }

    @Override
    protected String getRowKeyFromEntity(Sucursal entity) {
        if (entity != null && entity.getIdSucursal() != null) {
            return entity.getIdSucursal().toString();
        }
        return null;
    }

    @Override
    protected Sucursal getRowDataFromKey(String rowKey) {
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
    protected List<Sucursal> loadRegistros(int desde, int max) {
        return dataBean.findRange(desde, max);
    }

    @Override
    protected void createRegistro(Sucursal registro) {
        dataBean.create(registro);
    }

    @Override
    protected Sucursal updateRegistro(Sucursal registro) {
        return dataBean.update(registro);
    }

    @Override
    protected void deleteRegistro(Sucursal registro) {
        dataBean.delete(registro);
    }

    @Override
    protected String tituloDePagina(Sucursal entity) {
        return getTituloDePagina();
    }

    @Override
    protected Sucursal createNewEntity() {
        return new Sucursal();
    }

    // Getters y setters
    public SucursalBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(SucursalBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<Sucursal> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Sucursal> modelo) {
        this.modelo = modelo;
    }
}
