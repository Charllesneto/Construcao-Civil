
package controller;

/**
 *
 * @author david
 */
import database.Conexao;
import model.Etapa;
import model.Material;
import model.UsoMaterial;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
    
public class UsoMaterialController {

    private MaterialController materialController = new MaterialController();
    private EtapaController etapaController = new EtapaController();

    // Metodo adicionar uso insere no banco os dados de materiais gastos 
    public void adicionarUso(UsoMaterial uso) {
        String sql = "INSERT INTO uso_material (id_uso_material, id_material, id_etapa, quantidade) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, uso.getIdUsoMaterial());
            stmt.setInt(2, uso.getMaterial().getIdMaterial());
            stmt.setInt(3, uso.getEtapa().getIdEtapa());
            stmt.setDouble(4, uso.getQuantidade());

            stmt.executeUpdate();
            System.out.println("Uso de material cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar uso de material: " + e.getMessage());
        }
    }
     
    // método para listar os materiais usados faz uma consulta no banco e retorna um array com os objetos preenchidos pelos dados
    
    public List<UsoMaterial> listarUsos() {
        List<UsoMaterial> usos = new ArrayList<>();
        String sql = "SELECT * FROM uso_material";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Material material = materialController.buscarPorId(rs.getInt("id_material"));
                Etapa etapa = etapaController.buscarPorId(rs.getInt("id_etapa"));

                UsoMaterial uso = new UsoMaterial(
                    rs.getInt("id_uso_material"),
                    material,
                    etapa,
                    rs.getDouble("quantidade")
                );
                usos.add(uso);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar uso de material: " + e.getMessage());
        }

        return usos;
    }
    
    // metodo para atualizar os materiais ultilizados 
    
    public boolean atualizarUso(UsoMaterial uso) {
        String sql = "UPDATE uso_material SET id_material = ?, id_etapa = ?, quantidade = ? WHERE id_uso_material = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, uso.getMaterial().getIdMaterial());
            stmt.setInt(2, uso.getEtapa().getIdEtapa());
            stmt.setDouble(3, uso.getQuantidade());
            stmt.setInt(4, uso.getIdUsoMaterial());

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar uso de material: " + e.getMessage());
            return false;
        }
    }
    
    // metodo para buscar por ID um material em especifico
    
     public UsoMaterial buscarPorId(int idUso) {
        String sql = "SELECT * FROM uso_material WHERE id_uso_material = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idUso);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Material material = materialController.buscarPorId(rs.getInt("id_material"));
                Etapa etapa = etapaController.buscarPorId(rs.getInt("id_etapa"));

                return new UsoMaterial(
                    rs.getInt("id_uso_material"),
                    material,
                    etapa,
                    rs.getDouble("quantidade")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar uso de material por ID: " + e.getMessage());
        }

        return null;
    }
    public boolean removerUso(int idUsoMaterial) {
    String sql = "DELETE FROM uso_material WHERE id_uso_material = ?";
    try (Connection conn = Conexao.conectar();
         PreparedStatement stmt = conn.prepareStatement(sql)) {

        stmt.setInt(1, idUsoMaterial);
        int linhasAfetadas = stmt.executeUpdate();
        if (linhasAfetadas > 0) {
            System.out.println("Uso de material removido com sucesso!");
            return true;
        } else {
            System.out.println("Nenhum uso de material encontrado com ID " + idUsoMaterial);
            return false;
        }

    } catch (SQLException e) {
        System.err.println("Erro ao remover uso de material: " + e.getMessage());
        return false;
    }
}
}
