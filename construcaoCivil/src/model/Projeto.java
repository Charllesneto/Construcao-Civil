package model;

public class Projeto {
    private int idProjeto;
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFimPrevista;
    private String status;
    private int fkClienteProjeto;
    private int fkProfissionalProjeto;

    // Construtor vazio
    public Projeto() {}

    // Construtor com parâmetros
    public Projeto(int idProjeto, String nome, String descricao, String dataInicio, String dataFimPrevista, String status, int fkClienteProjeto, int fkProfissionalProjeto) {
        this.idProjeto = idProjeto;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
        this.status = status;
        this.fkClienteProjeto = fkClienteProjeto;
        this.fkProfissionalProjeto = fkProfissionalProjeto;
    }
