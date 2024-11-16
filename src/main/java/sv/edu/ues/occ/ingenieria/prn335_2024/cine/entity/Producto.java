package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "producto")
public class Producto implements Serializable {
    @Id
    @Column(name = "id_producto", nullable = false)
    private Long idProducto;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_tipo_producto")
    private TipoProducto idTipoProducto;
    @OneToMany(fetch= FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idProducto")
    private List<FacturaDetalleProducto> facturaDetalleProducto;

    @Size(max = 155)
    @Column(name = "nombre", length = 155)
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "descripcion")
    private String descripcion;

    public Producto(Long idProducto) {
        this.idProducto = idProducto;
    }
    public Producto() {

    }

    public Long getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(Long idProducto) {
        this.idProducto = idProducto;
    }

    public TipoProducto getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(TipoProducto idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<FacturaDetalleProducto> getFacturaDetalleProducto() {
        return facturaDetalleProducto;
    }

    public void setFacturaDetalleProducto(List<FacturaDetalleProducto> facturaDetalleProducto) {
        this.facturaDetalleProducto = facturaDetalleProducto;
    }
}