package com.techlab.articulo.menu;


import java.util.List;
import java.util.Scanner;


import com.techlab.articulo.model.Articulo;
import com.techlab.articulo.model.ArticuloAlimenticio;
import com.techlab.articulo.model.ArticuloElectronico;
import com.techlab.articulo.model.Categoria;


import com.techlab.articulo.repository.Repositorio;


import com.techlab.articulo.utils.Secuencias;
import com.techlab.articulo.utils.Validaciones;


public class MenuArticulos extends Menu {

    
    private final Repositorio<Articulo> repositorioArticulos;

    
    private final Repositorio<Categoria> repositorioCategorias;

    
    public MenuArticulos(
            Scanner scanner,
            Repositorio<Articulo> repositorioArticulos,
            Repositorio<Categoria> repositorioCategorias
    ) {
        
        super(scanner);

        
        this.repositorioArticulos = repositorioArticulos;
        this.repositorioCategorias = repositorioCategorias;
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n---------------- MENÚ DE ARTÍCULOS ----------------");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Consultar artículos");
        System.out.println("3 - Consultar un artículo");
        System.out.println("4 - Modificar un artículo");
        System.out.println("5 - Eliminar un artículo");
        System.out.println("0 - Volver");
        System.out.println("---------------------------------------------------");
    }

    @Override
    public void ejecutar() {
        int opcion;

        do {
            mostrarMenu();
            opcion = leerEntero("Ingrese una opción: ");

            switch (opcion) {
                case 1:
                    ingresarArticulo();
                    break;
                case 2:
                    listarArticulos();
                    break;
                case 3:
                    consultarArticulo();
                    break;
                case 4:
                    modificarArticulo();
                    break;
                case 5:
                    eliminarArticulo();
                    break;
                case 0:
                    System.out.println("\nVolviendo al menú principal...");
                    break;
                default:
                    System.out.println("\nError: la opción ingresada no es válida.");
            }

        } while (opcion != 0);
    }

    
    private void ingresarArticulo() {
        
        if (repositorioCategorias.estaVacio()) {
            System.out.println("\nNo es posible crear artículos porque no hay categorías cargadas.");
            System.out.println("Primero debe ingresar al menos una categoría.");
            return;
        }

        System.out.println("\nAlta de artículo");
        System.out.println("1 - Artículo electrónico");
        System.out.println("2 - Artículo alimenticio");

        int tipo;
        do {
            tipo = leerEntero("Seleccione el tipo de artículo: ");
            if (tipo != 1 && tipo != 2) {
                System.out.println("Error: debe elegir 1 o 2.");
            }
        } while (tipo != 1 && tipo != 2);

        String nombre = pedirNombreArticulo();
        double precio = pedirPrecioArticulo();
        Categoria categoria = pedirCategoriaExistente();

        // Validamos duplicados lógicos.
        if (existeArticuloDuplicado(nombre, categoria, -1)) {
            System.out.println("\nError: ya existe un artículo con ese nombre dentro de esa categoría.");
            return;
        }

        
        int codigo = Secuencias.generarCodigoArticulo();

        Articulo nuevoArticulo;

        if (tipo == 1) {
            int garantiaMeses = pedirGarantia();

           
            nuevoArticulo = new ArticuloElectronico(codigo, nombre, precio, categoria, garantiaMeses);
        } else {
            int diasParaVencimiento = pedirDiasParaVencimiento();

            
            nuevoArticulo = new ArticuloAlimenticio(codigo, nombre, precio, categoria, diasParaVencimiento);
        }

        
        boolean agregado = repositorioArticulos.agregar(nuevoArticulo);

        if (agregado) {
            System.out.println("\nArtículo ingresado correctamente.");
            System.out.println(nuevoArticulo);
        } else {
            System.out.println("\nError: no se pudo guardar el artículo.");
        }
    }

    
    private void listarArticulos() {
        List<Articulo> articulos = repositorioArticulos.listar();

        if (articulos.isEmpty()) {
            System.out.println("\nNo hay artículos cargados para mostrar.");
            return;
        }

        System.out.println("\nListado de artículos:");
        for (Articulo articulo : articulos) {
            System.out.println(articulo);
        }
    }

    
    private void consultarArticulo() {
        if (repositorioArticulos.estaVacio()) {
            System.out.println("\nNo hay artículos cargados.");
            return;
        }

        listarArticulos();

        int codigo = leerEntero("\nIngrese el código del artículo a consultar: ");

        Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("\nError: no existe un artículo con ese código.");
            return;
        }

        System.out.println("\nArtículo encontrado:");
        System.out.println(articulo);
    }

    
    private void modificarArticulo() {
        if (repositorioArticulos.estaVacio()) {
            System.out.println("\nNo hay artículos cargados.");
            return;
        }

        listarArticulos();

        int codigo = leerEntero("\nIngrese el código del artículo a modificar: ");
        Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("\nError: no existe un artículo con ese código.");
            return;
        }

        System.out.println("\nArtículo seleccionado:");
        System.out.println(articulo);

        // Variable temporal para validar duplicados antes de confirmar.
        String nuevoNombre = articulo.getNombre();
        Categoria nuevaCategoria = articulo.getCategoria();

        if (leerSiNo("\n¿Desea modificar el nombre? (S/N): ")) {
            nuevoNombre = pedirNombreArticulo();
        }

        if (leerSiNo("¿Desea modificar el precio? (S/N): ")) {
            articulo.setPrecio(pedirPrecioArticulo());
        }

        if (leerSiNo("¿Desea modificar la categoría? (S/N): ")) {
            nuevaCategoria = pedirCategoriaExistente();
        }

        
        if (existeArticuloDuplicado(nuevoNombre, nuevaCategoria, articulo.getCodigo())) {
            System.out.println("\nError: ya existe otro artículo con ese nombre dentro de esa categoría.");
            return;
        }

        
        articulo.setNombre(nuevoNombre);
        articulo.setCategoria(nuevaCategoria);

        
        if (articulo instanceof ArticuloElectronico electronico) {
            if (leerSiNo("¿Desea modificar la garantía? (S/N): ")) {
                electronico.setGarantiaMeses(pedirGarantia());
            }
        }

        
        if (articulo instanceof ArticuloAlimenticio alimenticio) {
            if (leerSiNo("¿Desea modificar los días para vencimiento? (S/N): ")) {
                alimenticio.setDiasParaVencimiento(pedirDiasParaVencimiento());
            }
        }

        System.out.println("\nArtículo modificado correctamente.");
        System.out.println(articulo);
    }

    
    private void eliminarArticulo() {
        if (repositorioArticulos.estaVacio()) {
            System.out.println("\nNo hay artículos cargados.");
            return;
        }

        listarArticulos();

        int codigo = leerEntero("\nIngrese el código del artículo a eliminar: ");
        Articulo articulo = repositorioArticulos.buscarPorCodigo(codigo);

        if (articulo == null) {
            System.out.println("\nError: no existe un artículo con ese código.");
            return;
        }

        System.out.println("\nArtículo seleccionado:");
        System.out.println(articulo);

        boolean confirmar = leerSiNo("¿Confirma la eliminación? (S/N): ");

        if (!confirmar) {
            System.out.println("\nOperación cancelada por el usuario.");
            return;
        }

        boolean eliminado = repositorioArticulos.eliminar(articulo);

        if (eliminado) {
            System.out.println("\nArtículo eliminado correctamente.");
        } else {
            System.out.println("\nError: no se pudo eliminar el artículo.");
        }
    }

   
    private String pedirNombreArticulo() {
        while (true) {
            String nombre = leerTexto("Ingrese el nombre del artículo: ");

            if (!Validaciones.validarTextoNoVacio(nombre)) {
                System.out.println("Error: el nombre no puede estar vacío.");
                continue;
            }

            if (!Validaciones.validarLongitudMaxima(nombre, 50)) {
                System.out.println("Error: el nombre no puede superar los 50 caracteres.");
                continue;
            }

            return nombre.trim();
        }
    }

    
    private double pedirPrecioArticulo() {
        while (true) {
            double precio = leerDouble("Ingrese el precio del artículo: ");

            if (!Validaciones.validarNoNegativo(precio)) {
                System.out.println("Error: el precio no puede ser negativo.");
                continue;
            }

            return precio;
        }
    }

   
    private int pedirGarantia() {
        while (true) {
            int garantia = leerEntero("Ingrese la garantía en meses: ");

            if (!Validaciones.validarNoNegativo(garantia)) {
                System.out.println("Error: la garantía no puede ser negativa.");
                continue;
            }

            return garantia;
        }
    }

    
    private int pedirDiasParaVencimiento() {
        while (true) {
            int dias = leerEntero("Ingrese los días para vencimiento: ");

            if (!Validaciones.validarNoNegativo(dias)) {
                System.out.println("Error: los días para vencimiento no pueden ser negativos.");
                continue;
            }

            return dias;
        }
    }

    
    private Categoria pedirCategoriaExistente() {
        while (true) {
            System.out.println("\nCategorías disponibles:");
            for (Categoria categoria : repositorioCategorias.listar()) {
                System.out.println(categoria);
            }

            int codigoCategoria = leerEntero("Ingrese el código de la categoría: ");
            Categoria categoria = repositorioCategorias.buscarPorCodigo(codigoCategoria);

            if (categoria == null) {
                System.out.println("Error: la categoría ingresada no existe.");
                continue;
            }

            return categoria;
        }
    }

    
    private boolean existeArticuloDuplicado(String nombre, Categoria categoria, int codigoExcluir) {
        for (Articulo articulo : repositorioArticulos.listar()) {
            boolean mismoCodigo = articulo.getCodigo() == codigoExcluir;
            boolean mismoNombre = articulo.getNombre().equalsIgnoreCase(nombre.trim());
            boolean mismaCategoria = articulo.getCategoria().getCodigo() == categoria.getCodigo();

            if (!mismoCodigo && mismoNombre && mismaCategoria) {
                return true;
            }
        }
        return false;
    }
}



public class MenuArticulos extends Menu {

    public MenuArticulos(java.util.Scanner scanner) {
        super(scanner);
    }

    @Override
    public void mostrarMenu() {
        System.out.println("\n--- MENÚ ARTÍCULOS ---");
        System.out.println("1 - Ingresar artículo");
        System.out.println("2 - Listar artículos");
        System.out.println("3 - Consultar artículo");
        System.out.println("4 - Modificar artículo");
        System.out.println("5 - Eliminar artículo");
        System.out.println("0 - Volver");
    }

    @Override
    public void ejecutar() {
        // TODO:
        // Implementar el loop del menú y llamar a los métodos correspondientes.
    }

    // TODO:
    // Implementar todos los métodos del CRUD de artículos.
}
