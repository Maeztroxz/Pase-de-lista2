package com.example.juana.pasedelista;

public class Campeon {
    int foto;
    String nombre;
    String matricula;
    boolean asistencia;

    public Campeon(int foto, String nombre, String matricula, boolean asistencia) {
        this.foto = foto;
        this.nombre = nombre;
        this.matricula = matricula;
        this.asistencia = asistencia;
    }

    public int getFoto() {
        return foto;
    }

    public void setFoto(int foto) {
        this.foto = foto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public boolean isAsistencia() {
        return asistencia;
    }

    public void setAsistencia(boolean asistencia) {
        this.asistencia = asistencia;
    }
}
