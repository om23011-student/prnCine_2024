package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;

import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FrmPeliculaf extends FrmAbstractDataPersist<Pelicula> implements Serializable {
    @Inject
    PeliculaBean pBean;

    @Inject
    FacesContext facesContext;

    @Inject
    FrmPeliculaCaracteristicaf  frmPeliculaCaracteristicaf;

    public  void cambiarTab(TabChangeEvent tce){
        if(tce.getTab().getTitle().equals("Tipos")){
            if(this.registro!=null && this.frmPeliculaCaracteristicaf!=null){
                this.frmPeliculaCaracteristicaf.setIdPelicula(this.registro.getIdPelicula());
            }
        }

    }













    public String getTituloDePagina(){
        return "Pelicula";
    }

    @Override
    protected String getRowKeyFromEntity(Pelicula entity) {
        if (entity != null && entity.getIdPelicula() != null) {
            return entity.getIdPelicula().toString();
        }
        return null;
    }

    @Override
    protected Pelicula getRowDataFromKey(String rowKey) {
        if (rowKey != null) {
            return pBean.findById(Integer.parseInt(rowKey));
        }
        return null;
    }

    @Override
    protected int countRegistros() {
        return pBean.count().intValue();
    }

    @Override
    protected List<Pelicula> loadRegistros(int desde, int max) {
        return pBean.findRange(desde,max);
    }

    @Override
    protected void createRegistro(Pelicula registro) {
        pBean.create(registro);
    }

    @Override
    protected Pelicula updateRegistro(Pelicula registro) {
        return pBean.update(registro);
    }

    @Override
    protected void deleteRegistro(Pelicula registro) {
        pBean.delete(registro);
    }

    @Override
    protected String tituloDePagina(Pelicula entity) {
        return "Pelicula";
    }

    @Override
    protected Pelicula createNewEntity() {
        return new Pelicula();
    }


    public PeliculaBean getpBean() {
        return pBean;
    }

    public void setpBean(PeliculaBean pBean) {
        this.pBean = pBean;
    }

    public LazyDataModel<Pelicula> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Pelicula> modelo) {
        this.modelo = modelo;
    }


public FrmPeliculaCaracteristicaf getFrmPeliculaCaracteristicaf(){
        return frmPeliculaCaracteristicaf;
    }










}
