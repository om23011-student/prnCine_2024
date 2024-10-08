package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;


import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.control.TipoReservaBean;
import sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.TipoReserva;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

@Named
@ViewScoped
public class FrmTipoReservaf implements Serializable {
    @Inject
    FacesContext facesContext;
    ESTADO_CRUD estado;
    @Inject
    TipoReservaBean dataBean;
    TipoReserva registro;
    LazyDataModel<TipoReserva> modelo;

    @PostConstruct
    public void inicializar() {
        estado=ESTADO_CRUD.NINGUNO;
        modelo = new LazyDataModel<TipoReserva>() {
            @Override
            public String getRowKey(TipoReserva object) {
                if (object != null && object.getIdTipoReserva() != null) {
                    return object.getIdTipoReserva().toString();
                }
                return null;
            }

            @Override
            public TipoReserva getRowData(String rowKey) {
                if (rowKey != null && getWrappedData() != null) {
                    TipoReserva tipoReservaSeleccionado = getWrappedData().stream()
                            .filter(r -> rowKey.equals(r.getIdTipoReserva().toString()))
                            .findFirst()
                            .orElse(null);
                    if (tipoReservaSeleccionado != null) {
                        registro = tipoReservaSeleccionado; // Asigna el registro seleccionado

                                estado = ESTADO_CRUD.MODIFICAR; // Cambia el estado a modificar
                    }

                    return tipoReservaSeleccionado; // Devuelve el registro seleccionado
                }
                return null;
            }

            @Override
            public int count(Map<String, FilterMeta> map) {
                try {

                    return dataBean.count().intValue();
                } catch (Exception e) {
                    e.printStackTrace();
                    //Poner mensajes de error al acceso
                }

                return 0;
            }

            @Override
            public List<TipoReserva> load(int desde, int max, Map<String, SortMeta> map, Map<String, FilterMeta> map1) {
                try {
                    if (desde >= 0 && max > 0) {
                        return dataBean.findRange(desde, max);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    //TODO enviat mensaje de error en el repositorio
                }
                return List.of();
            }
        };

    }

    public TipoReservaBean getDataBean() {
        return dataBean;
    }

    public void setDataBean(TipoReservaBean dataBean) {
        this.dataBean = dataBean;
    }

    public LazyDataModel<TipoReserva> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<TipoReserva> modelo) {
        this.modelo = modelo;
    }

    public TipoReserva getRegistro() {
        return registro;
    }

    public void setRegistro(TipoReserva registro) {
        this.registro = registro;
    }

    public void btnCrearHandler(ActionEvent actionEvent) {
        try{
               dataBean.create(registro);
               FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Datos guardados exitosamente", null);
               facesContext.addMessage(null, message);
               this.registro=null;
               estado=ESTADO_CRUD.NINGUNO;

        }catch (Exception e){
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            facesContext.addMessage(null, message);
            e.printStackTrace();

        }

    }
    public void btnNuevoHandler(ActionEvent actionEvent){
        //Validar todo
       this.registro=new TipoReserva();
       this.registro.setActivo(true);
       this.estado=ESTADO_CRUD.CREAR;

    }
    public void btnModificarHandler(ActionEvent actionEvent) {
        try {
            TipoReserva actualizado = dataBean.update(registro);
            if (actualizado != null) {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO,

                        "Éxito", "Registro modificado con éxito.");

                facesContext.addMessage(null, message);
                this.registro = null;
                this.estado = ESTADO_CRUD.NINGUNO;
            } else {
                FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR,

                        "Error", "No se pudo modificar el registro.");
                facesContext.addMessage(null, message);
            }
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",

                    "Error al modificar el registro.");

            facesContext.addMessage(null, message);
            e.printStackTrace();
        }
    }
    public void btnEliminarHandler(ActionEvent actionEvent) {
        try {
            dataBean.delete(registro);
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito",

                    "Registro eliminado con éxito.");

            facesContext.addMessage(null, message);
            this.registro = null;
            this.estado = ESTADO_CRUD.NINGUNO;
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error",

                    "No se pudo eliminar el registro.");

            facesContext.addMessage(null, message);
            e.printStackTrace();
        }
    }
    public void btnCancelarHandler(ActionEvent actionEvent) {
        this.registro = null;
        this.estado = ESTADO_CRUD.NINGUNO;
    }
    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }
}

