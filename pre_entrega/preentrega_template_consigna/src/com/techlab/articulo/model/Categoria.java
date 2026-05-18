package com.techlab.articulo.model;

import com.techlab.articulo.interfaces.Identificable;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase representa una categoría del sistema.
 *
 * Se utilizará como atributo dentro de Articulo.
 *
 * ATRIBUTOS OBLIGATORIOS
 * ------------------------------------------------------------
 * - codigo : int
 * - nombre : String
 * - descripcion : String
 *
 * ESTA CLASE DEBE
 * ------------------------------------------------------------
 * - implementar Identificable
 * - tener constructor
 * - tener getters y setters
 * - tener toString()
 *
 * IMPORTANTE
 * ------------------------------------------------------------
 * Luego esta clase tendrá su propio CRUD desde MenuCategorias.
 */
public class Categoria implements Identificable {

    private int codigo;
    private String nombre;
    private String descripcion;

    // TODO:
    // Crear constructor.

    @Override
    public int getCodigo() {
        // TODO:
        return 0;
    }

    // TODO:
    // Crear getters y setters restantes.

    @Override
    public String toString() {
        // TODO:
        return "";
    }
}
