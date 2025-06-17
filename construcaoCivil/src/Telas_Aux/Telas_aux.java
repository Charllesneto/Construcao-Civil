package Telas_Aux;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import controller.ClienteController;
import model.Cliente;

public class Telas_aux extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;
    private ClienteSelecionadoListener listener;

    public Telas_aux(ClienteSelecionadoListener listener) {
        this.listener = listener;

        setTitle("Selecionar Cliente");
        setSize(400, 300);
        setLocationRelativeTo(null);
        setLayout(null);

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 20, 340, 180);
        add(scroll);

        JButton btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setBounds(130, 220, 120, 30);
        add(btnSelecionar);

        btnSelecionar.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row >= 0) {
                int id = (int) modelo.getValueAt(row, 0);
                String nome = (String) modelo.getValueAt(row, 1);
                listener.clienteSelecionado(id, nome);
                dispose();
            }
        });
        carregarClientes();
        setVisible(true);
    }

    private void carregarClientes() {
        ClienteController controller = new ClienteController();
        List<Cliente> clientes = controller.listarClientes();
        for (Cliente c : clientes) {
            modelo.addRow(new Object[]{c.getIdCliente(), c.getNome()});
        }
    }

    public interface ClienteSelecionadoListener {

        void clienteSelecionado(int id, String nome);
    }
}
