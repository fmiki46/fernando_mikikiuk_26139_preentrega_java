package com.techlab.articulo.model;

import com.techlab.articulo.interfaces.Calculable;
import com.techlab.articulo.interfaces.Identificable;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe ser ABSTRACTA.
 *
 * ¿Por qué?
 * Porque no queremos crear objetos "Articulo" genéricos, sino trabajar
 * con tipos concretos de artículos.
 *
 * Esta clase representa todo lo común a cualquier artículo.
 *
 * ATRIBUTOS OBLIGATORIOS
 * ------------------------------------------------------------
 * - codigo : int
 * - nombre : String
 * - precio : double
 * - categoria : Categoria
 *
 * IMPORTANTE
 * ------------------------------------------------------------
 * categoria NO debe ser String.
 * Debe ser un objeto Categoria.
 *
 * ESTA CLASE DEBE
 * ------------------------------------------------------------
 * - implementar Calculable
 * - implementar Identificable
 * - tener constructor
 * - tener getters y setters
 * - tener toString()
 * - declarar un método abstracto:
 *   String getTipoArticulo();
 *
 * OPCIONAL RECOMENDADO
 * ------------------------------------------------------------
 * Podés declarar también otro método abstracto para el detalle específico,
 * por ejemplo:
 * - String getDetalleEspecifico();
 */
public abstract class Articulo implements Calculable, Identificable {

    protected int codigo;
    protected String nombre;
    protected double precio;
    protected Categoria categoria;

    // TODO:
    // Crear constructor completo.

    // TODO:
    // Crear getters y setters.

    public abstract String getTipoArticulo();

    @Override
    public String toString() {
        // TODO:
        // Mostrar todos los datos comunes del artículo.
        // Sugerencia:
        // incluir también tipo y precio final.
        return "";
    }
}
