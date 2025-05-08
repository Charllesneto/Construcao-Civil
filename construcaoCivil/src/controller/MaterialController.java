
package controller;

/**
 *
 * @author david
 */

import database.Conexao;
import model.Material;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MaterialController {
    
     public void adicionarMaterial(Material material) {
        String sql = "INSERT INTO material (id_material, nome, unidade_medida, preco_unitario) VALUES (?, ?, ?, ?)";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, material.getIdMaterial());
            stmt.setString(2, material.getNome());
            stmt.setString(3, material.getUnidadeMedida());
            stmt.setDouble(4, material.getPrecoUnitario());

            stmt.executeUpdate();
            System.out.println("Material adicionado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao adicionar material: " + e.getMessage());
        }
    }
   
    
}
