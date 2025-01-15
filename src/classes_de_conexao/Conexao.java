package classes_de_conexao;

import java.sql.*;

public class Conexao {
    public static void main(String[] args) {
        try {
            // Carregar o driver
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");

            // Criar a URL de conexão
            String url = "jdbc:sqlserver://;serverName=localhost;databaseName=master;trustServerCertificate=true";

            // Estabelecer a conexão
            Connection conn = DriverManager.getConnection(url, "", "");

            // Verificar se a conexão foi estabelecida
            if (conn != null) {
                System.out.println("Conexão estabelecida com sucesso!");
            } else {
                System.out.println("Erro ao conectar ao banco de dados.");
            }

            // Fechar a conexão (importante!)
            conn.close();
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
        }
    }
}
