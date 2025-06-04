package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Greedy {
    private int cantidadCandidatosConsiderados;
    private int piezasAcumuladas; // Suma acumulada para evitar calcularla en cada iteración
    private List<Maquina> S; // Solución

    public Greedy() {
        this.cantidadCandidatosConsiderados = 0;
        this.piezasAcumuladas = 0;
        this.S = new ArrayList<>();
    }

    /* Estrategia Greedy:
     * - ¿Cuáles son los candidatos?
     * Inicialmente el conjunto M contiene todos los candidatos, es decir, todas las máquinas disponibles.
     *
     * - Estrategia de selección de candidatos.
     * Se ordenan las máquinas por piezas descendente, para utilizar el Longest Processing Time First y minimizar
     * la cantidad de puestas en funcionamiento totales.
     * En cada paso se selecciona la máquina más productiva que no haga superar el total requerido.
     * El algoritmo finaliza cuando la suma de piezas alcanza el objetivo o no hay más máquinas posibles.
     * Se mide el costo considerando la cantidad de candidatos evaluados.
     *
     * - Consideraciones respecto a encontrar o no solución.
     * No garantiza una solución ni la óptima, ya que se basa en decisiones locales.
     *
     * - Complejidad O(n log n), correspondiente con el sort, siendo n la cantidad de máquinas disponibles.
     * Quitar el sort bajaría la complejidad a O(n) pero perderíamos el enfoque de
     * minimizar la cantidad de puestas en funcionamiento totales.

La solución puede ser mucho peor (más máquinas encendidas o incluso no llegar a una solución).
     */
    public List<Maquina> greedy(List<Maquina> M, int piezasTotales) {
        // Ordenar las máquinas por piezas desc
        // Complejidad sort O(n log n)
        Collections.sort(M, new ComparadorPiezasDesc()); // Longest Processing Time First

        int i = 0;

        // Complejidad O(n) en el peor de los casos
        while (i < M.size() && !solucion(S, piezasTotales)) {
            Maquina x = M.get(i);
            this.cantidadCandidatosConsiderados++;

            if (factible(S, x, piezasTotales)) {
                S.add(x);
                piezasAcumuladas += x.getPiezas();
            } else {
                i++; // Probar con la siguiente máquina
            }
        }

        if (solucion(S, piezasTotales)) {
            System.out.println("Solución obtenida.");
            System.out.println("  → Secuencia de máquinas: " + this.S);
            System.out.println("  → Piezas producidas: " + this.piezasAcumuladas);
            System.out.println("  → Puestas en funcionamiento: " + this.S.size());
            System.out.println("  → Cantidad de candidatos considerados: " + this.cantidadCandidatosConsiderados);
            return this.S;
        }

        return null;
    }

    private boolean solucion(List<Maquina> S, int piezasTotales) {
        return piezasAcumuladas == piezasTotales;
    }

    // Determina si válido agregar el candidato seleccionado a la solución.
    private boolean factible(List<Maquina> S, Maquina x, int piezasTotales) {
        return (piezasAcumuladas + x.getPiezas() <= piezasTotales);
    }
}
