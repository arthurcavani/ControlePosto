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
import principal.Usuario;


public class UsuarioDAO implements IDao {
    
    private Connection con;
    private PreparedStatement cmd;
    
    public UsuarioDAO(){
        this.con = Conexao.Conectar();
    }

    @Override
    public int inserir(Object obj) {
        try {
            String sql = "INSERT INTO usuario (login, senha) VALUES (?,?)";

            Usuario c = (Usuario) obj;
            cmd = con.prepareStatement(sql,
                    Statement.RETURN_GENERATED_KEYS);
            cmd.setString(1, c.getLogin());
            cmd.setString(2, c.getSenha());
          

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
            String sql = "UPDATE usuario SET login=?, senha=? WHERE id=?";

            Usuario c = (Usuario) obj;
            cmd = con.prepareStatement(sql);
            cmd.setString(1, c.getLogin());
            cmd.setString(2, c.getSenha());            
            cmd.setInt(3, c.getId());

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
            String sql = "SELECT * FROM usuario";
            
            cmd = con.prepareStatement(sql);
            ResultSet rs = cmd.executeQuery();
            
            List<Object> lista = new ArrayList<>();
            while(rs.next()){
                Usuario cat = new Usuario(
                    rs.getInt("id"),rs.getString("login"),rs.getString("senha"));                    
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
                Usuario c = new Usuario();
                c.setId(rs.getInt("id"));                
                c.setLogin(rs.getString("login"));
                c.setSenha(rs.getString("senha"));
                
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
           String sql = "SELECT * FROM usuario WHERE login LIKE ?ORDER by login;";
            cmd = con.prepareStatement(sql);
            cmd.setString(1, "%" + nome + "%");
            return pesquisar();
        } catch (SQLException e) {
            System.out.println("ERRO: " + e.getMessage());
            return null;
        }          
        
        
    } 
    }
    

