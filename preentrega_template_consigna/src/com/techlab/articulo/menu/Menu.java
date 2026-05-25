package com.techlab.articulo.menu;

import java.util.Scanner;


public abstract class Menu {

    protected Scanner scanner;

        @scanner

    public Menu(Scanner scanner) {
        this.scanner = scanner;
    }


    public abstract void mostrarMenu();

    @scanner mensaje
    @return

    public abstract void ejecutar(

        while (true) {
            try {
                System.out.print(mensaje);
                return Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número entero válido.");
            }
        }
    );

  
    protected double leerDouble(String mensaje) {
        while (true) {
            try {
                System.out.print(mensaje);
                return Double.parseDouble(scanner.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Error: debe ingresar un número decimal válido.");
            }
        }
    }
    protected String leerTexto(String mensaje) {
        System.out.print(mensaje);
        return scanner.nextLine();
    }
    protected boolean leerSiNo(String mensaje) {
        while (true) {
            System.out.print(mensaje);
            String respuesta = scanner.nextLine().trim().toUpperCase();

            if (respuesta.equals("S")) {
                return true;
            }

            if (respuesta.equals("N")) {
                return false;
            }

            System.out.println("Error: debe ingresar S o N.");
        }
    }
}