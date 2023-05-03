package controller.dao;

import java.util.List;



public interface IDao {

    public int inserir(Object obj);
    public int atualizar(Object obj);
    public List<Object> listar();
    public List<Object> pesquisarPorId(int id);
    public List<Object> pesquisarPorNome(String nome);
    
}
