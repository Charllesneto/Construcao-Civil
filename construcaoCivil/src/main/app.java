package main;

import controller.*;
import model.*;
import java.util.List;
import java.util.Scanner;
import java.sql.Date;


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
                case 2 ->
                    menuProfissionais();
                case 3 ->
                    menuProjetos();
                case 4 ->
                    menuEtapas();
                case 5 ->
                    menuMateriais();
                case 6 ->
                    menuUsoMateriais();
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
        int opcao;
        do {
            System.out.println("******** MENU PROFISSIONAIS *******");
            System.out.println("1. Adicionar");
            System.out.println("2. Listar");
            System.out.println("3. Atualizar");
            System.out.println("4. Remover");
            System.out.println("0. Voltar");
            System.out.print("Escolha: ");
            opcao = scanner.nextInt();
            scanner.nextLine(); // limpa buffer

            switch (opcao) {
                case 1 -> {

                    System.out.print("ID do Profissional: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Cargo: ");
                    String cargo = scanner.nextLine();
                    System.out.print("CPF: ");
                    String cpf = scanner.nextLine();
                    System.out.print("Telefone: ");
                    String tel = scanner.nextLine();

                    Profissional p = new Profissional(id, nome, cargo, cpf, tel);
                    profissionalController.adicionarProfissional(p);
                }

                case 2 -> {
                    List<Profissional> lista = profissionalController.listarProfissionais();
                    lista.forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID do Profissional a atualizar: ");
                    int idUp = scanner.nextInt();
                    scanner.nextLine();
                    Profissional p = profissionalController.buscarPorId(idUp);
                    if (p == null) {
                        System.out.println("Profissional não encontrado.");
                        break;
                    }

                    System.out.print("Novo nome (" + p.getNome() + "): ");
                    String novoNome = scanner.nextLine();
                    if (!novoNome.isBlank()) {
                        p.setNome(novoNome);
                    }

                    System.out.print("Novo cargo (" + p.getCargo() + "): ");
                    String novoCargo = scanner.nextLine();
                    if (!novoCargo.isBlank()) {
                        p.setCargo(novoCargo);
                    }

                    System.out.print("Novo CPF (" + p.getCpf() + "): ");
                    String novoCpf = scanner.nextLine();
                    if (!novoCpf.isBlank()) {
                        p.setCpf(novoCpf);
                    }

                    System.out.print("Novo telefone (" + p.getTelefone() + "): ");
                    String novoTel = scanner.nextLine();
                    if (!novoTel.isBlank()) {
                        p.setTelefone(novoTel);
                    }

                    profissionalController.atualizarProfissional(p);
                }
                case 4 -> {
                    System.out.print("ID do Profissional a remover: ");
                    int idRem = scanner.nextInt();
                    scanner.nextLine();
                    profissionalController.removerProfissional(idRem);
                }

                case 0 -> {
                    // volta ao menu principal
                }

                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menuEtapas() {
        int opcao;

        do {
            System.out.println("********** MENU ETAPAS *********");
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
                    System.out.print("ID da Etapa: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    // Selecionar Projeto
                    System.out.println("Projetos disponíveis:");
                    projetoController.listarProjetos()
                            .forEach(p -> System.out.println(p.getIdProjeto() + " - " + p.getNome()));
                    System.out.print("ID do Projeto: ");
                    int idProj = scanner.nextInt();
                    scanner.nextLine();
                    Projeto projeto = projetoController.buscarPorId(idProj);
                    if (projeto == null) {
                        System.out.println("Projeto não encontrado. Operação cancelada.");
                        break;
                    }

                    // Selecionar Profissional
                    System.out.println("Profissionais disponíveis:");
                    profissionalController.listarProfissionais()
                            .forEach(pr -> System.out.println(pr.getIdProfissional() + " - " + pr.getNome()));
                    System.out.print("ID do Profissional responsável: ");
                    int idProf = scanner.nextInt();
                    scanner.nextLine();
                    Profissional prof = profissionalController.buscarPorId(idProf);
                    if (prof == null) {
                        System.out.println("Profissional não encontrado. Operação cancelada.");
                        break;
                    }

                    System.out.print("Descrição da Etapa: ");
                    String desc = scanner.nextLine();
                    System.out.print("Status: ");
                    String status = scanner.nextLine();
                    System.out.print("Data de Início (ex: 2025-05-10): ");
                    String inicioStr = scanner.nextLine();
                    Date dataInicio = Date.valueOf(inicioStr);
                    System.out.print("Data Fim Prevista (ex: 2025-06-15): ");
                    String fimStr = scanner.nextLine();
                    Date dataFim = Date.valueOf(fimStr);

                    Etapa e = new Etapa(id, desc, status, dataInicio, dataFim , projeto, prof);
                    etapaController.adicionarEtapa(e);
                }

                case 2 -> {
                    etapaController.listarEtapas()
                            .forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID da Etapa a atualizar: ");
                    int idUp = scanner.nextInt();
                    scanner.nextLine();
                    Etapa e = etapaController.buscarPorId(idUp);
                    if (e == null) {
                        System.out.println("Etapa não encontrada.");
                        break;
                    }

                    System.out.print("Nova descrição (" + e.getDescricao() + "): ");
                    String nd = scanner.nextLine();
                    if (!nd.isBlank()) {
                        e.setDescricao(nd);
                    }

                    System.out.print("Novo status (" + e.getStatus() + "): ");
                    String ns = scanner.nextLine();
                    if (!ns.isBlank()) {
                        e.setStatus(ns);
                    }

                    System.out.print("Nova data de início (" + e.getDataInicio() + "): ");
                    String diUp = scanner.nextLine();
                    if (!diUp.isBlank()) {
                        Date novaDataInicio = Date.valueOf(diUp);
                        e.setDataInicio(novaDataInicio);
                    }

                    System.out.print("Nova data fim prevista (" + e.getDataFimPrevista() + "): ");
                    String dfUp = scanner.nextLine();
                    if (!dfUp.isBlank()) {
                        Date novaDataFim = Date.valueOf(dfUp);
                        e.setDataFimPrevista(novaDataFim);
                    }

                    // Trocar Projeto (opcional)
                    System.out.println("Projetos disponíveis:");
                    projetoController.listarProjetos()
                            .forEach(p -> System.out.println(p.getIdProjeto() + " - " + p.getNome()));
                    System.out.print("Novo ID do Projeto (ou ENTER para manter " + e.getProjeto().getIdProjeto() + "): ");
                    String entProj = scanner.nextLine();
                    if (!entProj.isBlank()) {
                        int newIdP = Integer.parseInt(entProj);
                        Projeto newProj = projetoController.buscarPorId(newIdP);
                        if (newProj != null) {
                            e.setProjeto(newProj);
                        } else {
                            System.out.println("Projeto não encontrado; mantendo o atual.");
                        }
                    }

                    // Trocar Profissional (opcional)
                    System.out.println("Profissionais disponíveis:");
                    profissionalController.listarProfissionais()
                            .forEach(pr -> System.out.println(pr.getIdProfissional() + " - " + pr.getNome()));
                    System.out.print("Novo ID do Profissional (ou ENTER para manter " + e.getProfissional().getIdProfissional() + "): ");
                    String entProf = scanner.nextLine();
                    if (!entProf.isBlank()) {
                        int newIdPr = Integer.parseInt(entProf);
                        Profissional newPr = profissionalController.buscarPorId(newIdPr);
                        if (newPr != null) {
                            e.setProfissional(newPr);
                        } else {
                            System.out.println("Profissional não encontrado; mantendo o atual.");
                        }
                    }

                    etapaController.atualizarEtapa(e);
                }

                case 4 -> {
                    System.out.print("ID da Etapa a remover: ");
                    int idRem = scanner.nextInt();
                    scanner.nextLine();
                    etapaController.removerEtapa(idRem);
                }

                case 0 -> {
                    // volta ao menu principal
                }

                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menuMateriais() {
        int opcao;

        do {
            System.out.println("********** MENU MATERIAIS *********");
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
                    System.out.print("ID do Material: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.print("Nome: ");
                    String nome = scanner.nextLine();
                    System.out.print("Unidade de Medida: ");
                    String unidade = scanner.nextLine();
                    System.out.print("Preço Unitário: ");
                    double preco = scanner.nextDouble();
                    scanner.nextLine();

                    Material m = new Material(id, nome, unidade, preco);
                    materialController.adicionarMaterial(m);
                }
                case 2 -> {
                    List<Material> lista = materialController.listarMateriais();
                    lista.forEach(System.out::println);
                }

                case 3 -> {
                    System.out.print("ID do Material a atualizar: ");
                    int idUp = scanner.nextInt();
                    scanner.nextLine();
                    Material m = materialController.buscarPorId(idUp);
                    if (m == null) {
                        System.out.println("Material não encontrado.");
                        break;
                    }

                    System.out.print("Novo nome (" + m.getNome() + "): ");
                    String novoNome = scanner.nextLine();
                    if (!novoNome.isBlank()) {
                        m.setNome(novoNome);
                    }

                    System.out.print("Nova unidade de medida (" + m.getUnidadeMedida() + "): ");
                    String novaUnidade = scanner.nextLine();
                    if (!novaUnidade.isBlank()) {
                        m.setUnidadeMedida(novaUnidade);
                    }

                    System.out.print("Novo preço unitário (" + m.getPrecoUnitario() + "): ");
                    String entradaPreco = scanner.nextLine();
                    if (!entradaPreco.isBlank()) {
                        double novoPreco = Double.parseDouble(entradaPreco);
                        m.setPrecoUnitario(novoPreco);
                    }

                    materialController.atualizarMaterial(m);
                }
                case 4 -> {
                    System.out.print("ID do Material a remover: ");
                    int idRem = scanner.nextInt();
                    scanner.nextLine();
                    materialController.removerMaterial(idRem);
                }

                case 0 -> {
                    // volta ao menu principal
                }

                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }

    private static void menuUsoMateriais() {
        int opcao;

        do {
            System.out.println("********** MENU USO DE MATERIAIS *********");
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
                    System.out.print("ID do Uso de Material: ");
                    int id = scanner.nextInt();
                    scanner.nextLine();

                    // Selecionar Material
                    System.out.println("Materiais disponíveis:");
                    materialController.listarMateriais()
                            .forEach(m -> System.out.println(m.getIdMaterial() + " - " + m.getNome()));
                    System.out.print("ID do Material: ");
                    int idMat = scanner.nextInt();
                    scanner.nextLine();
                    Material mat = materialController.buscarPorId(idMat);
                    if (mat == null) {
                        System.out.println("Material não encontrado. Operação cancelada.");
                        break;
                    }
                    // Selecionar Etapa
                    System.out.println("Etapas disponíveis:");
                    etapaController.listarEtapas()
                            .forEach(e -> System.out.println(e.getIdEtapa() + " - " + e.getDescricao()));
                    System.out.print("ID da Etapa: ");
                    int idEtap = scanner.nextInt();
                    scanner.nextLine();
                    Etapa etap = etapaController.buscarPorId(idEtap);
                    if (etap == null) {
                        System.out.println("Etapa não encontrada. Operação cancelada.");
                        break;
                    }

                    System.out.print("Quantidade usada: ");
                    double qtd = scanner.nextDouble();
                    scanner.nextLine();

                    UsoMaterial uso = new UsoMaterial(id, mat, etap, qtd);
                    usoMaterialController.adicionarUso(uso);
                }
                case 2 -> {
                    usoMaterialController.listarUsos()
                            .forEach(System.out::println);
                }
                case 3 -> {
                    System.out.print("ID do Uso de Material a atualizar: ");
                    int idUp = scanner.nextInt();
                    scanner.nextLine();
                    UsoMaterial uso = usoMaterialController.buscarPorId(idUp);
                    if (uso == null) {
                        System.out.println("Registro não encontrado.");
                        break;
                    }// Atualizar Material
                    System.out.println("Materiais disponíveis:");
                    materialController.listarMateriais()
                            .forEach(m -> System.out.println(m.getIdMaterial() + " - " + m.getNome()));
                    System.out.print("Novo ID do Material (ou ENTER para manter " + uso.getMaterial().getIdMaterial() + "): ");
                    String inMat = scanner.nextLine();
                    if (!inMat.isBlank()) {
                        int newMatId = Integer.parseInt(inMat);
                        Material newMat = materialController.buscarPorId(newMatId);
                        if (newMat != null) {
                            uso.setMaterial(newMat);
                        } else {
                            System.out.println("Material não encontrado; mantendo o atual.");
                        }
                    }

                    // Atualizar Etapa
                    System.out.println("Etapas disponíveis:");
                    etapaController.listarEtapas()
                            .forEach(e -> System.out.println(e.getIdEtapa() + " - " + e.getDescricao()));
                    System.out.print("Novo ID da Etapa (ou ENTER para manter " + uso.getEtapa().getIdEtapa() + "): ");
                    String inEtap = scanner.nextLine();
                    if (!inEtap.isBlank()) {
                        int newEtapId = Integer.parseInt(inEtap);
                        Etapa newEtap = etapaController.buscarPorId(newEtapId);
                        if (newEtap != null) {
                            uso.setEtapa(newEtap);
                        } else {
                            System.out.println("Etapa não encontrada; mantendo a atual.");
                        }
                    }

                    // Atualizar Quantidade
                    System.out.print("Nova quantidade (" + uso.getQuantidade() + "): ");
                    String inQtd = scanner.nextLine();
                    if (!inQtd.isBlank()) {
                        double newQtd = Double.parseDouble(inQtd);
                        uso.setQuantidade(newQtd);
                    }

                    usoMaterialController.atualizarUso(uso);
                }

                case 4 -> {
                    System.out.print("ID do Uso de Material a remover: ");
                    int idRem = scanner.nextInt();
                    scanner.nextLine();
                    usoMaterialController.removerUso(idRem);
                }

                case 0 -> {
                    // volta ao menu principal
                }

                default ->
                    System.out.println("Opção inválida.");
            }
        } while (opcao != 0);
    }
    
}
 