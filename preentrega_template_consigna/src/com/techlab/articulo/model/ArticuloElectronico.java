package com.techlab.articulo.model;


public class ArticuloElectronico extends Articulo {

        private int garantiaMeses;

    
    public ArticuloElectronico(int codigo, String nombre, double precio, Categoria categoria, int garantiaMeses) {
        
        super(codigo, nombre, precio, categoria);

        
        this.garantiaMeses = garantiaMeses;
    }

    public int getGarantiaMeses() {
        return garantiaMeses;
    }

    public void setGarantiaMeses(int garantiaMeses) {
        this.garantiaMeses = garantiaMeses;
    }

    @Override
    public String getTipoArticulo() {
        return "Electrónico";
    }

    @Override
    protected String getDetalleEspecifico() {
        return "garantía=" + garantiaMeses + " meses";
    }

    
    @Override
    public double calcularPrecioFinal() {
        if (garantiaMeses > 12) {
            return precio * 1.10;
        }
        return precio;
    }
}
