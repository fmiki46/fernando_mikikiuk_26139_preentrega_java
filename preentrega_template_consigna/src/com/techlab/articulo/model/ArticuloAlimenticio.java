package com.techlab.articulo.model;


public class ArticuloAlimenticio extends Articulo {

    
    private int diasParaVencimiento;

    
    public ArticuloAlimenticio(int codigo, String nombre, double precio, Categoria categoria, int diasParaVencimiento) {
        super(codigo, nombre, precio, categoria);
        this.diasParaVencimiento = diasParaVencimiento;
    }

    public int getDiasParaVencimiento() {
        return diasParaVencimiento;
    }

    public void setDiasParaVencimiento(int diasParaVencimiento) {
        this.diasParaVencimiento = diasParaVencimiento;
    }

    @Override
    public String getTipoArticulo() {
        return "Alimenticio";
    }

    @Override
    protected String getDetalleEspecifico() {
        return "días para vencimiento=" + diasParaVencimiento;
    }

    
    @Override
    public double calcularPrecioFinal() {
        if (diasParaVencimiento <= 3) {
            return precio * 0.80;
        }

        if (diasParaVencimiento <= 7) {
            return precio * 0.90;
        }

        return precio;
    }
}
