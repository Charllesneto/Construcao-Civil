
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
   
    public void adicionarProjeto(Projeto projeto) {
        String sql = "INSERT INTO projeto (id_projeto, nome, descricao, data_inicio, data_fim_prevista, status, id_cliente) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, projeto.getIdProjeto());
            stmt.setString(2, projeto.getNome());
            stmt.setString(3, projeto.getDescricao());
            stmt.setDate(4, Date.valueOf(projeto.getDataInicio()));
            stmt.setDate(5, Date.valueOf(projeto.getDataFimPrevista()));
            stmt.setString(6, projeto.getStatus());
            stmt.setInt(7, projeto.getCliente().getIdCliente());

            stmt.executeUpdate();
            System.out.println("Projeto adicionado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar projeto: " + e.getMessage());
        }
    }
}
