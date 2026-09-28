import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Act_Stream {
    private static final String ARCHIVO = "notas.txt";

    // Guardar la nota al final del archivo notas.txt
    public static void guardarNota(String nota) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(ARCHIVO, true))) {
            writer.write(nota);
            writer.newLine();
        }
    }

    // Leer todas las notas registradas en notas.txt
    public static List<String> leerNotas() throws IOException {
        List<String> notas = new ArrayList<>();
        File file = new File(ARCHIVO);
        
        if (!file.exists()) {
            return notas;
        }

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    notas.add(linea.trim());
                }
            }
        }
        return notas;
    }
}