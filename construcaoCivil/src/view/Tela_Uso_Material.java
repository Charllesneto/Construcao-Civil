package view;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.NumberFormatter;
import java.awt.event.*;
import java.text.NumberFormat;
import java.util.Vector;
import java.util.List;

import controller.UsoMaterialController;
import model.UsoMaterial;
import model.Material;
import model.Etapa;

public class Tela_Uso_Material extends JFrame {

    private JTextField txtProjeto, txtEtapa, txtMaterial;
    private JFormattedTextField txtQuantidade;
    private JTable tabela;
    private DefaultTableModel modelo;
    private JButton btnAlterar, btnExcluir;

    public Tela_Uso_Material() {
        setTitle("Gerenciar Uso de Materiais");
        setSize(750, 500);
        setLocationRelativeTo(null);
        setLayout(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        JLabel lblProjeto = new JLabel("Projeto:");
        lblProjeto.setBounds(20, 20, 80, 25);
        add(lblProjeto);

        txtProjeto = new JTextField();
        txtProjeto.setBounds(100, 20, 250, 25);
        add(txtProjeto);

        JLabel lblEtapa = new JLabel("Etapa:");
        lblEtapa.setBounds(370, 20, 80, 25);
        add(lblEtapa);

        txtEtapa = new JTextField();
        txtEtapa.setBounds(440, 20, 260, 25);
        add(txtEtapa);

        JLabel lblMaterial = new JLabel("Material:");
        lblMaterial.setBounds(20, 60, 80, 25);
        add(lblMaterial);

        txtMaterial = new JTextField();
        txtMaterial.setBounds(100, 60, 250, 25);
        add(txtMaterial);

        JLabel lblQuantidade = new JLabel("Quantidade:");
        lblQuantidade.setBounds(370, 60, 80, 25);
        add(lblQuantidade);

        NumberFormat formatoInteiro = NumberFormat.getIntegerInstance();
        NumberFormatter formatterQuantidade = new NumberFormatter(formatoInteiro);
        formatterQuantidade.setAllowsInvalid(false);
        formatterQuantidade.setMinimum(0);
        txtQuantidade = new JFormattedTextField(formatterQuantidade);
        txtQuantidade.setBounds(460, 60, 100, 25);
        add(txtQuantidade);

        JButton btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(20, 100, 100, 30);
        add(btnSalvar);

        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(130, 100, 100, 30);
        btnAlterar.setVisible(false);
        add(btnAlterar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(240, 100, 100, 30);
        btnExcluir.setVisible(false);
        add(btnExcluir);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(350, 100, 100, 30);
        btnAlterar.setVisible(false);
        btnExcluir.setVisible(false);
        add(btnCancelar);

        JButton btnVoltar = new JButton("Voltar");
        btnVoltar.setBounds(460, 100, 100, 30);
        add(btnVoltar);

        modelo = new DefaultTableModel();
        modelo.addColumn("Projeto");
        modelo.addColumn("Etapa");
        modelo.addColumn("Material");
        modelo.addColumn("Quantidade Usada");

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 150, 700, 280);
        add(scroll);

        // AÇÕES
        btnSalvar.addActionListener(e -> {
            Vector<String> linha = new Vector<>();
            linha.add(txtProjeto.getText());
            linha.add(txtEtapa.getText());
            linha.add(txtMaterial.getText());
            linha.add(txtQuantidade.getText());
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
                modelo.setValueAt(txtProjeto.getText(), row, 0);
                modelo.setValueAt(txtEtapa.getText(), row, 1);
                modelo.setValueAt(txtMaterial.getText(), row, 2);
                modelo.setValueAt(txtQuantidade.getValue(), row, 3);
                limparCampos();
            }
        });

        btnCancelar.addActionListener(e -> limparCampos());

        btnVoltar.addActionListener(e -> dispose());

        tabela.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                int row = tabela.getSelectedRow();
                if (row >= 0) {
                    txtProjeto.setText((String) modelo.getValueAt(row, 0));
                    txtEtapa.setText((String) modelo.getValueAt(row, 1));
                    txtMaterial.setText((String) modelo.getValueAt(row, 2));
                    txtQuantidade.setValue(modelo.getValueAt(row, 3));

                    btnAlterar.setVisible(true);
                    btnExcluir.setVisible(true);
                }
            }
        });

        carregarDadosNaTabela();
        setVisible(true);
    }

    private void carregarDadosNaTabela() {
        modelo.setRowCount(0);
        UsoMaterialController controller = new UsoMaterialController();
        List<UsoMaterial> lista = controller.listarUsos();

        for (UsoMaterial uso : lista) {
            Vector<Object> row = new Vector<>();
            String nomeProjeto = (uso.getEtapa().getProjeto() != null) ? uso.getEtapa().getProjeto().getNome() : "Desconhecido";
            row.add(nomeProjeto);
            row.add(uso.getEtapa().getDescricao());
            row.add(uso.getMaterial().getNome());
            row.add(uso.getQuantidade());
            modelo.addRow(row);
        }
    }

    private void limparCampos() {
        txtProjeto.setText("");
        txtEtapa.setText("");
        txtMaterial.setText("");
        txtQuantidade.setValue(null);
        tabela.clearSelection();
        btnAlterar.setVisible(false);
        btnExcluir.setVisible(false);
    }
}
