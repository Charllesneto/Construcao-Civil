
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
    String sql = "INSERT INTO cliente (id_cliente, nome, tipo_cliente, cpf_cnpj, telefone) VALUES (?, ?, ?, ?, ?)\";
}
    
}
