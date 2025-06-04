package main;

import java.util.Comparator;

public class ComparadorPiezasDesc implements Comparator<Maquina> {
    @Override
    public int compare(Maquina m1, Maquina m2) {
        return Integer.compare(m2.getPiezas(), m1.getPiezas()); // Orden descendente
    }
}
