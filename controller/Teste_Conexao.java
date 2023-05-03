package controller;

import java.sql.Connection;

public class Teste_Conexao {

    public static void main(String[] args) {
    
        Connection con = Conexao.Conectar();
        if (con != null){
            System.out.println("Conexão realizada com sucesso!");
            Conexao.Desconectar(con);
        }
        
    }
    
    
}
