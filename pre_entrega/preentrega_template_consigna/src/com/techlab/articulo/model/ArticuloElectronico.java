package com.techlab.articulo.model;

/**
 * CONSIGNA DE ESTA CLASE
 * ------------------------------------------------------------
 * Esta clase debe heredar de Articulo.
 *
 * Representa un artículo electrónico.
 *
 * ATRIBUTO ESPECÍFICO
 * ------------------------------------------------------------
 * - garantiaMeses : int
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
 * - si la garantía supera 12 meses, aplicar un recargo
 * - si no, dejar el precio igual
 *
 * Lo importante no es la regla comercial exacta,
 * sino mostrar que cada subtipo implementa el cálculo de manera distinta.
 */
public class ArticuloElectronico extends Articulo {

    private int garantiaMeses;

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
        // Implementar lógica propia del artículo electrónico.
        return 0;
    }
}
