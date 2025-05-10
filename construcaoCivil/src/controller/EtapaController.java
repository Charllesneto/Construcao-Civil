package controller;

/**
 *
 * @author Nodival Neto
 */
import model.Etapa;
import database.Conexao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EtapaController {

    // Métodos CRUD
    // Create
    public void adicionarEtapa(Etapa etapa) {
    String sql = "INSERT INTO etapa "
               + "(id_etapa, descricao, status, data_inicio, data_fim_prevista, fk_projeto_etapa) "
               + "VALUES (?, ?, ?, ?, ?, ?)";

    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, etapa.getIdEtapa());
        stmt.setString(2, etapa.getDescricao());
        stmt.setString(3, etapa.getStatus());
        stmt.setString(4, etapa.getDataInicio());
        stmt.setString(5, etapa.getDataFimPrevista());
        stmt.setInt(6, etapa.getProjeto().getIdProjeto());

        stmt.executeUpdate();
        System.out.println("Etapa adicionada com sucesso!");
    } catch (SQLException e) {
        System.err.println("Erro ao adicionar etapa: " + e.getMessage());
    }
}


    // Read
    public List<Etapa> listarEtapas() {
        List<Etapa> etapas = new ArrayList<>();
        String sql = "SELECT * FROM etapa";

        try (Connection conn = Conexao.conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Etapa etapa = new Etapa(
                        rs.getInt("id_etapa"),
                        rs.getString("descricao"),
                        rs.getString("status"),
                        rs.getString("data_inicio").toString(),
                        rs.getString("data_fim_prevista").toString()
                );
                etapas.add(etapa);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return etapas;
    }

    // Update
    public boolean atualizarEtapa(Etapa etapa) {
        String sql = "UPDATE etapa SET descricao = ?, status = ?, data_inicio = ?, data_fim_prevista = ?, id_projeto= ?,id_profissional=? WHERE id_etapa = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, etapa.getDescricao());
            stmt.setString(2, etapa.getStatus());
            stmt.setString(3, etapa.getDataInicio());
            stmt.setString(4, etapa.getDataFimPrevista());
            stmt.setInt(5, etapa.getProjeto().getIdProjeto());
            stmt.setInt(6, etapa.getProfissional().getIdProfissional());

            stmt.setInt(5, etapa.getIdEtapa());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete
    public boolean removerEtapa(int idEtapa) {
        String sql = "DELETE FROM etapa WHERE id_etapa = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEtapa);
            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Buscar por ID
    public Etapa buscarPorId(int idEtapa) {
        String sql = "SELECT * FROM etapa WHERE id_etapa = ?";
        try (Connection conn = Conexao.conectar(); PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idEtapa);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Etapa(
                        rs.getInt("id_etapa"),
                        rs.getString("descricao"),
                        rs.getString("status"),
                        rs.getDate("data_inicio").toString(),
                        rs.getDate("data_fim_prevista").toString()
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
