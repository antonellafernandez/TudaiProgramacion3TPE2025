package main;

public class Maquina {
    private String nombre;
    private int piezas;

    public Maquina(String nombre, int piezas) {
        this.nombre = nombre;
        this.piezas = piezas;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPiezas() {
        return piezas;
    }

    public void setPiezas(int piezas) {
        this.piezas = piezas;
    }

    @Override
    public String toString() {
        return "Maquina{" +
                "nombre='" + nombre + '\'' +
                ", piezas=" + piezas +
                '}';
    }
}
