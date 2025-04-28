
package br.com.charlles;

public class Profissional {
    
    private int idProfissional;
    private String nome;
    private String cargo;
    private String cpf;
    private String telefone;

    // Construtor vazio
    public Profissional() {
    }

    // Construtor completo
    public Profissional(int idProfissional, String nome, String cargo, String cpf, String telefone) {
        this.idProfissional = idProfissional;
        this.nome = nome;
        this.cargo = cargo;
        this.cpf = cpf;
        this.telefone = telefone;
    }

    // Getters e Setters
    public int getIdProfissional() {
        return idProfissional;
    }

    public void setIdProfissional(int idProfissional) {
        this.idProfissional = idProfissional;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getCargo() {
        return cargo;
    }

    public void setCargo(String cargo) {
        this.cargo = cargo;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    // Método toString (opcional, mas ajuda muito para visualizar os dados)
    @Override
    public String toString() {
        return "Profissional{" +
                "idProfissional=" + idProfissional +
                ", nome='" + nome + '\'' +
                ", cargo='" + cargo + '\'' +
                ", cpf='" + cpf + '\'' +
                ", telefone='" + telefone + '\'' +
                '}';
    }
}
    

