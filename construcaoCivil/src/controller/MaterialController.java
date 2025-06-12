
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
    
    // adiciona materiais ao bd.
    
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
     // método para listar os materiais, retorna um objeto com os dados do banco para um array 
     
      public List<Material> listarMateriais() {
        List<Material> lista = new ArrayList<>();
        String sql = "SELECT * FROM material";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Material m = new Material(
                    rs.getInt("id_material"),
                    rs.getString("nome"),
                    rs.getString("unidade_medida"),
                    rs.getDouble("preco_unitario")
                );
                lista.add(m);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar materiais: " + e.getMessage());
        }

        return lista;
    }
    // criação do metodo para atualizar os dados de material
      
        public boolean atualizarMaterial(Material material) {
        String sql = "UPDATE material SET nome = ?, unidade_medida = ?, preco_unitario = ? WHERE id_material = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, material.getNome());
            stmt.setString(2, material.getUnidadeMedida());
            stmt.setDouble(3, material.getPrecoUnitario());
            stmt.setInt(4, material.getIdMaterial());

            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar material: " + e.getMessage());
            return false;
        }
    }
        
        
        // metodo para remover um material
        
    public boolean removerMaterial(int idMaterial) {
        String sql = "DELETE FROM material WHERE id_material = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMaterial);
            int linhas = stmt.executeUpdate();
            return linhas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao remover material: " + e.getMessage());
            return false;
        }
    }
    // busca apenas um material pelo id
     public Material buscarPorId(int idMaterial) {
        String sql = "SELECT * FROM material WHERE id_material = ?";

        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, idMaterial);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Material(
                    rs.getInt("id_material"),
                    rs.getString("nome"),
                    rs.getString("unidade_medida"),
                    rs.getDouble("preco_unitario")
                );
            }

        } catch (SQLException e) {
            System.err.println("Erro ao buscar material por ID: " + e.getMessage());
        }

        return null;
    }
    
}
