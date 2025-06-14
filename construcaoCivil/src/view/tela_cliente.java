package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.Vector;
import java.util.List;
import java.util.ArrayList;
import controller.ClienteController;



public class tela_cliente extends JFrame {

    private ClienteController clienteController;
    private JTextField txtNome;
    private JFormattedTextField txtCpf, txtTelefone; // Campos com máscara
    private JTable tabela;
    private DefaultTableModel modelo;
    
    public tela_cliente() {
        
        clienteController = new ClienteController();  // Inicialize o controlador
        
        setTitle("Gerenciar Clientes");
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

        JLabel lblCpf = new JLabel("CPF:");
        lblCpf.setBounds(320, 20, 80, 25);
        add(lblCpf);

        try {
            MaskFormatter cpfMask = new MaskFormatter("###.###.###-##");
            cpfMask.setPlaceholderCharacter('_');
            txtCpf = new JFormattedTextField(cpfMask);
        } catch (ParseException e) {
            txtCpf = new JFormattedTextField();
        }
        txtCpf.setBounds(370, 20, 180, 25);
        add(txtCpf);

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
        modelo.addColumn("ID");
        modelo.addColumn("Nome");
        modelo.addColumn("CPF");
        modelo.addColumn("Telefone");

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 150, 540, 250);
        add(scroll);

        setVisible(true);

        // Eventos
        btnSalvar.addActionListener(e -> {
            Vector<String> linha = new Vector<>();
            linha.add(txtNome.getText());
            linha.add(txtCpf.getText());
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
                modelo.setValueAt(txtCpf.getText(), row, 1);
                modelo.setValueAt(txtTelefone.getText(), row, 2);
                limparCampos();
            }
        });

        btnCancelar.addActionListener(e -> limparCampos());

        btnVoltar.addActionListener(e -> {
            dispose(); // fecha esta janela
            // Opcional: aqui você pode abrir a tela principal, se quiser
            // new telaPrincipal().setVisible(true);
        });

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                if (row >= 0) {
                    txtNome.setText((String) modelo.getValueAt(row, 0));
                    txtCpf.setText((String) modelo.getValueAt(row, 1));
                    txtTelefone.setText((String) modelo.getValueAt(row, 2));
                }
            }
        });
    preencherTabela();     
    }

    private void limparCampos() {
        txtNome.setText("");
        txtCpf.setValue(null);
        txtTelefone.setValue(null);
        tabela.clearSelection();
    }

    private void preencherTabela() {
        modelo.setRowCount(0); // Limpa a tabela

        List<Object[]> listaClientes = clienteController.listarClientesParaTela(); // Usando o método alterado do controlador

        for (Object[] clienteData : listaClientes) {
            modelo.addRow(clienteData);  // Adiciona os dados formatados na tabela
        }
    }

}
