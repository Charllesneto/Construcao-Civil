package model;

public class Projeto {
    private int idProjeto;
    private String nome;
    private String descricao;
    private String dataInicio;
    private String dataFimPrevista;
    private String status;
    private Projeto projeto;
    private Profissional profissional;
    private Cliente cliente;

    // Construtor vazio
    public Projeto() {}

    // Construtor com parâmetros
    public Projeto(int idProjeto, String nome, String descricao, String dataInicio, String dataFimPrevista, String status,
            Projeto projeto,Profissional profissional,Cliente cliente) {
        this.idProjeto = idProjeto;
        this.nome = nome;
        this.descricao = descricao;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
        this.status = status;
        this.projeto = projeto;
        this.profissional = profissional;
        this.cliente = cliente;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public Projeto getProjeto() {
        return projeto;
    }

    public void setProjeto(Projeto projeto) {
        this.projeto = projeto;
    }

    public Profissional getProfissional() {
        return profissional;
    }

    public void setProfissional(Profissional profissional) {
        this.profissional = profissional;
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
                ", Projeto =" + projeto +
                ", Profissional=" + profissional +
                '}';
    }
}