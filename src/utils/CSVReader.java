package utils;

import main.Maquina;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class CSVReader {
    public CSVReader() { }

    public int readConfiguration (String configurationPath, List<Maquina> maquinas) {
        int piezasTotales = 0;
        ArrayList<String[]> lines = this.readContent(configurationPath);

        for (int i = 0; i < lines.size(); i++) {
            String[] line = lines.get(i);

            if (i == 0) {
                piezasTotales = Integer.parseInt(line[0].trim());
            } else {
                String nombre = line[0].trim();
                int piezas = Integer.parseInt(line[1].trim());

                // Instanciar y agregar Máquina
                Maquina maquina = new Maquina(nombre, piezas);
                maquinas.add(maquina);
            }
        }

        return piezasTotales;
    }

    private ArrayList<String[]> readContent(String path) {
        ArrayList<String[]> lines = new ArrayList<String[]>();

        File file = new File(path);
        FileReader fileReader = null;
        BufferedReader bufferedReader = null;

        try {
            fileReader = new FileReader(file);
            bufferedReader = new BufferedReader(fileReader);
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                line = line.trim();
                lines.add(line.split(","));
            }
        } catch (Exception e) {
            e.printStackTrace();
            if (bufferedReader != null)
                try {
                    bufferedReader.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
        }

        return lines;
    }
}
