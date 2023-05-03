package controller.dao;

import controller.Conexao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import principal.ProdutoPosto;


public class ProdutoPostoDAO implements IDao {
    
    private Connection con;
    private PreparedStatement cmd;
    
    public ProdutoPostoDAO(){
        this.con = Conexao.Conectar();
    }

    @Override
    public int inserir(Object obj) {
        try {
           String sql = "INSERT INTO bomba (id_combustivel,"
                    + "litros, valorlitro, valortotal)  VALUES (?,"
                    + "?,?,?)";

            ProdutoPosto c = (ProdutoPosto) obj;
            cmd = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
                      
            cmd.setInt(1, c.getId_combustivel());          
            cmd.setInt(2, c.getLitros());                    
            cmd.setDouble(3, c.getVllitro()); 
            cmd.setDouble(4, c.getVltotal());

            if (cmd.executeUpdate() > 0) {
                ResultSet rs = cmd.getGeneratedKeys();
                return rs.next() ? rs.getInt(1) : -1;
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return -1;
        
        } finally {
            Conexao.Desconectar(con);
        }
    }

    @Override
    public int atualizar(Object obj) {
         try {
            String sql = "UPDATE bomba SET id_combustivel=?, litros=?, valorlitro=?, valortotal=?  WHERE id=?";

            ProdutoPosto c = (ProdutoPosto) obj;                      
            cmd.setInt(1, c.getId_combustivel());          
            cmd.setInt(2, c.getLitros());                    
            cmd.setDouble(3, c.getVllitro()); 
            cmd.setDouble(4, c.getVltotal());
            cmd.setInt(5, c.getId());
            if (cmd.executeUpdate() > 0) {
                return c.getId();
            } else {
                return -1;
            }
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return -1;
        } finally {
            Conexao.Desconectar(con);
        }
    }

    @Override
    public List<Object> listar() {
        try {
            String sql = "SELECT * FROM bomba ORDER BY id";
            
            cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            
            List<Object> lista = new ArrayList<>();
            while(rs.next()){
                ProdutoPosto cat = new ProdutoPosto(
                rs.getInt("id"),                    
                rs.getInt("id_combustivel"),                    
                rs.getInt("litros"),                    
                rs.getDouble("valorlitro"),                 
                rs.getDouble("valortotal"));
                lista.add(cat);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }finally{
            Conexao.Desconectar(con);
        }
    }
    private List<Object> pesquisar(){
    try {
            ResultSet rs = cmd.executeQuery();
            List<Object> lista = new ArrayList<>();
            while(rs.next()){
                ProdutoPosto c = new ProdutoPosto();
                c.setId(rs.getInt("id"));                
                c.setId_combustivel(rs.getInt("id_combustivel"));
                c.setLitros(rs.getInt("litros"));
                c.setVllitro(rs.getDouble("valorlitro"));
                c.setVltotal(rs.getDouble("valortotal"));                
                lista.add(c);
            }
            return lista;
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }      
    }

    @Override
    public List<Object> pesquisarPorId(int id) {
      try {
           String sql = "SELECT * FROM bomba WHERE id = ?;";
            cmd = con.prepareStatement(sql);
            cmd.setInt(1, id);
            return pesquisar();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }     
    }

    @Override
    public List<Object> pesquisarPorNome(String nome) {
        try {
           String sql = "SELECT * FROM posto WHERE nome LIKE ?ORDER by nome;";
            cmd = con.prepareStatement(sql);
            cmd.setString(1, "%" + nome + "%");
            return pesquisar();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }          
        
        
    } 
    }
    

