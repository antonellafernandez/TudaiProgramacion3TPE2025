package main;

import java.util.ArrayList;
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
     * (mejorSolucion.isEmpty() || solucionActual.size() < mejorSolucion.size())
     * Además también se realiza una poda considerando tamaño de solucion actual vs mejor encontrada hasta el momento para evitar continuar
     * si la solución actual es mayor o igual a la mejor encontrada.
     *
     * Se guarda la mejor solución, es decir, la que utiliza la menor cantidad de puestas en funcionamiento.
     *
     * - Complejidad O(n^k) en el peor de los casos, siendo n la cantidad de máquinas disponibles y k la profundidad del árbol de exploración.
     */
    public List<Maquina> backtracking(List<Maquina> M, int piezasTotales) {
        List<Maquina> solucionActual = new ArrayList<>();

        // CORRECCIÓN
        // Agregamos el parámetro indice al método recursivo para que el bucle comience desde esa posición y no siempre desde la primera máquina
        // Así nos aseguramos de que no se generen estados duplicados ni soluciones repetidas
        backtracking(M, piezasTotales, solucionActual, 0);

        System.out.println("Solución obtenida.");
        System.out.println("  → Secuencia de máquinas: " + this.mejorSolucion);
        System.out.println("  → Piezas producidas: " + this.piezasSolucion);
        System.out.println("  → Puestas en funcionamiento: " + this.mejorSolucion.size());
        System.out.println("  → Cantidad de estados generados: " + this.cantidadEstadosGenerados);

        return this.mejorSolucion;
    }

    private void backtracking(List<Maquina> M, int piezasTotales, List<Maquina> solucionActual, int indice) {
        cantidadEstadosGenerados++;

        // Caso base: Producir las piezas totales
        if (piezasAcumuladas == piezasTotales) {
            if (mejorSolucion.isEmpty() || solucionActual.size() < mejorSolucion.size()) {
                mejorSolucion = new ArrayList<>(solucionActual);
                piezasSolucion = piezasAcumuladas;
            }
            return;
        }

        // Complejidad O(n), siendo n la cantidad de máquinas a recorrer a partir de la posición índice
        for (int i = indice; i < M.size(); i++) {
            Maquina m = M.get(i);

            // Poda considerando la cantidad de piezas
            if (piezasAcumuladas + m.getPiezas() <= piezasTotales) {
                // Intento de asignación
                solucionActual.add(m);
                piezasAcumuladas += m.getPiezas();

                // CORRECCIÓN
                // Poda considerando tamaño de solucion actual vs mejor encontrada hasta el momento
                if (mejorSolucion.isEmpty() || solucionActual.size() < mejorSolucion.size()) {
                    // Recursión
                    backtracking(M, piezasTotales, solucionActual, i);
                }

                // Backtrack
                solucionActual.remove(solucionActual.size() - 1);
                piezasAcumuladas -= m.getPiezas();
            }
        }
    }
}
