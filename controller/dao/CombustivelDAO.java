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
import principal.Combustivel;


public class CombustivelDAO implements IDao {
    
    private Connection con;
    private PreparedStatement cmd;
    
    public CombustivelDAO(){
        this.con = Conexao.Conectar();
    }

    @Override
    public int inserir(Object obj) {
        try {
            String sql = "INSERT INTO combustivel (nome) VALUES (?)";

            Combustivel c = (Combustivel) obj;
            cmd = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, c.getNome());   
          

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
            String sql = "UPDATE combustivel SET nome=? WHERE id=?";

            Combustivel c = (Combustivel) obj;
            cmd = con.prepareStatement(sql);
            cmd.setString(1, c.getNome());            
            cmd.setInt(2, c.getId());

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
            String sql = "SELECT * FROM combustivel ORDER BY nome";
            
            cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            
            List<Object> lista = new ArrayList<>();
            while(rs.next()){
                Combustivel cat = new Combustivel(
                    rs.getInt("id"),rs.getString("nome"));                    
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
                Combustivel c = new Combustivel();
                c.setId(rs.getInt("id"));                
               
                c.setNome(rs.getString("nome"));
               
                
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
           String sql = "SELECT * FROM combustivel WHERE id = ?;";
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
           String sql = "SELECT * FROM combustivel WHERE nome LIKE ?ORDER by nome;";
            cmd = con.prepareStatement(sql);
            cmd.setString(1, "%" + nome + "%");
            return pesquisar();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }          
        
        
    } 
    }
    

