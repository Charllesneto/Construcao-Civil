package Telas_Aux;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.*;
import java.util.List;
import model.Profissional;
import controller.ProfissionalController;

public class Tela_aux2 extends JFrame {

    private JTable tabela;
    private DefaultTableModel modelo;
    private ProfissionalSelecionadoListener listener;

    public Tela_aux2(ProfissionalSelecionadoListener listener) {
        this.listener = listener;

        setTitle("Selecionar Profissional");
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        modelo = new DefaultTableModel();
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("Especialidade");

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 20, 440, 280);
        add(scroll);

        JButton btnSelecionar = new JButton("Selecionar");
        btnSelecionar.setBounds(180, 320, 120, 30);
        add(btnSelecionar);

        carregarProfissionais();

        btnSelecionar.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row >= 0) {
                int id = (int) modelo.getValueAt(row, 0);
                String nome = (String) modelo.getValueAt(row, 1);
                listener.profissionalSelecionado(id, nome);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Selecione um profissional na tabela.");
            }
        });

        setVisible(true);
    }

    private void carregarProfissionais() {
        ProfissionalController controller = new ProfissionalController();
        List<Profissional> lista = controller.listarProfissionais();

        for (Profissional prof : lista) {
            modelo.addRow(new Object[]{prof.getIdProfissional(), prof.getNome(), prof.getCargo()});
        }
    }

    public interface ProfissionalSelecionadoListener {

        void profissionalSelecionado(int id, String nome);
    }
}
