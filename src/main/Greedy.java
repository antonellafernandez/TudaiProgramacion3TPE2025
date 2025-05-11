package main;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Greedy {
    private int cantidadCandidatosConsiderados;
    private List<Maquina> S; // Solución

    public Greedy() {
        this.cantidadCandidatosConsiderados = 0;
        this.S = new ArrayList<>();
    }

    public List<Maquina> greedy(List<Maquina> M, int piezasTotales) {
        // Ordenar las máquinas por piezas desc
        Collections.sort(M, new ComparadorPiezasDesc()); // Longest Processing Time First

        int i = 0;

        while (i < M.size() && !solucion(S, piezasTotales)) {
            Maquina x = M.get(i);
            this.cantidadCandidatosConsiderados++;

            if (factible(S, x, piezasTotales)) {
                S.add(x);
            } else {
                i++; // Probar con la siguiente máquina
            }
        }

        if (solucion(S, piezasTotales)) {
            System.out.println("Cantidad de candidatos considerados: " + this.cantidadCandidatosConsiderados);
            return S;
        }

        return null;
    }

    private boolean solucion(List<Maquina> S, int piezasTotales) {
        return calcularSolPiezasActuales(S) == piezasTotales;
    }

    private boolean factible(List<Maquina> S, Maquina x, int piezasTotales) {
        return (calcularSolPiezasActuales(S) + x.getPiezas() <= piezasTotales);
    }

    private int calcularSolPiezasActuales(List<Maquina> S) {
        int sum = 0;

        for (Maquina m : S) {
            sum += m.getPiezas();
        }

        return sum;
    }
}
