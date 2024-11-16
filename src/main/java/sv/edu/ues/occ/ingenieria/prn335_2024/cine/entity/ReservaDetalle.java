package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "reserva_detalle")
public class ReservaDetalle implements Serializable {
    @Id
    @Column(name = "id_reserva_detalle", nullable = false)
    private Long idReservaDetalle;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_reserva")
    private Reserva idReserva;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_asiento")
    private Asiento idAsiento;

    @OneToMany(fetch= FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idReservaDetalle")
    private List<FacturaDetalleSala> facturaDetalleSala;

    @Size(max = 155)
    @Column(name = "estado", length = 155)
    private String estado;

    public ReservaDetalle(Long idReservaDetalle) {
        this.idReservaDetalle = idReservaDetalle;
    }

    public ReservaDetalle() {
    }

    public Long getIdReservaDetalle() {
        return idReservaDetalle;
    }

    public void setIdReservaDetalle(Long idReservaDetalle) {
        this.idReservaDetalle = idReservaDetalle;
    }

    public Reserva getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(Reserva idReserva) {
        this.idReserva = idReserva;
    }

    public Asiento getIdAsiento() {
        return idAsiento;
    }

    public void setIdAsiento(Asiento idAsiento) {
        this.idAsiento = idAsiento;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public List<FacturaDetalleSala> getFacturaDetalleSala() {
        return facturaDetalleSala;
    }

    public void setFacturaDetalleSala(List<FacturaDetalleSala> facturaDetalleSala) {
        this.facturaDetalleSala = facturaDetalleSala;
    }
}