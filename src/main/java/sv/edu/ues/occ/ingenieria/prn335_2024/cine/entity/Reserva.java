package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.time.OffsetDateTime;
import java.util.List;

@Entity
@Table(name = "reserva")
public class Reserva implements Serializable {
    @Id
    @Column(name = "id_reserva", nullable = false)
    private Long idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_programacion")
    private Programacion idProgramacion;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_reserva")
    private TipoReserva idTipoReserva;

    @OneToMany(fetch= FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idReserva")
    private List<ReservaDetalle> reservaDetalle;

    @Column(name = "fecha_reserva")
    private OffsetDateTime fechaReserva;

    @Size(max = 155)
    @Column(name = "estado", length = 155)
    private String estado;

    @Lob
    @Column(name = "observaciones")
    private String observaciones;

    public Reserva(Long idReserva) {
        this.idReserva = idReserva;
    }
    public Reserva() {

    }

    public Long getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Long idReserva) {
        this.idReserva = idReserva;
    }

    public Programacion getIdProgramacion() {
        return idProgramacion;
    }

    public void setIdProgramacion(Programacion idProgramacion) {
        this.idProgramacion = idProgramacion;
    }

    public TipoReserva getIdTipoReserva() {
        return idTipoReserva;
    }

    public void setIdTipoReserva(TipoReserva idTipoReserva) {
        this.idTipoReserva = idTipoReserva;
    }

    public OffsetDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(OffsetDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<ReservaDetalle> getReservaDetalle() {
        return reservaDetalle;
    }

    public void setReservaDetalle(List<ReservaDetalle> reservaDetalle) {
        this.reservaDetalle = reservaDetalle;
    }
}