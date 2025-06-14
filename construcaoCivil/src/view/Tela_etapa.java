package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Vector;
import controller.EtapaController;

public class Tela_etapa extends JFrame {

    private JTextField txtNome, txtProjeto;
    private JFormattedTextField txtDataInicio, txtDataTermino;
    private JComboBox<String> cbStatus;
    private JTable tabela;
    private DefaultTableModel modelo;
   
   

    public Tela_etapa() {

        
        modelo = (DefaultTableModel) tabela.getModel();
        carregarTabelaDoBanco();
        setTitle("Gerenciar Etapas");
        setSize(750, 520);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNome = new JLabel("Etapa:");
        lblNome.setBounds(20, 20, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 250, 25);
        add(txtNome);

        JLabel lblProjeto = new JLabel("Projeto:");
        lblProjeto.setBounds(370, 20, 80, 25);
        add(lblProjeto);

        txtProjeto = new JTextField();
        txtProjeto.setBounds(440, 20, 260, 25);
        add(txtProjeto);

        JLabel lblInicio = new JLabel("Início:");
        lblInicio.setBounds(20, 60, 80, 25);
        add(lblInicio);

        try {
            MaskFormatter dataMask = new MaskFormatter("##/##/####");
            dataMask.setPlaceholderCharacter('_');
            txtDataInicio = new JFormattedTextField(dataMask);
        } catch (ParseException e) {
            txtDataInicio = new JFormattedTextField();
        }
        txtDataInicio.setBounds(100, 60, 150, 25);
        add(txtDataInicio);

        JLabel lblTermino = new JLabel("Término:");
        lblTermino.setBounds(270, 60, 80, 25);
        add(lblTermino);

        try {
            MaskFormatter dataMask = new MaskFormatter("##/##/####");
            dataMask.setPlaceholderCharacter('_');
            txtDataTermino = new JFormattedTextField(dataMask);
        } catch (ParseException e) {
            txtDataTermino = new JFormattedTextField();
        }
        txtDataTermino.setBounds(350, 60, 150, 25);
        add(txtDataTermino);

        JLabel lblStatus = new JLabel("Status:");
        lblStatus.setBounds(520, 60, 80, 25);
        add(lblStatus);

        cbStatus = new JComboBox<>(new String[]{"Planejada", "Em Andamento", "Concluída"});
        cbStatus.setBounds(580, 60, 120, 25);
        add(cbStatus);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(20, 100, 100, 30);
        add(btnSalvar);

        JButton btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(130, 100, 100, 30);
        add(btnAlterar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(240, 100, 100, 30);
        add(btnExcluir);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(350, 100, 100, 30);
        add(btnCancelar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(460, 100, 100, 30);
        add(btnVoltar);

        modelo = new DefaultTableModel();
        modelo.addColumn("Etapa");
        modelo.addColumn("Projeto");
        modelo.addColumn("Início");
        modelo.addColumn("Término");
        modelo.addColumn("Status");

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 150, 700, 320);
        add(scroll);

        setVisible(true);

        // AÇÕES
        btnSalvar.addActionListener(e -> {
            Vector<String> linha = new Vector<>();
            linha.add(txtNome.getText());
            linha.add(txtProjeto.getText());
            linha.add(txtDataInicio.getText());
            linha.add(txtDataTermino.getText());
            linha.add((String) cbStatus.getSelectedItem());
            modelo.addRow(linha);
            limparCampos();
        });

        btnExcluir.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row >= 0) {
                modelo.removeRow(row);
                limparCampos();
            }
        });

        btnAlterar.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row >= 0) {
                modelo.setValueAt(txtNome.getText(), row, 0);
                modelo.setValueAt(txtProjeto.getText(), row, 1);
                modelo.setValueAt(txtDataInicio.getText(), row, 2);
                modelo.setValueAt(txtDataTermino.getText(), row, 3);
                modelo.setValueAt(cbStatus.getSelectedItem(), row, 4);
                limparCampos();
            }
        });

        btnCancelar.addActionListener(e -> limparCampos());

        btnVoltar.addActionListener(e -> dispose());

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                txtNome.setText((String) modelo.getValueAt(row, 0));
                txtProjeto.setText((String) modelo.getValueAt(row, 1));
                txtDataInicio.setText((String) modelo.getValueAt(row, 2));
                txtDataTermino.setText((String) modelo.getValueAt(row, 3));
                cbStatus.setSelectedItem(modelo.getValueAt(row, 4));
            }
        });
        carregarTabelaDoBanco();
    }

    private void carregarTabelaDoBanco() {
        // Limpa a tabela antes de adicionar os novos dados
        modelo.setRowCount(0);

        EtapaController controller = new EtapaController();
        java.util.List<model.Etapa> lista = controller.listarEtapas();

        for (model.Etapa etapa : lista) {
            modelo.addRow(new Object[]{
                etapa.getDescricao(),
                (etapa.getProjeto() != null ? etapa.getProjeto().getNome() : ""), // Projeto (caso você tenha o nome no objeto Projeto)
                etapa.getDataInicio(),
                etapa.getDataFimPrevista(),
                etapa.getStatus()
            });
        }
    }

    private void limparCampos() {
        txtNome.setText("");
        txtProjeto.setText("");
        txtDataInicio.setValue(null);
        txtDataTermino.setValue(null);
        cbStatus.setSelectedIndex(0);
        tabela.clearSelection();
    }
}