package mx.edu.ittepic.lab_prueba.entidades;

public class Usuario {
    private String Clave;
    private String nombre;
    private double sueldo;
    private String email;

    public Usuario(String clave, String nombre, double sueldo) {
        Clave = clave;
        this.nombre = nombre;
        this.sueldo = sueldo;

    }

    public String getClave() {
        return Clave;
    }

    public void setClave(String clave) {
        Clave = clave;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getSueldo() {
        return sueldo;
    }

    public void setSueldo(double sueldo) {
        this.sueldo = sueldo;
    }

}
