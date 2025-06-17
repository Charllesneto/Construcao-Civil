package view;

import controller.ProjetoController;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;
import java.awt.event.*;
import java.text.ParseException;
import java.util.List;
import java.util.Vector;
import model.Projeto;

public class Tela_Projeto extends JFrame {

    private JTextField txtNomeProjeto, txtCliente;
    private JFormattedTextField txtDataInicio;
    private JFormattedTextField txtDataFim;
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

        JLabel lblDataFim = new JLabel("Data Fim Prevista:");
        lblDataFim.setBounds(270, 60, 120, 25);
        add(lblDataFim);

        try {
            MaskFormatter dataMask = new MaskFormatter("##/##/####");
            dataMask.setPlaceholderCharacter('_');
            txtDataFim = new JFormattedTextField(dataMask);
        } catch (ParseException e) {
            txtDataFim = new JFormattedTextField();
        }
        txtDataFim.setBounds(400, 60, 150, 25);
        add(txtDataFim);

        btnSalvar = new JButton("Salvar");
        btnSalvar.setBounds(20, 100, 100, 30);
        add(btnSalvar);

        btnAlterar = new JButton("Alterar");
        btnAlterar.setBounds(130, 100, 100, 30);
        btnAlterar.setEnabled(false);
        add(btnAlterar);

        btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(240, 100, 100, 30);
        btnExcluir.setEnabled(false);
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
        modelo.addColumn("Data Fim Prevista");

        tabela = new JTable(modelo);
        JScrollPane scroll = new JScrollPane(tabela);
        scroll.setBounds(20, 150, 640, 270);
        add(scroll);

        setVisible(true);
        carregarDadosNaTabela();

        // AÇÕES
        btnSalvar.addActionListener(e -> {
            Vector<String> linha = new Vector<>();
            linha.add(txtNomeProjeto.getText());
            linha.add(txtCliente.getText());
            linha.add(txtDataInicio.getText());
            linha.add(txtDataFim.getText()
            );
            modelo.addRow(linha);
            limparCampos();
            carregarDadosNaTabela();
        });

        btnExcluir.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row >= 0) {
                modelo.removeRow(row);
                limparCampos();
                carregarDadosNaTabela();
            }
        });

        btnAlterar.addActionListener(e -> {
            int row = tabela.getSelectedRow();
            if (row >= 0) {
                modelo.setValueAt(txtNomeProjeto.getText(), row, 0);
                modelo.setValueAt(txtCliente.getText(), row, 1);
                modelo.setValueAt(txtDataInicio.getText(), row, 2);
                modelo.setValueAt(txtDataFim.getText(), row, 3);
                limparCampos();
                carregarDadosNaTabela();
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
                txtDataFim.setText((String) modelo.getValueAt(row, 3));
                btnAlterar.setEnabled(true);
                btnExcluir.setEnabled(true);
            }
        });
    }

    private void carregarDadosNaTabela() {
        modelo.setRowCount(0);
        ProjetoController controller = new ProjetoController();
        List<Projeto> lista = controller.listarProjetos();

        for (Projeto projeto : lista) {
            Vector<Object> linha = new Vector<>();
            linha.add(projeto.getNome());
            linha.add(projeto.getCliente().getNome());
            linha.add(projeto.getDataInicio());
            linha.add(projeto.getDataFimPrevista());
            modelo.addRow(linha);
        }
    }

    private void limparCampos() {
        txtNomeProjeto.setText("");
        txtCliente.setText("");
        txtDataInicio.setValue(null);
        txtDataFim.setValue(null);
        tabela.clearSelection();
        btnAlterar.setEnabled(false);
        btnExcluir.setEnabled(false);
    }
}
