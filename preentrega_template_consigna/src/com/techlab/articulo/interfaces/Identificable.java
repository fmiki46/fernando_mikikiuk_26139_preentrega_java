package com.techlab.articulo.interfaces;

/**
 * CONSIGNA DE ESTA INTERFAZ
 * ------------------------------------------------------------
 * Esta interfaz se usa para que distintos objetos puedan ser gestionados
 * por el mismo repositorio genérico.
 *
 * Debe ser implementada por:
 * - Categoria
 * - Articulo (o sus subtipos a través de herencia)
 *
 * ¿Por qué?
 * Porque el repositorio genérico necesita un criterio común de búsqueda.
 * En este caso, ese criterio será el código.
 */
public interface Identificable {

    // TODO:
    // Declarar el método getCodigo().
}
