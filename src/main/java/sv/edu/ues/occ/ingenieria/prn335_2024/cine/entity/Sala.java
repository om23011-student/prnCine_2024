package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "sala",schema = "public")
@NamedQueries({
        @NamedQuery(name = "Sala.findByIdTipoSala",
                query = "SELECT s FROM SalaCaracteristica sc JOIN   sc.idSala s WHERE sc.idTipoSala.idTipoSala= :idTipoSala  GROUP BY s.idSala ORDER BY s.nombre ASC")
})
public class Sala implements Serializable {
    @Id
    @Column(name = "id_sala", nullable = false)
    private Long idSala;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sucursal")
    private Sucursal idSucursal;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idSala" )
      private List<Asiento> asiento;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idSala" )
    private List<SalaCaracteristica> salaCaracteristica;


    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idSala")
    private List<Programacion> programacion;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    public Sala(Long idSala) {
        this.idSala = idSala;
    }
    public Sala() {

    }

    public Long getIdSala() {
        return idSala;
    }

    public void setIdSala(Long idSala) {
        this.idSala = idSala;
    }

    public Sucursal getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(Sucursal idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<Asiento> getAsiento() {
        return asiento;
    }

    public void setAsiento(List<Asiento> asiento) {
        this.asiento = asiento;
    }

    public List<SalaCaracteristica> getSalaCaracteristica() {
        return salaCaracteristica;
    }

    public void setSalaCaracteristica(List<SalaCaracteristica> salaCaracteristica) {
        this.salaCaracteristica = salaCaracteristica;
    }

    public List<Programacion> getProgramacion() {
        return programacion;
    }

    public void setProgramacion(List<Programacion> programacion) {
        this.programacion = programacion;
    }
}