
package controller;
/**
 *
 * @author david
 */

import model.Cliente;
import database.Conexao;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {
    
public void adicionarCliente(Cliente cliente){
    String sql = "INSERT INTO cliente (id_cliente, nome, tipo_cliente, cpf_cnpj, telefone) VALUES (?, ?, ?, ?, ?)";
    try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, cliente.getIdCliente());
            stmt.setString(2, cliente.getNome());
            stmt.setString(3, cliente.getTipoCliente());
            stmt.setString(4, cliente.getCpfCnpj());
            stmt.setString(5, cliente.getTelefone());

            stmt.executeUpdate();
            System.out.println("Cliente cadastrado com sucesso!");

        } catch (SQLException e) {
            System.err.println("Erro ao cadastrar cliente: " + e.getMessage());
        }
    }
}
