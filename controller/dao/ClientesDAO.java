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
import principal.Clientes;


public class ClientesDAO implements IDao {
    
    private Connection con;
    private PreparedStatement cmd;
    
    public ClientesDAO(){
        this.con = Conexao.Conectar();
    }

    @Override
    public int inserir(Object obj) {
        try {
           String sql = "INSERT INTO cliente (id_cidade,nome,"
                    + "cpf)  VALUES (?,"
                    + "?,?)";

            Clientes c = (Clientes) obj;
            cmd = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            cmd.setInt(1, c.getCidade());          
            cmd.setString(2, c.getNome());          
            cmd.setString(3, c.getCpf());                    

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
            String sql = "UPDATE cliente SET id_cidade=?, nome=?, cpf=?  WHERE id=?";

            Clientes c = (Clientes) obj;
            cmd = con.prepareStatement(sql);
            cmd.setInt(1, c.getCidade());          
            cmd.setString(2, c.getNome());          
            cmd.setString(3, c.getCpf());
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
            String sql = "SELECT * FROM cliente ORDER BY nome";
            
            cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            
            List<Object> lista = new ArrayList<>();
            while(rs.next()){
                Clientes cat = new Clientes(
                rs.getInt("id"),                    
                rs.getString("nome"),                    
                rs.getString("cpf"),                    
                rs.getInt("id_cidade"));                    
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
                Clientes c = new Clientes();
                c.setId(rs.getInt("id"));                
                c.setCidade(rs.getInt("id_cidade"));
                c.setNome(rs.getString("nome"));
                c.setCpf(rs.getString("cpf"));
                
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
           String sql = "SELECT * FROM cliente WHERE nome LIKE ?ORDER by nome;";
            cmd = con.prepareStatement(sql);
            cmd.setString(1, "%" + nome + "%");
            return pesquisar();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }          
        
        
    } 
    }
    

