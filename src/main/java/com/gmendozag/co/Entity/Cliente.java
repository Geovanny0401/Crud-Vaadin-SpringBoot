package com.gmendozag.co.Entity;


import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;


@Entity
public class Cliente {

    public Cliente() {
    }

    public Cliente(String nombre, String apellido) {
        this.nombre=nombre;
        this.apellido=apellido;

    }

    public Cliente(String nombre, String apellido, String telefono, String email) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono=telefono;
        this.email=email;

    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @NotNull(message = "Nombre Requerido")
    @Size(min = 3, max = 50, message = "El nombre debe estar en el rango, como minimo 3 y como maximo 50 caracteres")
    private String nombre;

    private String apellido;

    private String telefono;

    @NotNull(message = "Email es Requerido")
    @Pattern(regexp = ".+@.+\\.[a-z]+", message = "\n" + "Debe ser un correo electrónico válido")
    private String email;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public String toString() {
        return "Cliente{" + "id=" + id + ", nombre='" + nombre + '\'' + ", apellido='" + apellido + '\'' + ",  telefono='" + telefono + '\'' + ", email='" + email + '\'' + '}';
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (this.id == null) {
            return false;
        }
        if (obj instanceof Cliente && obj.getClass().equals(getClass())) {
            return this.id.equals(((Cliente) obj).id);
        }
        return false;
    }
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 43 * hash + (id == null ? 0 : id.hashCode());
        return hash;
    }

}
