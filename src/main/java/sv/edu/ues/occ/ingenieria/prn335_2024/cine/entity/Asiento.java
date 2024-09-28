package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "asiento")
public class Asiento implements Serializable {
    @Id
    @Column(name = "id_asiento", nullable = false)
    private Long idAsiento;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_sala")
    private Sala idSala;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idAsiento")
    private List<ReservaDetalle>    reservaDetalle;
    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idAsiento")
    private List<AsientoCaracteristica> asientoCaracteristica;
    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    public Asiento(Long idAsiento) {
        this.idAsiento = idAsiento;
    }
    public Asiento() {

    }

    public Long getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Long idAsiento) {
        this.idAsiento = idAsiento;
    }

    public Sala getIdSala() {
        return idSala;
    }

    public void setIdSala(Sala idSala) {
        this.idSala = idSala;
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

    public List<ReservaDetalle> getReservaDetalle() {
        return reservaDetalle;
    }

    public void setReservaDetalle(List<ReservaDetalle> reservaDetalle) {
        this.reservaDetalle = reservaDetalle;
    }

    public List<AsientoCaracteristica> getAsientoCaracteristica() {
        return asientoCaracteristica;
    }

    public void setAsientoCaracteristica(List<AsientoCaracteristica> asientoCaracteristica) {
        this.asientoCaracteristica = asientoCaracteristica;
    }
}