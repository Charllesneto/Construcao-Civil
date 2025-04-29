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
    // Getters e Setters
    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        this.idProjeto = idProjeto;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(String dataInicio) {
        this.dataInicio = dataInicio;
    }

    public String getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(String dataFimPrevista) {
        this.dataFimPrevista = dataFimPrevista;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getFkClienteProjeto() {
        return fkClienteProjeto;
    }

    public void setFkClienteProjeto(int fkClienteProjeto) {
        this.fkClienteProjeto = fkClienteProjeto;
    }

    public int getFkProfissionalProjeto() {
        return fkProfissionalProjeto;
    }

    public void setFkProfissionalProjeto(int fkProfissionalProjeto) {
        this.fkProfissionalProjeto = fkProfissionalProjeto;
    }

    // Método toString para facilitar a exibição do projeto
    @Override
    public String toString() {
        return "Projeto{" +
                "idProjeto=" + idProjeto +
                ", nome='" + nome + '\'' +
                ", descricao='" + descricao + '\'' +
                ", dataInicio='" + dataInicio + '\'' +
                ", dataFimPrevista='" + dataFimPrevista + '\'' +
                ", status='" + status + '\'' +
                ", fkClienteProjeto=" + fkClienteProjeto +
                ", fkProfissionalProjeto=" + fkProfissionalProjeto +
                '}';
    }
}