package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;

import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;

import java.io.Serializable;
import java.util.List;

public abstract class AbstractFrmsHtml<T> implements Serializable {

    public abstract void btnNuevoHandler(ActionEvent actionEvent);
    // Método para guardar una entidad
    public abstract void btnGuardarHandler(ActionEvent event);

    // Método para modificar una entidad
    public abstract void btnModificarHandler(ActionEvent event);

    // Método para eliminar una entidad
    public abstract void btnEliminarHandler(ActionEvent event);

    // Método para cancelar cualquier operación
    public void btnCancelarHandler(ActionEvent event) {
        setRegistro(null);
        setEstado(ESTADO_CRUD.NINGUNO);
    }

    // Método para seleccionar un registro para edición
    public abstract void btnSeleccionarRegistroHandler(Integer id);



    // Método para manejar el estado CRUD
    public abstract ESTADO_CRUD getEstado();

    public abstract void setEstado(ESTADO_CRUD estado);

    // Obtener y establecer el registro de tipo T
    public abstract T getRegistro();

    public abstract void setRegistro(T registro);

    // Métodos abstractos para manejo de la lista de registros
    public abstract List<T> getRegistros();

    public abstract void setRegistros(List<T> registros);

    // Método utilitario para mostrar mensajes en la interfaz
    protected void agregarMensaje(String summary, String detail, FacesMessage.Severity severity) {
        FacesMessage message = new FacesMessage(severity, summary, detail);
        FacesContext.getCurrentInstance().addMessage(null, message);
    }
}

