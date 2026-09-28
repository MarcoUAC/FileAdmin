import java.io.IOException;
import java.util.List;
import java.util.Scanner;

public class Practica_01 {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String opcion = "";

        do {
            System.out.println("\n--- Menú ---");
            System.out.println("1. Agregar nota");
            System.out.println("2. Ver todas las notas");
            System.out.println("3. Calcular promedio");
            System.out.println("4. Salir");
            System.out.print("Selecciona una opción: ");

            opcion = scanner.nextLine().trim();

            switch (opcion) {
                case "1":
                    agregarNota(scanner);
                    break;
                case "2":
                    verNotas();
                    break;
                case "3":
                    double promedio = calcularPromedio();
                    if (promedio > 0) {
                        System.out.println("Promedio: " + promedio);
                    }
                    break;
                case "4":
                    System.out.println("¡Hasta luego!");
                    break;
                default:
                    System.out.println("Opción inválida.");
                    break;
            }
        } while (!opcion.equals("4"));

        scanner.close();
    }

    // 1. Método agregarNota
    public static void agregarNota(Scanner scanner) {
        System.out.print("Ingresa la nota: ");
        String entrada = scanner.nextLine().trim();

        try {
            double nota = Double.parseDouble(entrada);
            Act_Stream.guardarNota(String.valueOf(nota));
            System.out.println("Nota guardada.");
        } catch (NumberFormatException e) {
            System.out.println("Debes ingresar un número.");
        } catch (IOException e) {
            System.out.println("Error al guardar la nota: " + e.getMessage());
        }
    }

    // 2. Método verNotas
    public static void verNotas() {
        List<String> notas = null; // Variable declarada fuera del try
        try {
            notas = Act_Stream.leerNotas();
            if (notas.isEmpty()) {
                System.out.println("No hay notas registradas.");
            } else {
                System.out.println("\n--- Lista de Notas ---");
                for (String n : notas) {
                    System.out.println(n);
                }
            }
        } catch (IOException e) {
            System.out.println("Error al leer las notas: " + e.getMessage());
        }
    }

    // 3. Método calcularPromedio
    public static double calcularPromedio() {
        List<String> notas = null;
        try {
            notas = Act_Stream.leerNotas();

            if (notas == null || notas.isEmpty()) {
                System.out.println("No hay notas registradas.");
                return 0;
            }

            double suma = 0;
            int total = 0;

            for (String linea : notas) {
                try {
                    suma += Double.parseDouble(linea);
                    total++;
                } catch (NumberFormatException e) {
                    System.out.println("Línea no numérica ignorada: " + linea);
                }
            }

            if (total == 0) {
                System.out.println("No hay notas registradas.");
                return 0;
            }

            return suma / total;

        } catch (IOException e) {
            System.out.println("Error al leer las notas: " + e.getMessage());
            return 0;
        }
    }
}