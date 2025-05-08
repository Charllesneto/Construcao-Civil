
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
    // metodo para atualizar os dados de profissicional, abre a conecxão, substitui os dados e retorna se houve mudança nas linhas afetadas.
     
     
    public boolean atualizarProfissional(Profissional profissional) {
        String sql = "UPDATE profissional SET nome = ?, cargo = ?, cpf = ?, telefone = ? WHERE id_profissional = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, profissional.getNome());
            stmt.setString(2, profissional.getCargo());
            stmt.setString(3, profissional.getCpf());
            stmt.setString(4, profissional.getTelefone());
            stmt.setInt(5, profissional.getIdProfissional());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar profissional: " + e.getMessage());
            return false;
        }
    }
    // metodo para deletar os dados de profissional
    
    public boolean removerProfissional(int idProfissional) {
        String sql = "DELETE FROM profissional WHERE id_profissional = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProfissional);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover profissional: " + e.getMessage());
            return false;
        }
    }
    
    // metodo para buscar profissional por id.
    
    public Profissional buscarPorId(int idProfissional) {
        String sql = "SELECT * FROM profissional WHERE id_profissional = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProfissional);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Profissional(
                    rs.getInt("id_profissional"),
                    rs.getString("nome"),
                    rs.getString("cargo"),
                    rs.getString("cpf"),
                    rs.getString("telefone")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar profissional por ID: " + e.getMessage());
        }

        return null;
    }
}
