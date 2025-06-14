package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.event.*;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Vector;
import controller.MaterialController;
import model.Material;
import java.util.List;



public class Tela_Material extends JFrame  {

    private JTextField txtNome, txtQuantidade;
    private JFormattedTextField txtPreco;
    private JComboBox<String> cbUnidade;
    private JTable tabela;
    private DefaultTableModel modelo;


    public Tela_Material() {
        setTitle("Gerenciar Materiais");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblNome = new JLabel("Material:");
        lblNome.setBounds(20, 20, 80, 25);
        add(lblNome);

        txtNome = new JTextField();
        txtNome.setBounds(100, 20, 250, 25);
        add(txtNome);

        JLabel lblUnidade = new JLabel("Unidade:");
        lblUnidade.setBounds(370, 20, 80, 25);
        add(lblUnidade);

        cbUnidade = new JComboBox<>(new String[]{"Kg", "m²", "litro", "unidade", "m³"});
        cbUnidade.setBounds(450, 20, 120, 25);
        add(cbUnidade);

        JLabel lblQuantidade = new JLabel("Qtd Estoque:");
        lblQuantidade.setBounds(20, 60, 100, 25);
        add(lblQuantidade);

        txtQuantidade = new JTextField();
        txtQuantidade.setBounds(120, 60, 100, 25);
        add(txtQuantidade);

        JLabel lblPreco = new JLabel("Preço Unit.:");
        lblPreco.setBounds(240, 60, 100, 25);
        add(lblPreco);

        NumberFormat formatPreco = new DecimalFormat("#,##0.00");
        NumberFormatter formatterPreco = new NumberFormatter(formatPreco);
        formatterPreco.setAllowsInvalid(false);
        formatterPreco.setMinimum(0.0);
        txtPreco = new JFormattedTextField(formatterPreco);
        txtPreco.setBounds(340, 60, 100, 25);
        add(txtPreco);

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
        modelo.addColumn("Material");
        modelo.addColumn("Unidade");
        modelo.addColumn("Quantidade");
        modelo.addColumn("Preço Unitário");

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 150, 700, 280);
        add(scroll);

        setVisible(true);

        // AÇÕES
        btnSalvar.addActionListener(e -> {
            Vector<String> linha = new Vector<>();
            linha.add(txtNome.getText());
            linha.add((String) cbUnidade.getSelectedItem());
            linha.add(txtQuantidade.getText());
            linha.add(txtPreco.getText());
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
                modelo.setValueAt(cbUnidade.getSelectedItem(), row, 1);
                modelo.setValueAt(txtQuantidade.getText(), row, 2);
                modelo.setValueAt(txtPreco.getText(), row, 3);
                limparCampos();
            }
        });

        btnCancelar.addActionListener(e -> limparCampos());

        btnVoltar.addActionListener(e -> dispose());

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                txtNome.setText((String) modelo.getValueAt(row, 0));
                cbUnidade.setSelectedItem(modelo.getValueAt(row, 1));
                txtQuantidade.setText((String) modelo.getValueAt(row, 2));
                txtPreco.setText((String) modelo.getValueAt(row, 3));
            }
        });
    carregarMateriaisDoBanco();  
    }
    private void carregarMateriaisDoBanco() {
    MaterialController controller = new MaterialController();
    List<Material> lista = controller.listarMateriais();

    // Limpa a tabela antes de adicionar os dados
    modelo.setRowCount(0);

    for (Material m : lista) {
        Vector<Object> linha = new Vector<>();
        linha.add(m.getNome());
        linha.add(m.getUnidadeMedida());
        linha.add("N/A"); // Quantidade não está no model.Material, ajuste se necessário
        linha.add(String.format("R$ %.2f", m.getPrecoUnitario()));
        modelo.addRow(linha);
    }
}

    private void limparCampos() {
        txtNome.setText("");
        cbUnidade.setSelectedIndex(0);
        txtQuantidade.setText("");
        txtPreco.setValue(null);
        tabela.clearSelection();
    }
}
