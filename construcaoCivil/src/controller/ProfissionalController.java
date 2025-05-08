
package controller;

/**
 *
 * @author david
 */

import database.Conexao;
import model.Profissional;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProfissionalController {
    
    // metodo para adicionar um profissional ao banco de dados 
    
    public void adicionarProfissional(Profissional profissional) {
        String sql = "INSERT INTO profissional (id_profissional, nome, cargo, cpf, telefone) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, profissional.getIdProfissional());
            stmt.setString(2, profissional.getNome());
            stmt.setString(3, profissional.getCargo());
            stmt.setString(4, profissional.getCpf());
            stmt.setString(5, profissional.getTelefone());

            stmt.executeUpdate();
            System.out.println("Profissional cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar profissional: " + e.getMessage());
        }
    }
    // metodo para listar os profissionais, testa a conecxao e itera sob um laço de repetição
    // enquanto rs.next retornar true ele instancia um novo objeto com os dados do profissional
    
     public List<Profissional> listarProfissionais() {
        List<Profissional> lista = new ArrayList<>();
        String sql = "SELECT * FROM profissional";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Profissional p = new Profissional(
                    rs.getInt("id_profissional"),
                    rs.getString("nome"),
                    rs.getString("cargo"),
                    rs.getString("cpf"),
                    rs.getString("telefone")
                );
                lista.add(p);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar profissionais: " + e.getMessage());
        }
        return lista;
    }

    

    
}
