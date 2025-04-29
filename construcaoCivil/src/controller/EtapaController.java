package controller;

/**
 *
 * @author Nodival Neto
 */
import model.Etapa;
import database.Conexao;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;




public class EtapaController {

    // Métodos CRUD
    // Create
    public void adicionarEtapa(Etapa etapa) {
        String sql = "INSERT INTO etapa (id_etapa, descricao, status, data_inicio, data_fim_prevista) VALUES (?,?,?,?,?)";
        try (Connection conn = Conexao.conectar();
            PreparedStatement stmt = conn.prepareStatement(sql)){
            
            stmt.setInt(1, etapa.getIdEtapa());
            stmt
        }
        
    

)
    }
    
    
}
