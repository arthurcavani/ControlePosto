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
import principal.ProdutoConveniencia;


public class ProdutoConvenienciaDAO implements IDao {
    
    private Connection con;
    private PreparedStatement cmd;
    
    public ProdutoConvenienciaDAO(){
        this.con = Conexao.Conectar();
    }

    @Override
    public int inserir(Object obj) {
        try {
           String sql = "INSERT INTO conveniencia (nome,"
                    + "valorunitario, quantidade)  VALUES ("
                    + "?,?,?)";

            ProdutoConveniencia c = (ProdutoConveniencia) obj;
            cmd = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
                      
            cmd.setString(1, c.getNome());          
            cmd.setInt(3, c.getQuantidade());                    
            cmd.setDouble(2, c.getVlunitario()); 

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
            String sql = "UPDATE conveniencia SET nome=?, valorunitario=?, quantidade=?  WHERE id=?";

            ProdutoConveniencia c = (ProdutoConveniencia) obj;
            cmd = con.prepareStatement(sql);                     
            cmd.setString(1, c.getNome());          
            cmd.setInt(3, c.getQuantidade());
            cmd.setDouble(2, c.getVlunitario());
            cmd.setInt(4, c.getId());

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
            String sql = "SELECT * FROM conveniencia ORDER BY id";
            
            cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            
            List<Object> lista = new ArrayList<>();
            while(rs.next()){
                ProdutoConveniencia cat = new ProdutoConveniencia(
                rs.getInt("id"),                    
                rs.getString("nome"),                    
                rs.getInt("quantidade"),                    
                rs.getDouble("valorunitario"));                    
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
                ProdutoConveniencia c = new ProdutoConveniencia();
                c.setId(rs.getInt("id")); 
                c.setNome(rs.getString("nome"));
                c.setQuantidade(rs.getInt("quantidade"));
                c.setVlunitario(rs.getDouble("valorunitario"));
                
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
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.      
    }

    @Override
    public List<Object> pesquisarPorNome(String nome) {
        try {
           String sql = "SELECT * FROM conveniencia WHERE nome LIKE ?ORDER by nome;";
            cmd = con.prepareStatement(sql);
            cmd.setString(1, "%" + nome + "%");
            return pesquisar();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }          
        
        
    } 
    }
    

