package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Vector;

public class Tela_Projeto extends JFrame {

    private JTextField txtNomeProjeto, txtCliente;
    private JFormattedTextField txtDataInicio;
    private JTextField txtOrcamento;
    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton btnSalvar, btnAlterar, btnExcluir, btnCancelar, btnVoltar;


    public Tela_Projeto() {
        setTitle("Gerenciar Projetos");
        setSize(700, 480);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNomeProjeto = new JLabel("Nome:");
        lblNomeProjeto.setBounds(20, 20, 80, 25);
        add(lblNomeProjeto);

        txtNomeProjeto = new JTextField();
        txtNomeProjeto.setBounds(100, 20, 250, 25);
        add(txtNomeProjeto);

        JLabel lblCliente = new JLabel("Cliente:");
        lblCliente.setBounds(370, 20, 80, 25);
        add(lblCliente);

        txtCliente = new JTextField();
        txtCliente.setBounds(440, 20, 200, 25);
        add(txtCliente);

        JLabel lblDataInicio = new JLabel("Data Início:");
        lblDataInicio.setBounds(20, 60, 80, 25);
        add(lblDataInicio);

        try {
            MaskFormatter dataMask = new MaskFormatter("##/##/####");
            dataMask.setPlaceholderCharacter('_');
            txtDataInicio = new JFormattedTextField(dataMask);
        } catch (ParseException e) {
            txtDataInicio = new JFormattedTextField();
        }
        txtDataInicio.setBounds(100, 60, 150, 25);
        add(txtDataInicio);

        JLabel lblOrcamento = new JLabel("Orçamento:");
        lblOrcamento.setBounds(270, 60, 80, 25);
        add(lblOrcamento);

        txtOrcamento = new JTextField();
        txtOrcamento.setBounds(350, 60, 150, 25);
        add(txtOrcamento);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(20, 100, 100, 30);
        add(btnSalvar);

        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(130, 100, 100, 30);
        add(btnAlterar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(240, 100, 100, 30);
        add(btnExcluir);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(350, 100, 100, 30);
        add(btnCancelar);

        btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(460, 100, 100, 30);
        add(btnVoltar);

        modelo = new DefaultTableModel();
        modelo.addColumn("Nome");
        modelo.addColumn("Cliente");
        modelo.addColumn("Data Início");
        modelo.addColumn("Orçamento");

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 150, 640, 270);
        add(scroll);

        setVisible(true);

        // AÇÕES
        btnSalvar.addActionListener(e -> {
            Vector<String> linha = new Vector<>();
            linha.add(txtNomeProjeto.getText());
            linha.add(txtCliente.getText());
            linha.add(txtDataInicio.getText());
            linha.add(txtOrcamento.getText());
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
                modelo.setValueAt(txtNomeProjeto.getText(), row, 0);
                modelo.setValueAt(txtCliente.getText(), row, 1);
                modelo.setValueAt(txtDataInicio.getText(), row, 2);
                modelo.setValueAt(txtOrcamento.getText(), row, 3);
                limparCampos();
            }
        });

        btnCancelar.addActionListener(e -> limparCampos());

        btnVoltar.addActionListener(e -> dispose());

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                txtNomeProjeto.setText((String) modelo.getValueAt(row, 0));
                txtCliente.setText((String) modelo.getValueAt(row, 1));
                txtDataInicio.setText((String) modelo.getValueAt(row, 2));
                txtOrcamento.setText((String) modelo.getValueAt(row, 3));
            }
        });
    }

    private void limparCampos() {
        txtNomeProjeto.setText("");
        txtCliente.setText("");
        txtDataInicio.setValue(null);
        txtOrcamento.setText("");
        tabela.clearSelection();
    }
}
