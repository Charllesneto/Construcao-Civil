import java.awt.event.*;
import java.text.ParseException;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.text.MaskFormatter;

public class Tela {

    public static void montarTela() throws ParseException {
        JFrame frame = new JFrame("Cadastro de Empresa");
        frame.setBounds(450, 170, 650, 500);
        frame.setLayout(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel lblCNPJ = new JLabel("CNPJ:");
        lblCNPJ.setBounds(10, 10, 50, 25);
        frame.add(lblCNPJ);

        JFormattedTextField txtCNPJ = new JFormattedTextField(mascara("##.###.###/####-##"));
        txtCNPJ.setBounds(70, 10, 150, 25);
        frame.add(txtCNPJ);

       
        JLabel lblRazao = new JLabel("RZ. SOC.:");
        lblRazao.setBounds(10, 45, 60, 25);
        frame.add(lblRazao);

        JTextField txtRazao = new JTextField();
        txtRazao.setBounds(70, 45, 400, 25);
        frame.add(txtRazao);

       
        JLabel lblTipo = new JLabel("Tipo:");
        lblTipo.setBounds(10, 80, 60, 25);
        frame.add(lblTipo);

        String[] tipos = { "LTDA", "MEI" };
        JComboBox<String> cbTipo = new JComboBox<>(tipos);
        cbTipo.setBounds(70, 80, 100, 25);
        frame.add(cbTipo);

       
        JLabel lblDataFund = new JLabel("DT FUND.:");
        lblDataFund.setBounds(200, 80, 80, 25);
        frame.add(lblDataFund);

        JFormattedTextField txtDataFund = new JFormattedTextField(mascara("##/##/####"));
        txtDataFund.setBounds(280, 80, 100, 25);
        frame.add(txtDataFund);

        
        String[] colunas = { "CNPJ", "RZ. SOC.", "TIPO", "DT. FUND." };
        DefaultTableModel model = new DefaultTableModel(colunas, 0);
        JTable tabela = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(tabela);
        scrollPane.setBounds(10, 120, 600, 200);
        frame.add(scrollPane);

        
        JButton btnGravar = new JButton("Gravar");
        btnGravar.setBounds(60, 340, 100, 30);
        frame.add(btnGravar);

        JButton btnEditar = new JButton("Editar");
        btnEditar.setBounds(170, 340, 100, 30);
        frame.add(btnEditar);

        JButton btnExcluir = new JButton("Excluir");
        btnExcluir.setBounds(280, 340, 100, 30);
        frame.add(btnExcluir);

        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(390, 340, 100, 30);
        frame.add(btnCancelar);

        
        frame.setVisible(true);

      
        btnGravar.addActionListener(e -> {
            String cnpj = txtCNPJ.getText();
            String razao = txtRazao.getText();
            String tipo = (String) cbTipo.getSelectedItem();
            String dataFund = txtDataFund.getText();

            model.addRow(new Object[]{cnpj, razao, tipo, dataFund});
        });
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
