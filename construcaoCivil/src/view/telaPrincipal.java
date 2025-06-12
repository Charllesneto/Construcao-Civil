
package view;

/**
 *
 * @author ThinkPad T470
 */

    
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class telaPrincipal extends JFrame {

    public telaPrincipal() {
        setTitle("Sistema de Construção Civil");
        setSize(400, 450);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setLayout(null);

        JLabel titulo = new JLabel("SISTEMA DE CONSTRUÇÃO CIVIL", JLabel.CENTER);
        titulo.setBounds(30, 20, 320, 30);
        add(titulo);

        JButton btnClientes = new JButton("Gerenciar Clientes");
        btnClientes.setBounds(100, 70, 200, 30);
        add(btnClientes);

        JButton btnProfissionais = new JButton("Gerenciar Profissionais");
        btnProfissionais.setBounds(100, 110, 200, 30);
        add(btnProfissionais);

        JButton btnProjetos = new JButton("Gerenciar Projetos");
        btnProjetos.setBounds(100, 150, 200, 30);
        add(btnProjetos);

        JButton btnEtapas = new JButton("Gerenciar Etapas");
        btnEtapas.setBounds(100, 190, 200, 30);
        add(btnEtapas);

        JButton btnMateriais = new JButton("Gerenciar Materiais");
        btnMateriais.setBounds(100, 230, 200, 30);
        add(btnMateriais);

        JButton btnUsoMateriais = new JButton("Gerenciar Uso de Materiais");
        btnUsoMateriais.setBounds(100, 270, 200, 30);
        add(btnUsoMateriais);

        JButton btnSair = new JButton("Sair");
        btnSair.setBounds(100, 320, 200, 30);
        add(btnSair);

        // AÇÕES DOS BOTÕES
        btnClientes.addActionListener(e -> {
            new tela_cliente(); // Abre a tela de clientes
        });

        btnProfissionais.addActionListener(e -> {
            new Tela_Proficional(); // Abre a tela de profissionais
        });

        btnProjetos.addActionListener(e -> {
            new Tela_Projeto(); // Abre a tela de projetos
        });

        btnEtapas.addActionListener(e -> {
            new Tela_etapa(); // Abre a tela de etapas
        });

        btnMateriais.addActionListener(e -> {
            new Tela_Material(); // Abre a tela de materiais
        });

        btnUsoMateriais.addActionListener(e -> {
            new Tela_Uso_Material(); // Abre a tela de uso de materiais
        });

        btnSair.addActionListener(e -> {
            dispose(); // Fecha o programa
        });

        setVisible(true);
    }

    public static void main(String[] args) {
        new telaPrincipal(); // inicia a aplicação
    }
}
