package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_pago",schema = "public")
public class TipoPago implements Serializable {
    @Id
    @Column(name = "id_tipo_pago", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoPago;
    @OneToMany(fetch= FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idTipoPago")
    private List<Pago> pagos;

    @NotBlank(message ="Debe ingresar un nombre valido" )
    @Size(max = 155,min = 3, message = "Debe agregar un nombre entre 3 y 155 caracteres")
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    public TipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
    }

    public TipoPago() {
    }

    public Integer getIdTipoPago() {
        return idTipoPago;
    }

    public void setIdTipoPago(Integer idTipoPago) {
        this.idTipoPago = idTipoPago;
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

    public List<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(List<Pago> pagos) {
        this.pagos = pagos;
    }
}