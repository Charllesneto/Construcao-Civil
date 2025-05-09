package main;

import controller.*;
import model.*;
import java.util.List;
import java.util.Scanner;

public class app {

    private static final Scanner scanner = new Scanner(System.in);
    private static final ClienteController clienteController = new ClienteController();
    private static final ProfissionalController profissionalController = new ProfissionalController();
    private static final ProjetoController projetoController = new ProjetoController();
    private static final EtapaController etapaController = new EtapaController();
    private static final MaterialController materialController = new MaterialController();
    private static final UsoMaterialController usoMaterialController = new UsoMaterialController();

    public static void main(String[] args) {
        int opcao;

        do {
            System.out.println("\n *********** SISTEMA DE CONSTRUÇÃO CIVIL ***********");
            System.out.println("1. Gerenciar Clientes");
            System.out.println("2. Gerenciar Profissionais");
            System.out.println("3. Gerenciar Projetos");
            System.out.println("4. Gerenciar Etapas");
            System.out.println("5. Gerenciar Materiais");
            System.out.println("6. Gerenciar Uso de Materiais");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 ->
                    menuClientes();
                //case 2 -> menuProfissionais();
                //case 3 -> menuProjetos();
                //case 4 -> menuEtapas();
                //case 5 -> menuMateriais();
                //case 6 -> menuUsoMateriais();
                case 0 ->
                    System.out.println("Encerrando...");
                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menuClientes() {
        int opcao;
        do {
            System.out.println("\n==== MENU CLIENTES ====");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    scanner.nextLine(); // limpar buffer
                    System.out.print("ID: ");
                    int id = scanner.nextInt(); scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Tipo: ");
                    String tipo = scanner.nextLine();
                    System.out.print("CPF/CNPJ: ");
                    String cpfCnpj = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String tel = scanner.nextLine();
                    Cliente c = new Cliente(id, nome, tipo, cpfCnpj, tel);
                    clienteController.adicionarCliente(c);
                }
                case 2 -> {
                    List<Cliente> lista = clienteController.listarClientes();
                    lista.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID do cliente a atualizar: ");
                    int id = scanner.nextInt(); scanner.nextLine();
                    Cliente c = clienteController.buscarPorId(id);
                    if (c != null) {
                        System.out.print("Novo nome: ");
                        c.setNome(scanner.nextLine());
                        System.out.print("Novo tipo: ");
                        c.setTipoCliente(scanner.nextLine());
                        System.out.print("Novo CPF/CNPJ: ");
                        c.setCpfCnpj(scanner.nextLine());
                        System.out.print("Novo telefone: ");
                        c.setTelefone(scanner.nextLine());
                        clienteController.atualizarCliente(c);
                    } else {
                        System.out.println("Cliente não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do cliente a remover: ");
                    int id = scanner.nextInt();
                    clienteController.removerCliente(id);
                }
            }
        } while (opcao != 0);
    }

}

        