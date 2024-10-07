package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;



import java.io.Serializable;
import java.util.List;

@Named
@ViewScoped
public class FrmTipoReserva extends AbstractFrmsHtml<TipoReserva> implements Serializable {
    @Inject
    TipoReservaBean trBean;
    @Inject
    private FacesContext facesContext;
    TipoReserva registro;
    List<TipoReserva> registros;
    ESTADO_CRUD estado;
    @PostConstruct
    public void init() {
        estado=ESTADO_CRUD.NINGUNO;
        registros= trBean.findRange(0,10000);
        registro = new TipoReserva();
    }



    @Override
    public void btnNuevoHandler(ActionEvent actionEvent) {
        this.registro= new TipoReserva();
        this.registro.setActivo(true);
        this.estado=ESTADO_CRUD.CREAR;
    }

    @Override
    public void btnGuardarHandler(ActionEvent event) {
        trBean.create(registro);
        this.registro = null;
        this.registros = trBean.findRange(0, 10000);
        this.estado = ESTADO_CRUD.NINGUNO;
        agregarMensaje("Guardado exitosamente", null, FacesMessage.SEVERITY_INFO);
    }

    @Override
    public void btnModificarHandler(ActionEvent event) {
        TipoReserva actualizado = trBean.update(registro);
        if (actualizado != null) {
            this.registro = null;
            this.estado = ESTADO_CRUD.NINGUNO;
            agregarMensaje("Registro modificado con éxito", null, FacesMessage.SEVERITY_INFO);
        } else {
            agregarMensaje("No se pudo modificar el registro", null, FacesMessage.SEVERITY_ERROR);
        }
    }

    @Override
    public void btnEliminarHandler(ActionEvent event) {
        trBean.delete(registro);
        this.registro = null;
        this.registros = trBean.findRange(0, 10000);
        this.estado = ESTADO_CRUD.NINGUNO;
        agregarMensaje("Registro eliminado con éxito", null, FacesMessage.SEVERITY_INFO);
    }

    @Override
    public void btnSeleccionarRegistroHandler(Integer id) {
        if (id != null) {
            this.registro = this.registros.stream().filter(t -> t.getIdTipoReserva().equals(id)).findFirst().orElse(null);
            this.estado = ESTADO_CRUD.MODIFICAR;
        } else {
            this.registro = null;
        }
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    @Override
    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }


    public TipoReserva getRegistro() {
        return registro;
    }

    public void setRegistro(TipoReserva registro) {
        this.registro = registro;
    }

    public List<TipoReserva> getRegistros() {
        return registros;
    }

    public void setRegistros(List<TipoReserva> registros) {
        this.registros = registros;
    }
}
