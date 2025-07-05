package main;

import utils.CSVReader;

import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        List<Maquina> maquinas = new ArrayList<>();

        CSVReader reader = new CSVReader();

        int piezasTotales = reader.readConfiguration("./src/datasets/Configuracion.csv", maquinas);

        System.out.println("Piezas Totales: " + piezasTotales);
        System.out.println("Máquinas: " + maquinas);
        System.out.println();

        System.out.println("==================== Backtracking ====================");
        Backtracking b = new Backtracking();
        b.backtracking(maquinas, piezasTotales);
        System.out.println();

        System.out.println("======================= Greedy =======================");
        Greedy g = new Greedy();
        g.greedy(maquinas, piezasTotales);
    }
}
