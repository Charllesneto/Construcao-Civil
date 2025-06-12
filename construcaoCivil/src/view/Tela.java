package view;

/**
 *
 * @author david
 */
import controller.ClienteController;
import model.Cliente;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.List;
import javax.swing.text.DefaultFormatterFactory;

public class Tela {

    private static DefaultTableModel model;
    private static JTable tabela;
    private static ClienteController clienteController = new ClienteController();

    public static void montarTela() throws ParseException {
        JFrame frame = new JFrame("Cadastro de Cliente");
        frame.setBounds(400, 180, 700, 520);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblNome = new JLabel("Nome:");
        lblNome.setBounds(10, 10, 60, 25);
        frame.add(lblNome);

        JTextField txtNome = new JTextField();
        txtNome.setBounds(80, 10, 300, 25);
        frame.add(txtNome);

        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(400, 10, 40, 25);
        frame.add(lblTipo);

        String[] tipos = {"Pessoa Física", "Pessoa Jurídica"};
        JComboBox<String> cbTipo = new JComboBox<>(tipos);
        cbTipo.setBounds(450, 10, 180, 25);
        frame.add(cbTipo);

// CPF/CNPJ
        JLabel lblCpfCnpj = new JLabel("CPF/CNPJ:");
        lblCpfCnpj.setBounds(10, 45, 80, 25);
        frame.add(lblCpfCnpj);

        JFormattedTextField txtCpfCnpj = new JFormattedTextField();
        txtCpfCnpj.setBounds(80, 45, 150, 25);
        txtCpfCnpj.setFormatterFactory(
                new DefaultFormatterFactory(new MaskFormatter("###.###.###-##"))); // padrão: CPF
        frame.add(txtCpfCnpj);

// Troca de máscara conforme tipo selecionado
        cbTipo.addActionListener(e -> {
            try {
                txtCpfCnpj.setValue(null); // limpa o campo ao trocar
                if (cbTipo.getSelectedItem().equals("Pessoa Física")) {
                    txtCpfCnpj.setFormatterFactory(
                            new DefaultFormatterFactory(new MaskFormatter("###.###.###-##")));
                } else {
                    txtCpfCnpj.setFormatterFactory(
                            new DefaultFormatterFactory(new MaskFormatter("##.###.###/####-##")));
                }
            } catch (ParseException ex) {
                ex.printStackTrace();
            }
        });

        JLabel lblTelefone = new JLabel("Telefone:");
        lblTelefone.setBounds(250, 45, 70, 25);
        frame.add(lblTelefone);

        JFormattedTextField txtTelefone = new JFormattedTextField(mascara("(##)#####-####"));
        txtTelefone.setBounds(320, 45, 150, 25);
        frame.add(txtTelefone);

        // Tabela
        String[] colunas = {"ID", "Nome", "Tipo", "CPF/CNPJ", "Telefone"};
        model = new DefaultTableModel(colunas, 0);
        tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(10, 90, 660, 250);
        frame.add(scrollPane);

        // Botões
        JButton btnGravar = new JButton("Gravar");
        btnGravar.setBounds(60, 370, 100, 30);
        frame.add(btnGravar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(170, 370, 100, 30);
        frame.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(280, 370, 100, 30);
        frame.add(btnExcluir);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(390, 370, 100, 30);
        frame.add(btnCancelar);

        frame.setVisible(true);

        carregarTabela();

        btnGravar.addActionListener(e -> {
            String nome = txtNome.getText();
            String tipo = (String) cbTipo.getSelectedItem();
            String cpfCnpj = txtCpfCnpj.getText();
            String telefone = txtTelefone.getText();

            Cliente novo = new Cliente(nome, tipo, cpfCnpj, telefone);
            clienteController.adicionarCliente(novo);
            limparCampos(txtNome, txtCpfCnpj, txtTelefone);
            carregarTabela();
        });

        btnEditar.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Selecione um cliente na tabela.");
                return;
            }
            int id = (int) model.getValueAt(row, 0);

            String nome = txtNome.getText();
            String tipo = (String) cbTipo.getSelectedItem();
            String cpfCnpj = txtCpfCnpj.getText();
            String telefone = txtTelefone.getText();

            Cliente editado = new Cliente(id, nome, tipo, cpfCnpj, telefone);
            clienteController.atualizarCliente(editado);
            limparCampos(txtNome, txtCpfCnpj, txtTelefone);
            carregarTabela();
        });

        btnExcluir.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row == -1) {
                JOptionPane.showMessageDialog(frame, "Selecione um cliente na tabela.");
                return;
            }
            int id = (int) model.getValueAt(row, 0);
            clienteController.removerCliente(id);
            carregarTabela();
        });

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                txtNome.setText((String) model.getValueAt(row, 1));
                cbTipo.setSelectedItem((String) model.getValueAt(row, 2));
                txtCpfCnpj.setText((String) model.getValueAt(row, 3));
                txtTelefone.setText((String) model.getValueAt(row, 4));
            }
        });

        btnCancelar.addActionListener(e ->{limparCampos(txtNome, txtCpfCnpj, txtTelefone);
         frame.dispose();});
       
    }

    public static void carregarTabela() {
        model.setRowCount(0);
        List<Cliente> lista = clienteController.listarClientes();
        for (Cliente c : lista) {
            model.addRow(new Object[]{
                c.getIdCliente(),
                c.getNome(),
                c.getTipoCliente(),
                c.getCpfCnpj(),
                c.getTelefone()
            });
        }
    }

    public static void limparCampos(JTextField nome, JTextField cpfcnpj, JFormattedTextField telefone) {
        nome.setText("");
        cpfcnpj.setText("");
        telefone.setText("");
        tabela.clearSelection();
    }

    public static MaskFormatter mascara(String formato) throws ParseException {
        MaskFormatter mascara = new MaskFormatter(formato);
        mascara.setPlaceholderCharacter('_');
        return mascara;
    }

    public static void main(String[] args) {
        try {
            montarTela();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
