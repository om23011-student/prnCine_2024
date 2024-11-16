package sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "tipo_pelicula",schema = "public")


public class TipoPelicula implements Serializable {
    @Id
    @Column(name = "id_tipo_pelicula", nullable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idTipoPelicula;

    @OneToMany(fetch = FetchType.LAZY,cascade = CascadeType.ALL,mappedBy = "idTipoPelicula")
    private List<PeliculaCaracteristica> PeliculaCaracteristica;

    @NotBlank(message ="Debe ingresar un nombre valido" )
    @Size(max = 155,min = 3, message = "Debe agregar un nombre entre 3 y 155 caracteres")
    private String nombre;

    @Column(name = "activo")
    private Boolean activo;

    @Lob
    @Column(name = "comentarios")
    private String comentarios;

    @Lob
    @Column(name = "expresion_regular")
    private String expresionRegular;

    public TipoPelicula(Integer idTipoPelicula) {
        this.idTipoPelicula = idTipoPelicula;
    }

    public TipoPelicula() {

    }

    public Integer getIdTipoPelicula() {
        return idTipoPelicula;
    }

    public void setIdTipoPelicula(Integer idTipoPelicula) {
        this.idTipoPelicula = idTipoPelicula;
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

    public String getComentarios() {
        return comentarios;
    }

    public void setComentarios(String comentarios) {
        this.comentarios = comentarios;
    }

    public String getExpresionRegular() {
        return expresionRegular;
    }

    public void setExpresionRegular(String expresionRegular) {
        this.expresionRegular = expresionRegular;
    }

    public List<sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica> getPeliculaCaracteristica() {
        return PeliculaCaracteristica;
    }

    public void setPeliculaCaracteristica(List<sv.edu.ues.occ.ingenieria.prn335_2024.cine.entity.PeliculaCaracteristica> peliculaCaracteristica) {
        PeliculaCaracteristica = peliculaCaracteristica;
    }
}