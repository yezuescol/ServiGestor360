package com.prueba.prueba.dto;

public class LoginResponse {

    private String mensaje;
    private String correo;
    private String rol;
    private String token;
    private String tipo;

    public LoginResponse() {
    }

    public LoginResponse(String mensaje, String correo, String rol, String token, String tipo) {
        this.mensaje = mensaje;
        this.correo = correo;
        this.rol = rol;
        this.token = token;
        this.tipo = tipo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public String getCorreo() {
        return correo;
    }

    public String getRol() {
        return rol;
    }

    public String getToken() {
        return token;
    }

    public String getTipo() {
        return tipo;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}