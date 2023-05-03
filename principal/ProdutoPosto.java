/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package principal;

/**
 *
 * @author Arthur
 */
public class ProdutoPosto {
    
    private int id;
    private int id_combustivel;
    private int litros;
    private double vllitro;
    private double vltotal;
    
    public ProdutoPosto() {
}

    public ProdutoPosto(int id, int id_combustivel, int litros, double vllitro, double vltotal) {
        this.id = id;
        this.id_combustivel = id_combustivel;
        this.litros = litros;
        this.vllitro = vllitro;
        this.vltotal = vltotal;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId_combustivel() {
        return id_combustivel;
    }

    public void setId_combustivel(int id_combustivel) {
        this.id_combustivel = id_combustivel;
    }

    public int getLitros() {
        return litros;
    }

    public void setLitros(int litros) {
        this.litros = litros;
    }

    public double getVllitro() {
        return vllitro;
    }

    public void setVllitro(double vllitro) {
        this.vllitro = vllitro;
    }

    public double getVltotal() {
        return vltotal;
    }

    public void setVltotal(double vltotal) {
        this.vltotal = vltotal;
    }

    
}



