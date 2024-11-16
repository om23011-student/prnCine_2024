package sv.edu.ues.occ.ingenieria.prn335_2024.cine.boundary.jsf;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.event.ActionEvent;
import jakarta.inject.Inject;
import org.primefaces.model.FilterMeta;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortMeta;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public abstract class FrmAbstractDataPersist<T> implements Serializable {
    @Inject
    protected FacesContext facesContext;
    protected ESTADO_CRUD estado;
    protected LazyDataModel<T> modelo;
    protected T registro;


    @PostConstruct
    public void inicializar() {
        estado = ESTADO_CRUD.NINGUNO;
        modelo = new LazyDataModel<T>() {
            @Override
            public String getRowKey(T object) {
                return getRowKeyFromEntity(object);
            }

            @Override
            public T getRowData(String rowKey) {
                T entity = getRowDataFromKey(rowKey);
                if (entity != null) {
                    registro = entity;
                    estado = ESTADO_CRUD.MODIFICAR;
                }
                return entity;
            }

            @Override
            public int count(Map<String, FilterMeta> map) {
                try {
                    return countRegistros();
                } catch (Exception e) {
                    e.printStackTrace();
                    return 0;
                }
            }

            @Override
            public List<T> load(int desde, int max, Map<String, SortMeta> sortMeta, Map<String, FilterMeta> filterMeta) {
                try {
                    return loadRegistros(desde, max);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                return List.of();
            }
        };
    }

    protected abstract String getRowKeyFromEntity(T entity);

    protected abstract T getRowDataFromKey(String rowKey);

    protected abstract int countRegistros();

    protected abstract List<T> loadRegistros(int desde, int max);

    protected abstract void createRegistro(T registro);

    protected abstract T updateRegistro(T registro);

    protected abstract void deleteRegistro(T registro);

    protected abstract String tituloDePagina(T entity);

    public void btnCrearHandler(ActionEvent actionEvent) {
        try {
            createRegistro(registro);
            facesContext= FacesContext.getCurrentInstance();
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Registro creado con éxito.");
            facesContext.addMessage(null, message);
            this.registro = null;
            this.estado = ESTADO_CRUD.NINGUNO;
        } catch (Exception e) {
            FacesMessage message = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", e.getMessage());
            facesContext.addMessage(null, message);
            e.printStackTrace();
        }
    }

    public void btnNuevoHandler(ActionEvent actionEvent) {
        registro = createNewEntity();
        estado = ESTADO_CRUD.CREAR;
    }

    public void btnModificarHandler(ActionEvent actionEvent) {
        try {
            T actualizado = updateRegistro(registro);
            if (actualizado != null) {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Registro modificado con éxito."));
                reset();
            } else {
                facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo modificar el registro."));
            }
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "Error al modificar el registro."));
            e.printStackTrace();
        }
    }

    public void btnEliminarHandler(ActionEvent actionEvent) {
        try {
            deleteRegistro(registro);
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Éxito", "Registro eliminado con éxito."));
            reset();
        } catch (Exception e) {
            facesContext.addMessage(null, new FacesMessage(FacesMessage.SEVERITY_ERROR, "Error", "No se pudo eliminar el registro."));
            e.printStackTrace();
        }
    }

    public void btnCancelarHandler(ActionEvent actionEvent) {
        reset();
    }

    public void btnGuardarHandler(ActionEvent actionEvent) {

    }

    protected void reset() {
        registro = null;
        estado = ESTADO_CRUD.NINGUNO;
    }

    public LazyDataModel<T> getModelo() {
        return modelo;
    }

    public void setModelo(LazyDataModel<T> modelo) {
        this.modelo = modelo;
    }

    public T getRegistro() {
        return registro;
    }

    public void setRegistro(T registro) {
        this.registro = registro;
    }

    public ESTADO_CRUD getEstado() {
        return estado;
    }

    public void setEstado(ESTADO_CRUD estado) {
        this.estado = estado;
    }

    public FacesContext getFacesContext() {
        return facesContext;
    }

    public void setFacesContext(FacesContext facesContext) {
        this.facesContext = facesContext;
    }

    protected abstract T createNewEntity();
}
