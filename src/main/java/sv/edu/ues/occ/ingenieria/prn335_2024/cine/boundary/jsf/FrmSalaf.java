package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import org.primefaces.event.TabChangeEvent;
import org.primefaces.model.LazyDataModel;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.PeliculaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.SalaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Pelicula;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.Sala;

import java.io.Serializable;
import java.util.List;

public class FrmSalaf extends FrmAbstractDataPersist<Sala> implements Serializable {
    @Inject
    SalaBean sBean;

    @Inject
    FacesContext facesContext;

    @Inject
    FrmSalaCaracteristicaf frmSalaCaracteristicaf;

    public void cambiarTab(TabChangeEvent tce){
        if (tce.getTab().getTitle().equals("Tipos")){
            if (this.registro!=null && this.frmSalaCaracteristicaf!=null){
                this.frmSalaCaracteristicaf.setIdSala(this.registro.getIdSala());
            }
        }

    }
    public String getTituloDePagina(){
        return "Sala";
    }



    @Override
    protected String getRowKeyFromEntity(Sala entity) {
        if(entity!=null && entity.getIdSala()!=null){
            return entity.getIdSala().toString();

        }
        return null;
    }

    @Override
    protected Sala getRowDataFromKey(String rowKey) {
        if (rowKey != null) {
            return sBean.findById(Integer.parseInt(rowKey));
        }
        return null;
    }

    @Override
    protected int countRegistros() {
        return sBean.count().intValue();
    }

    @Override
    protected List<Sala> loadRegistros(int desde, int max) {
        return sBean.findRange(desde,max);
    }

    @Override
    protected void createRegistro(Sala registro) {
        sBean.create(registro);
    }

    @Override
    protected Sala updateRegistro(Sala registro) {
        return sBean.update(registro);
    }

    @Override
    protected void deleteRegistro(Sala registro) {
        sBean.delete(registro);
    }

    @Override
    protected String tituloDePagina(Sala entity) {
        return "Sala";
    }

    @Override
    protected Sala createNewEntity() {
        return new Sala();
    }

    public SalaBean getpBean() {
        return sBean;
    }

    public void setpBean(SalaBean sBean) {
        this.sBean = sBean;
    }

    public LazyDataModel<Sala> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<Sala> modelo) {
        this.modelo = modelo;
    }

    public FrmSalaCaracteristicaf getFrmSalaCaracteristicaf(){
        return frmSalaCaracteristicaf;
    }

}
