package controller;

import database.Conexao;
import model.Cliente;
import model.Projeto;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

/**
 *
 * @author David
 */
public class ProjetoController {

    private ClienteController clienteController = new ClienteController(); // para buscar o cliente pelo id.

    // Método para adicionar um novo projeto ao BD.
    public void adicionarProjeto(Projeto projeto) {
        String sql = "INSERT INTO projeto "
                + "(id_projeto, nome, descricao, data_inicio, data_fim_prevista, status,fk_cliente_projeto) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, projeto.getIdProjeto());
            stmt.setString(2, projeto.getNome());
            stmt.setString(3, projeto.getDescricao());
            stmt.setString(4, projeto.getDataInicio());
            stmt.setString(5, projeto.getDataFimPrevista());
            stmt.setString(6, projeto.getStatus());
            stmt.setInt(7, projeto.getCliente().getIdCliente());

            stmt.executeUpdate();
            System.out.println("Projeto adicionado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar projeto: " + e.getMessage());
        }
    }

    // metodo que retorna um array com os dados de projeto vindos do BD.
    public List<Projeto> listarProjetos() {
        List<Projeto> projetos = new ArrayList<>();
        String sql = "SELECT * FROM projeto";

        // 
        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {

                Cliente cliente = clienteController.buscarPorId(rs.getInt("fk_cliente_projeto"));
                Projeto projeto = new Projeto(
                        rs.getInt("id_projeto"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("data_inicio"),
                        rs.getString("data_fim_prevista"),
                        rs.getString("status"),
                        cliente
                );
                projetos.add(projeto);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar projetos: " + e.getMessage());
        }
        return projetos;
    }

    // atualiza os dados do projeto e retorna true se alguma linha for alterada
    public boolean atualizarProjeto(Projeto projeto) {
        String sql = "UPDATE projeto SET nome = ?, descricao = ?, data_inicio = ?, data_fim_prevista = ?, status = ?, fk_cliente_projeto = ? WHERE id_projeto = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, projeto.getNome());
            stmt.setString(2, projeto.getDescricao());
            stmt.setString(3, projeto.getDataInicio());
            stmt.setString(4, projeto.getDataFimPrevista());

            stmt.setString(5, projeto.getStatus());
            stmt.setInt(6, projeto.getCliente().getIdCliente());
            stmt.setInt(7, projeto.getIdProjeto());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar projeto: " + e.getMessage());
            return false;
        }
    }

    // metodo para deletar o projeto pelo ID.
    public boolean removerProjeto(int idProjeto) {
        String sql = "DELETE FROM projeto WHERE id_projeto = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProjeto);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover projeto: " + e.getMessage());
            return false;
        }
    }

    // metodo para fazer uma busca detalhada por id.
    //seleciona o objeto por id e retorna um objeto com os dados do banco
    public Projeto buscarPorId(int idProjeto) {
        String sql = "SELECT * FROM projeto WHERE id_projeto = ?";

        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idProjeto);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Cliente cliente = clienteController.buscarPorId(rs.getInt("fk_cliente_projeto"));
                return new Projeto(
                        rs.getInt("id_projeto"),
                        rs.getString("nome"),
                        rs.getString("descricao"),
                        rs.getString("data_inicio"),
                        rs.getString("data_fim_prevista"),
                        rs.getString("status"),
                        cliente
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar projeto por ID: " + e.getMessage());
        }
        return null;
    }

}
