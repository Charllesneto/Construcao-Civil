package view;

import Telas_Aux.Tela_aux2;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Vector;

public class Tela_Proficional extends JFrame {

    private JTextField txtNome, txtProfissao;
    private JFormattedTextField txtTelefone; // Campo com máscara
    private JTable tabela;
    private DefaultTableModel modelo;
    
    public Tela_Proficional() {
        setTitle("Gerenciar Profissionais");
        setSize(600, 450);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(20, 20, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 200, 25);
        add(txtNome);

        JLabel lblProfissao = new JLabel("Profissão:");
        lblProfissao.setBounds(320, 20, 80, 25);
        add(lblProfissao);

        txtProfissao = new JTextField();
        txtProfissao.setBounds(400, 20, 160, 25);
        add(txtProfissao);

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(20, 60, 80, 25);
        add(lblTelefone);

        try {
            MaskFormatter telMask = new MaskFormatter("(##) #####-####");
            telMask.setPlaceholderCharacter('_');
            txtTelefone = new JFormattedTextField(telMask);
        } catch (ParseException e) {
            txtTelefone = new JFormattedTextField();
        }
        txtTelefone.setBounds(100, 60, 200, 25);
        add(txtTelefone);

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
        modelo.addColumn("Nome");
        modelo.addColumn("Profissão");
        modelo.addColumn("Telefone");

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 150, 540, 250);
        add(scroll);

        setVisible(true);

        // AÇÕES DOS BOTÕES
        btnSalvar.addActionListener(e -> {
            Vector<String> linha = new Vector<>();
            linha.add(txtNome.getText());
            linha.add(txtProfissao.getText());
            linha.add(txtTelefone.getText());
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
                modelo.setValueAt(txtProfissao.getText(), row, 1);
                modelo.setValueAt(txtTelefone.getText(), row, 2);
                limparCampos();
            }
        });

        btnCancelar.addActionListener(e -> limparCampos());

        btnVoltar.addActionListener(e -> {
            dispose(); // Fecha essa janela
            // Opcional: abrir a tela principal novamente
            // new telaPrincipal().setVisible(true);
        });

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                if (row >= 0) {
                    txtNome.setText((String) modelo.getValueAt(row, 0));
                    txtProfissao.setText((String) modelo.getValueAt(row, 1));
                    txtTelefone.setText((String) modelo.getValueAt(row, 2));
                }
            }
        });
    }

    private void limparCampos() {
        txtNome.setText("");
        txtProfissao.setText("");
        txtTelefone.setValue(null);
        tabela.clearSelection();
    }
}
