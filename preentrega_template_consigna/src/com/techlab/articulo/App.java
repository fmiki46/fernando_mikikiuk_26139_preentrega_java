package com.techlab.articulo;


import java.util.Scanner;


import com.techlab.articulo.menu.MenuArticulos;
import com.techlab.articulo.menu.MenuCategorias;


import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;


import com.techlab.articulo.repository.Repositorio;

public class App {

    public static void main(String[] args) {

       
        Scanner scanner = new Scanner(System.in);

        
        Repositorio<Categoria> repositorioCategorias = new Repositorio<>();

                Repositorio<Articulo> repositorioArticulos = new Repositorio<>();

        
        MenuCategorias menuCategorias = new MenuCategorias(
                scanner,
                repositorioCategorias,
                repositorioArticulos
        );

        
        MenuArticulos menuArticulos = new MenuArticulos(
                scanner,
                repositorioArticulos,
                repositorioCategorias
        );

       
        int opcion;

        
        do {
            
            System.out.println("\n==================================================");
            System.out.println(" SISTEMA DE GESTIÓN - PREENTREGA JAVA CONSOLA");
            System.out.println("==================================================");
            System.out.println("1 - Menú de artículos");
            System.out.println("2 - Menú de categorías");
            System.out.println("0 - Salir");
            System.out.println("==================================================");

           
            opcion = leerEntero(scanner, "Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    // Si elige 1, ejecutamos el submenú de artículos.
                    menuArticulos.ejecutar();
                    break;

                case 2:
                    // Si elige 2, ejecutamos el submenú de categorías.
                    menuCategorias.ejecutar();
                    break;

                case 0:
                    // Si elige 0, mostramos mensaje de salida.
                    System.out.println("\nGracias por utilizar el sistema. ¡Hasta luego!");
                    break;

                default:
                    // Si la opción no existe, informamos el error.
                    System.out.println("\nError: la opción ingresada no es válida.");
            }

        } while (opcion != 0);

       
        scanner.close();
    }

    private static int leerEntero(Scanner scanner, String mensaje) {
        while (true) {
            try {
                // Mostramos el mensaje de pedido.
                System.out.print(mensaje);

                // Leemos la línea completa y la convertimos a entero.
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                // Si falla la conversión, avisamos y repetimos.
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }
    }
}
