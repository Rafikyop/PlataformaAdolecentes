package com.plataforma.proyecto.dto;

public class UsuarioDTO {
    private int id;
    private String nombre;
    private String tipo;
    private String email;
    private String contraseña;


    // Constructor vacío
    public UsuarioDTO() {}

    
    // Constructor sin ID (para inserciones)
    public UsuarioDTO(String nombre, String tipo, String email, String contraseña) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.email = email;
        this.contraseña = contraseña;
    }

    // Constructor con ID (opcional)
    public UsuarioDTO(int id, String nombre, String tipo, String email, String contraseña) {
        this.id = id;
        this.nombre = nombre;
        this.tipo = tipo;
        this.email = email;
        this.contraseña = contraseña;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
}
