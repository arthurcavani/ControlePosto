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
public class ProdutoConveniencia {
    
    
    private int id;
    private String nome;    
    private int quantidade;
    private double vlunitario;
    
   
    public ProdutoConveniencia(){
        
    }

    public ProdutoConveniencia(int id, String nome, int quantidade, double vlunitario) {
        this.id = id;
        this.nome = nome;
        this.quantidade = quantidade;
        this.vlunitario = vlunitario;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public double getVlunitario() {
        return vlunitario;
    }

    public void setVlunitario(double vlunitario) {
        this.vlunitario = vlunitario;
    }

    
}
