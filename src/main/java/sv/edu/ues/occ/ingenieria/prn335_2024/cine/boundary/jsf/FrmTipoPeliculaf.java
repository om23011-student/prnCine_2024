package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.util.List;
@Named
@ViewScoped
public class FrmTipoPeliculaf extends FrmAbstractDataPersist<TipoPelicula> implements Serializable {
    @Inject
    TipoPeliculaBean dataBean;

    public String getTituloDePagina() {
        return "T Pelicula";
    }

    @Override
    protected String getRowKeyFromEntity(TipoPelicula entity) {
        if (entity != null && entity.getIdTipoPelicula() != null) {
            return entity.getIdTipoPelicula().toString();
        }
        return null;
    }

    @Override
    protected TipoPelicula getRowDataFromKey(String rowKey) {
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
    protected List<TipoPelicula> loadRegistros(int desde, int max) {
        return dataBean.findRange(desde,max);
    }

    @Override
    protected void createRegistro(TipoPelicula registro) {
        dataBean.create(registro);
    }

    @Override
    protected TipoPelicula updateRegistro(TipoPelicula registro) {
        return dataBean.update(registro);
    }

    @Override
    protected void deleteRegistro(TipoPelicula registro) {
      dataBean.delete(registro);
    }

    @Override
    protected String tituloDePagina(TipoPelicula entity) {
        return "TipoPelicula";
    }

    @Override
    protected TipoPelicula createNewEntity() {
        return new TipoPelicula();
    }


    public TipoPeliculaBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(TipoPeliculaBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<TipoPelicula> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoPelicula> modelo) {
        this.modelo = modelo;
    }
} //117 || 73

