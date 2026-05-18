package com.techlab.articulo.model;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Articulo.
 *
 * Representa un artículo alimenticio.
 *
 * ATRIBUTO ESPECÍFICO
 * ------------------------------------------------------------
 * - diasParaVencimiento : int
 *
 * ESTA CLASE DEBE
 * ------------------------------------------------------------
 * - tener constructor
 * - tener getters y setters
 * - sobrescribir getTipoArticulo()
 * - sobrescribir calcularPrecioFinal()
 *
 * IDEA DIDÁCTICA PARA calcularPrecioFinal()
 * ------------------------------------------------------------
 * Podés definir una regla propia, por ejemplo:
 * - si faltan pocos días para vencer, aplicar descuento
 * - si no, mantener el precio
 */
public class ArticuloAlimenticio extends Articulo {

    private int diasParaVencimiento;

    // TODO:
    // Crear constructor.

    // TODO:
    // Crear getters y setters.

    @Override
    public String getTipoArticulo() {
        // TODO:
        return "";
    }

    @Override
    public double calcularPrecioFinal() {
        // TODO:
        // Implementar lógica propia del artículo alimenticio.
        return 0;
    }
}
