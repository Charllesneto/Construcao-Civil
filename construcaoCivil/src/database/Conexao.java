package database;

/**
 *
 * @author Nodival Neto
 */
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Na hora de apresentar, a pessoa que for espelhar a tela, deverá atualizar essa área, com seus respectivos dados.
public class Conexao {

    private static final String URL = "jdbc:mysql://localhost:3306/BANCO";
    private static final String USUARIO = "root";
    private static final String SENHA = "senha do banco";

    public static Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL, USUARIO, SENHA);
    }
}
