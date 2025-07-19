package Tutorial_001;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class print{
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Ingresa tu nombre: ");
        String nombre = scanner.nextLine();

        try {
            FileWriter writer = new FileWriter("nombres.txt", true);
            writer.write(nombre + "\n");
            writer.close();
            System.out.println("El nombre ha sido guardado correctamente en nombres.txt");
        } catch (IOException e) {
            System.out.println("Ha ocurrido un error al guardar el nombre en el archivo.");
            e.printStackTrace();
        }

        scanner.close();
    }
}