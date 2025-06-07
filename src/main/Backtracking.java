package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Backtracking {
    private int cantidadEstadosGenerados;
    private int piezasAcumuladas; // Suma acumulada para evitar calcularla en cada iteración
    private int piezasSolucion; // Suma final de piezas en mejorSolucion
    private List<Maquina> mejorSolucion;

    public Backtracking() {
        this.cantidadEstadosGenerados = 0;
        this.piezasAcumuladas = 0;
        this.piezasSolucion = 0;
        this.mejorSolucion = new ArrayList<>();
    }

    /* Estrategia Backtracking:
     * - ¿Cómo se genera el árbol de exploración?
     * Se exploran todas las posibles combinaciones de máquinas que sumen exactamente la cantidad de piezas requerida.
     * Cada nodo del árbol representa una secuencia parcial de máquinas elegidas hasta el momento.
     *
     * - ¿Cuáles son los estados finales y estados solución?
     * Un estado final puede ser aquel donde se hayan recorrido todas las máquinas, no necesariamente un estado solución.
     * Un estado solución es aquel en el que la suma de piezas alcanza exactamente el total requerido.
     *
     * - Posibles podas.
     * (sumaActual + m.getPiezas() <= piezasTotales)
     * Se realiza una poda cuando al seleccionar una nueva máquina y sumar sus piezas con la suma parcial, supera el total de piezas necesario,
     * evitando continuar por esa rama.
     *
     * Se guarda la mejor solución, es decir, la que utiliza la menor cantidad de puestas en funcionamiento.
     *
     * - Complejidad O(n^k) en el peor de los casos, siendo n la cantidad de máquinas disponibles y k la profundidad del árbol de exploración.
     */
    public List<Maquina> backtracking(List<Maquina> M, int piezasTotales) {
        List<Maquina> solucionActual = new ArrayList<>();
        backtracking(M, piezasTotales, solucionActual);

        System.out.println("Solución obtenida.");
        System.out.println("  → Secuencia de máquinas: " + this.mejorSolucion);
        System.out.println("  → Piezas producidas: " + this.piezasSolucion);
        System.out.println("  → Puestas en funcionamiento: " + this.mejorSolucion.size());
        System.out.println("  → Cantidad de estados generados: " + this.cantidadEstadosGenerados);

        return this.mejorSolucion;
    }

    private void backtracking(List<Maquina> M, int piezasTotales, List<Maquina> solucionActual) {
        // Caso base: Producir las piezas totales
        if (piezasAcumuladas == piezasTotales) {
            if (mejorSolucion.isEmpty() || solucionActual.size() < mejorSolucion.size()) {
                mejorSolucion = new ArrayList<>(solucionActual);
                piezasSolucion = piezasAcumuladas;
            }
            return;
        }

        cantidadEstadosGenerados++;

        // Complejidad O(n), siendo n la cantidad de máquinas disponibles
        for (Maquina m : M) {
            // Poda
            if (piezasAcumuladas + m.getPiezas() <= piezasTotales) {
                // Intento de asignación
                solucionActual.add(m);
                piezasAcumuladas += m.getPiezas();

                // Recursión
                backtracking(M, piezasTotales, solucionActual);

                // Backtrack
                solucionActual.remove(solucionActual.size() - 1);
                piezasAcumuladas -= m.getPiezas();
            }
        }
    }
}
