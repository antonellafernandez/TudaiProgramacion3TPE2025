package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Backtracking {
    private int cantidadEstadosGenerados;
    private List<Maquina> mejorSolucion;

    public Backtracking() {
        this.cantidadEstadosGenerados = 0;
        this.mejorSolucion = new ArrayList<>();
    }

    public List<Maquina> backtracking(List<Maquina> M, int piezasTotales) {
        // Ordenar las máquinas por piezas desc
        Collections.sort(M, new ComparadorPiezasDesc()); // Longest Processing Time First

        List<Maquina> solucionActual = new ArrayList<>();
        backtracking(M, piezasTotales, solucionActual);

        System.out.println("Cantidad de estados generados: " + this.cantidadEstadosGenerados);
        return mejorSolucion;
    }

    private void backtracking(List<Maquina> M, int piezasTotales, List<Maquina> solucionActual) {
        int sumaActual = sumaPiezasActuales(solucionActual);

        // Caso base: Producir las piezas totales
        if (sumaActual == piezasTotales) {
            if (mejorSolucion.isEmpty() || solucionActual.size() < mejorSolucion.size()) {
                mejorSolucion = new ArrayList<>(solucionActual);
            }
            return;
        }

        cantidadEstadosGenerados++;

        for (Maquina m : M) {
            // Poda
            if (sumaActual + m.getPiezas() <= piezasTotales) {
                // Intento de asignación
                solucionActual.add(m);

                // Recursión
                backtracking(M, piezasTotales, solucionActual);

                // Backtrack
                solucionActual.remove(solucionActual.size() - 1);
            }
        }
    }

    private int sumaPiezasActuales(List<Maquina> S) {
        int sum = 0;

        for (Maquina m : S) {
            sum += m.getPiezas();
        }

        return sum;
    }
}
