
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
    
}
