package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.Dependent;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.component.log.Log;
import org.primefaces.model.LazyDataModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SalaCaracteristicaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoSalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.SalaCaracteristica;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoPelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoSala;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Named
@Dependent
public class FrmSalaCaracteristicaf extends FrmAbstractDataPersist<SalaCaracteristica> implements Serializable {
    Long IdSala;
    @Inject
    FacesContext facesContext;

    @Inject
    TipoSalaBean tsBean;

    @Inject
    SalaCaracteristicaBean dataBean;

    List<TipoSala> tiposSalaList;

@PostConstruct
@Override
public void inicializar(){
    super.inicializar();
    try{
        this.tiposSalaList = tsBean.findRange(0, Integer.MAX_VALUE);
    }catch (Exception e){
        Logger.getLogger(getClass().getName()).log(Level.SEVERE, e.getMessage(), e);
        facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al cargar"));
    }
}

public SalaCaracteristica buscarPorId(String id){
    if (id!=null && this.modelo!=null){
        return this.modelo.getWrappedData().stream().filter(r->r.getIdSalaCarteristica().toString().equals(id)).findFirst().orElse(null);
    }
    return  null;
}


public String buscarIdPorRegistro(SalaCaracteristica este){
    if (este!=null && este.getIdSalaCarteristica()!=null){
        return este.getIdSalaCarteristica().toString();
    }
    return null;
}



    @Override
    protected String getRowKeyFromEntity(SalaCaracteristica entity) {
        if (entity!=null && entity.getIdSalaCarteristica()!=null){
            return entity.getIdSalaCarteristica().toString();
        }
        return null;
    }

    @Override
    protected SalaCaracteristica getRowDataFromKey(String rowKey) {
        if (rowKey!=null){
            return dataBean.findById(Integer.parseInt(rowKey));
        }
        return null;
    }

    @Override
    protected int countRegistros() {
        return dataBean.count().intValue();
    }

    @Override
    protected List<SalaCaracteristica> loadRegistros(int desde, int max) {
        return dataBean.findRange(desde,max);
    }

    @Override
    protected void createRegistro(SalaCaracteristica registro) {
        dataBean.create(registro);
    }

    @Override
    protected SalaCaracteristica updateRegistro(SalaCaracteristica registro) {
        return dataBean.update(registro);
    }

    @Override
    protected void deleteRegistro(SalaCaracteristica registro) {
        dataBean.delete(registro);
    }

    @Override
    protected String tituloDePagina(SalaCaracteristica entity) {
        return getTituloDePagina();
    }


    protected String getTituloDePagina() {
        return "Sala Caracteristica";
    }


    @Override
    protected SalaCaracteristica createNewEntity() {
        return new SalaCaracteristica();
    }

    public SalaCaracteristicaBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(SalaCaracteristicaBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<SalaCaracteristica> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<SalaCaracteristica> modelo) {
    this.modelo = modelo;}

    public Long getIdSala() {
        return IdSala;
    }

    public void setIdSala(Long idSala) {
        IdSala = idSala;
    }
    public List<TipoSala> getTiposSalaList() {
        return tiposSalaList;
    }

    public void setTiposSalaList(List<TipoSala> tiposSalaList) {
        this.tiposSalaList = tiposSalaList;
    }

    public Integer getIdTipoSalaSeleccionado(){
        if (this.registro!=null && this.registro.getIdTipoSala()!=null){
            return this.registro.getIdTipoSala().getIdTipoSala();
        }
        return null;
    }

    public void setIdTipoSalaSeleccionado(final Integer idTipoSala){
        if (this.registro!=null && this.tiposSalaList!=null && !this.tiposSalaList.isEmpty()){
            this.registro.setIdTipoSala(this.tiposSalaList.stream().filter(r->r.getIdTipoSala().equals(idTipoSala)).findFirst().orElse(null));
        }
    }
}
