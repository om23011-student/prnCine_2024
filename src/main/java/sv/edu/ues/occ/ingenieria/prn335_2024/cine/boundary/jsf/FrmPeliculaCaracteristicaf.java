package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.LazyDataModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoPeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPelicula;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@Dependent
public class FrmPeliculaCaracteristicaf extends FrmAbstractDataPersist<PeliculaCaracteristica> implements Serializable {
    Long IdPelicula;

    @Inject
    FacesContext facesContext;
    @Inject
    TipoPeliculaBean tpBean;

    @Inject
    PeliculaCaracteristicaBean dataBean;

    List<TipoPelicula> tiposPeliculaList;

    @PostConstruct
    @Override
    public void inicializar(){
        super.inicializar();
        try{
            this.tiposPeliculaList = tpBean.findRange(0, Integer.MAX_VALUE);
        }catch (Exception e){
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al cargar."));
        }
    }

    public PeliculaCaracteristica buscarRegistroPorId(String id){
        if (id!=null && this.modelo!=null){
            return this.modelo.getWrappedData().stream().filter(r -> r.getIdPeliculaCaracteristica().toString().equals(id)).findFirst().orElse(null);
        }
        return null;
    }

    public String buscarIdPorRegistro(PeliculaCaracteristica este){
        if (este!=null && este.getIdPeliculaCaracteristica()!=null){
            return este.getIdPeliculaCaracteristica().toString();
        }
        return null;
    }

    @Override
    protected String getRowKeyFromEntity(PeliculaCaracteristica entity) {
        if (entity != null && entity.getIdPeliculaCaracteristica()!= null) {
            return entity.getIdPeliculaCaracteristica().toString();
        }
        return null;
    }

    @Override
    protected PeliculaCaracteristica getRowDataFromKey(String rowKey) {
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
    protected List<PeliculaCaracteristica> loadRegistros(int desde, int max) {
        return dataBean.findRange(desde,max);
    }

    @Override
    protected void createRegistro(PeliculaCaracteristica registro) {
        dataBean.create(registro);
    }

    @Override
    protected PeliculaCaracteristica updateRegistro(PeliculaCaracteristica registro) {return dataBean.update(registro);}

    @Override
    protected void deleteRegistro(PeliculaCaracteristica registro) {
        dataBean.delete(registro);
    }
    public String getTituloDePagina() {
        return "Pelicula Caracteristica";
    }
    @Override
    protected String tituloDePagina(PeliculaCaracteristica entity) {
        return getTituloDePagina();
    }

    @Override
    protected PeliculaCaracteristica createNewEntity() {
        return new PeliculaCaracteristica();}

    public PeliculaCaracteristicaBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(PeliculaCaracteristicaBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<PeliculaCaracteristica> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<PeliculaCaracteristica> modelo) {this.modelo = modelo;}

    public Long getIdPelicula() {
        return IdPelicula;
    }

    public void setIdPelicula(Long idPelicula) {
        IdPelicula = idPelicula;
    }

    public List<TipoPelicula> getTiposPeliculaList() {
        return tiposPeliculaList;
    }

    public void setTiposPeliculaList(List<TipoPelicula> tiposPeliculaList) {
        this.tiposPeliculaList = tiposPeliculaList;
    }

    public Integer getIdTipoPeliculaSeleccionado(){
        if (this.registro!=null && this.registro.getIdTipoPelicula()!=null){
            return this.registro.getIdTipoPelicula().getIdTipoPelicula();
        }
        return null;
    }

    public void setIdTipoPeliculaSeleccionado(final Integer idTipoPelicula){
        if (this.registro!=null && this.tiposPeliculaList!=null && !this.tiposPeliculaList.isEmpty()){
            this.registro.setIdTipoPelicula(this.tiposPeliculaList.stream().filter(r->r.getIdTipoPelicula().equals(idTipoPelicula)).findFirst().orElse(null));
        }
    }


    //aqui son metodos creados por el ide


}





