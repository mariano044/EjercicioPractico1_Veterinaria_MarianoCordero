package com.clinicaveterinaria.citas.domain;

/**
 * Objeto de formulario para las consultas de contacto.
 * No es una entidad: no se almacena en la base de datos.
 */
public class ConsultaContacto {

    private String nombreCliente;
    private String correo;
    private String nombreMascota;
    private String mensaje;

    public ConsultaContacto() {
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getNombreMascota() {
        return nombreMascota;
    }

    public void setNombreMascota(String nombreMascota) {
        this.nombreMascota = nombreMascota;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }
}
