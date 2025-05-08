
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
    
// metodo para adicionar clientes 
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
// Lista todos os clientes, método retorna uma lista com os dados de clientes vindos do BD.
public List<Cliente> listarClientes() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";

        try (Connection conn = Conexao.conectar();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Cliente cliente = new Cliente(
                    rs.getInt("id_cliente"),
                    rs.getString("nome"),
                    rs.getString("tipo_cliente"),
                    rs.getString("cpf_cnpj"),
                    rs.getString("telefone")
                );
                lista.add(cliente);
            }

        } catch (SQLException e) {
            System.err.println("Erro ao listar clientes: " + e.getMessage());
        }
        return lista;
    }
// metodo para atualizar os dados do cliente, retorna true se houver atualização em pelo menos uma linha da tabela.
    public boolean atualizarCliente(Cliente cliente) {
        String sql = "UPDATE cliente SET nome = ?, tipo_cliente = ?, cpf_cnpj = ?, telefone = ? WHERE id_cliente = ?";
        try (Connection conn = Conexao.conectar();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, cliente.getNome());
            stmt.setString(2, cliente.getTipoCliente());
            stmt.setString(3, cliente.getCpfCnpj());
            stmt.setString(4, cliente.getTelefone());
            stmt.setInt(5, cliente.getIdCliente());

            int linhasAfetadas = stmt.executeUpdate();
            return linhasAfetadas > 0;

        } catch (SQLException e) {
            System.err.println("Erro ao atualizar cliente: " + e.getMessage());
            return false;
        }
    }
    
}
