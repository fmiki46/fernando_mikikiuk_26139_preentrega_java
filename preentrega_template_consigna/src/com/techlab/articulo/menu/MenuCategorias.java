package com.techlab.articulo.menu;

import java.util.List;
import java.util.Scanner;

import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.Categoria;

import com.techlab.articulo.repository.Repositorio;

import com.techlab.articulo.utils.Secuencias;
import com.techlab.articulo.utils.Validaciones;


public class MenuCategorias extends Menu {

    private final Repositorio<Categoria> repositorioCategorias;
    private final Repositorio<Articulo> repositorioArticulos;

    public MenuCategorias(
                Scanner scanner,
                Repositorio<Categoria> repositorioCategorias,
                Repositorio<Articulo> repositorioArticulos
    ) {
            super(scanner);
            this.repositorioCategorias = repositorioCategorias;
            this.repositorioArticulos = repositorioArticulos;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--------------- MENÚ DE CATEGORÍAS ---------------");
        System.out.println("1 - Ingresar categoría");
        System.out.println("2 - Consultar categorías");
        System.out.println("3 - Consultar una categoría");
        System.out.println("4 - Modificar una categoría");
        System.out.println("5 - Eliminar una categoría");
        System.out.println("0 - Volver");
        System.out.println("--------------------------------------------------");
    }
    @Override
    public void ejecutar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    ingresarCategoria();
                    break;
                case 2:
                    listarCategorias();
                    break;
                case 3:
                    consultarCategoria();
                    break;
                case 4:
                    modificarCategoria();
                    break;
                case 5:
                    eliminarCategoria();
                    break;
                case 0:
                    System.out.println("\nVolviendo al menú principal...");
                    break;
                default:
                    System.out.println("\nError: la opción ingresada no es válida.");
            }

        } while (opcion != 0);
    }

    private void ingresarCategoria() {
        System.out.println("\nAlta de categoría");

        String nombre = pedirNombreCategoria(-1);
        String descripcion = pedirDescripcionCategoria();

        int codigo = Secuencias.generarCodigoCategoria();

        Categoria categoria = new Categoria(codigo, nombre, descripcion);

        boolean agregada = repositorioCategorias.agregar(categoria);

        if (agregada) {
            System.out.println("\nCategoría ingresada correctamente.");
            System.out.println(categoria);
        } else {
            System.out.println("\nError: no se pudo guardar la categoría.");
        }
    }
    private void listarCategorias() {
        List<Categoria> categorias = repositorioCategorias.listar();

        if (categorias.isEmpty()) {
            System.out.println("\nNo hay categorías cargadas para mostrar.");
            return;
        }

        System.out.println("\nListado de categorías:");
        for (Categoria categoria : categorias) {
            System.out.println(categoria);
        }
    }
    private void consultarCategoria() {
        if (repositorioCategorias.estaVacio()) {
            System.out.println("\nNo hay categorías cargadas.");
            return;
        }

        listarCategorias();

        int codigo = leerEntero("\nIngrese el código de la categoría a consultar: ");
        Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

        if (categoria == null) {
            System.out.println("\nError: no existe una categoría con ese código.");
            return;
        }

            System.out.println("\nCategoría encontrada:");
            System.out.println(categoria);
    }

    private void eliminarCategoria(){
        if (repositorioCategorias.estaVacio()) {
            System.out.println("\ No hay Categorias cargadas. ");
            return;
        }

        listarCategorias();

        int codigo = leerEntero("\nIngrese el código de la categoría a eliminar: ");
        Categoria categoria = repositorioCategorias.buscarPorCodigo(codigo);

        if (categoria == null) {
            System.out.println("\nError: no existe una categoría con ese código.");
            return;
        }

        if (categoriaTieneArticulosAsociados(categoria)) {
            System.out.println("\nNo se puede eliminar la categoría porque tiene artículos asociados.");
            return;
        }
        System.out.println("\nCategoría seleccionada:");
        System.out.println(categoria);

        boolean confirmar = leerSiNo("¿Confirma la eliminación? (S/N): ");

        if (!confirmar) {
            System.out.println("\nOperación cancelada por el usuario.");
            return;
        }

        boolean eliminada = repositorioCategorias.eliminar(categoria);

        if (eliminada) {
            System.out.println("\nCategoría eliminada correctamente.");
        } else {
            System.out.println("\nError: no se pudo eliminar la categoría.");
        }

    }    
    
    private String pedirNombreCategoria(int codigoExcluir) {
        while (true) {
            String nombre = leerTexto("Ingrese el nombre de la categoría: ");

            if (!Validaciones.validarTextoNoVacio(nombre)) {
                System.out.println("Error: el nombre no puede estar vacío.");
                continue;
            }

            if (!Validaciones.validarLongitudMaxima(nombre, 40)) {
                System.out.println("Error: el nombre no puede superar los 40 caracteres.");
                continue;
            }

            if (existeNombreCategoria(nombre.trim(), codigoExcluir)) {
                System.out.println("Error: ya existe una categoría con ese nombre.");
                continue;
            }

            return nombre.trim();
        }
    }

    private String pedirDescripcionCategoria() {
        while (true) {
            String descripcion = leerTexto("Ingrese la descripción de la categoría: ");

            if (!Validaciones.validarTextoNoVacio(descripcion)) {
                System.out.println("Error: la descripción no puede estar vacía.");
                continue;
            }

            if (!Validaciones.validarLongitudMaxima(descripcion, 100)) {
                System.out.println("Error: la descripción no puede superar los 100 caracteres.");
                continue;
            }

            return descripcion.trim();
        }
    }
 
    private boolean existeNombreCategoria(String nombre, int codigoExcluir) {
        for (Categoria categoria : repositorioCategorias.listar()) {
            boolean mismoCodigo = categoria.getCodigo() == codigoExcluir;
            boolean mismoNombre = categoria.getNombre().equalsIgnoreCase(nombre);

            if (!mismoCodigo && mismoNombre) {
                return true;
            }
        }
        return false;
    }

    private boolean categoriaTieneArticulosAsociados(Categoria categoria) {
        for (Articulo articulo : repositorioArticulos.listar()) {
            if (articulo.getCategoria().getCodigo() == categoria.getCodigo()) {
                return true;
            }
        }
        return false;
    }

}
