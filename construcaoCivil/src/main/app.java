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
        // menu principal 
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
                case 3 -> menuProjetos();
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

    // sub menu de clientes para realizar as operações crud,o laço de repetição itera sobre o switch case
    // que possibilita retornar para o menu principal assim que termine a ação
    private static void menuClientes() {
        int opcao;
        do {
            System.out.println("************ MENU CLIENTES **********");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();

            switch (opcao) {
                case 1 -> {
                    scanner.nextLine();
                    System.out.print("ID: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Tipo: ");
                    String tipo = scanner.nextLine();
                    System.out.print("CPF/CNPJ: ");
                    String cpfCnpj = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String tel = scanner.nextLine();
                    Cliente cli = new Cliente(id, nome, tipo, cpfCnpj, tel);
                    clienteController.adicionarCliente(cli);
                }
                case 2 -> {
                    List<Cliente> lista = clienteController.listarClientes();
                    lista.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID do cliente a atualizar: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    Cliente cli = clienteController.buscarPorId(id);
                    if (cli != null) {
                        System.out.print("Novo nome: ");
                        cli.setNome(scanner.nextLine());
                        System.out.print("Novo tipo: ");
                        cli.setTipoCliente(scanner.nextLine());
                        System.out.print("Novo CPF/CNPJ: ");
                        cli.setCpfCnpj(scanner.nextLine());
                        System.out.print("Novo telefone: ");
                        cli.setTelefone(scanner.nextLine());
                        clienteController.atualizarCliente(cli);
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

    private static void menuProjetos() {

        int opcao;
        do {
            System.out.println(" ****** MENU DE PROJETOS ******");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine();
            switch (opcao) {
                case 1 -> {
                    System.out.print("ID do Projeto: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    // Escolher Cliente
                    System.out.println("Clientes disponíveis:");
                    clienteController.listarClientes()
                            .forEach(c -> System.out.println(c.getIdCliente() + " - " + c.getNome()));
                    System.out.print("ID do Cliente responsável: ");
                    int idCli = scanner.nextInt();
                    scanner.nextLine();
                    Cliente cli = clienteController.buscarPorId(idCli);
                    if (cli == null) {
                        System.out.println("Cliente não encontrado.");
                        break;
                    }

                    System.out.print("Nome do Projeto: ");
                    String nome = scanner.nextLine();
                    System.out.print("Descrição: ");
                    String desc = scanner.nextLine();
                    System.out.print("Data de Início (formato livre, ex: 2025-05-09): ");
                    String di = scanner.nextLine();
                    System.out.print("Data Fim Prevista (formato livre): ");
                    String df = scanner.nextLine();
                    System.out.print("Status: ");
                    String status = scanner.nextLine();

                    Projeto p = new Projeto(id, nome, desc, di, df, status, cli);
                    projetoController.adicionarProjeto(p);
                }
                case 2 -> {
                    projetoController.listarProjetos()
                            .forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID do Projeto a atualizar: ");
                    int idUp = scanner.nextInt();
                    scanner.nextLine();
                    Projeto p = projetoController.buscarPorId(idUp);
                    if (p != null) {
                        System.out.print("Novo nome (" + p.getNome() + "): ");
                        p.setNome(scanner.nextLine());
                        System.out.print("Nova descrição: ");
                        p.setDescricao(scanner.nextLine());
                        System.out.print("Nova data de início (" + p.getDataInicio() + "): ");
                        String diUp = scanner.nextLine();
                        if (!diUp.isBlank()) {
                            p.setDataInicio(diUp);
                        }
                        System.out.print("Nova data fim prevista (" + p.getDataFimPrevista() + "): ");
                        String dfUp = scanner.nextLine();
                        if (!dfUp.isBlank()) {
                            p.setDataFimPrevista(dfUp);
                        }
                        System.out.print("Novo status (" + p.getStatus() + "): ");
                        p.setStatus(scanner.nextLine());

                        // Se quiser trocar de cliente:
                        // Oferece a possibilidade de trocar de cliente, para evitar de ter que digitar os dados novamente
                        
                        System.out.println("Clientes:");
                        clienteController.listarClientes()
                                .forEach(c -> System.out.println(c.getIdCliente() + " - " + c.getNome()));
                        System.out.print("Novo ID do Cliente (ou ENTER para manter): ");
                        String entrada = scanner.nextLine();
                        if (!entrada.isBlank()) {
                            int novoIdCli = Integer.parseInt(entrada);
                            Cliente novoCli = clienteController.buscarPorId(novoIdCli);
                            if (novoCli != null) {
                                p.setCliente(novoCli);
                            } else {
                                System.out.println("Cliente não encontrado; mantendo antigo.");
                            }
                        }

                        projetoController.atualizarProjeto(p);
                    } else {
                        System.out.println("Projeto não encontrado.");
                    }
                }
                case 4 -> {
                    System.out.print("ID do Projeto a remover: ");
                    int idRem = scanner.nextInt();
                    projetoController.removerProjeto(idRem);
                }
                case 0 -> {
                    /* volta ao menu principal */ }
                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
    
    private static void menuProfissionais() {
        
    }
}
