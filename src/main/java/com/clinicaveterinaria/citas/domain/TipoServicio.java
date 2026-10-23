package com.clinicaveterinaria.citas.domain;

import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tipo_servicio")
public class TipoServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "nombre", nullable = false, length = 100)
    private String nombre;

    @Column(name = "descripcion", length = 255)
    private String descripcion;

    @OneToMany(mappedBy = "tipoServicio")
    private List<Servicio> serviciosAsociados = new ArrayList<>();

    public TipoServicio() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<Servicio> getServiciosAsociados() {
        return serviciosAsociados;
    }

    public void setServiciosAsociados(List<Servicio> serviciosAsociados) {
        this.serviciosAsociados = serviciosAsociados;
    }

    public int getCantidadServicios() {
        return serviciosAsociados == null ? 0 : serviciosAsociados.size();
    }
}
