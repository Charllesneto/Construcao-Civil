
package model;

public class Cliente {
        private int idCliente;
    private String nome;
    private String tipoCliente;
    private String cpfCnpj;
    private String telefone;

    public Cliente(int idCliente, String nome, String tipoCliente, String cpfCnpj, String telefone) {
        this.idCliente = idCliente;
        this.nome = nome;
        this.tipoCliente = tipoCliente;
        this.cpfCnpj = cpfCnpj;
        this.telefone = telefone;
    }

    // Getters
    public int getIdCliente() {
        return idCliente;
    }

    public String getNome() {
        return nome;
    }

    public String getTipoCliente() {
        return tipoCliente;
    }

    public String getCpfCnpj() {
        return cpfCnpj;
    }

    public String getTelefone() {
        return telefone;
    }

    // Setters
    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setTipoCliente(String tipoCliente) {
        this.tipoCliente = tipoCliente;
    }

    public void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    @Override
    public String toString() {
        return "Cliente [idCliente=" + idCliente + ", nome=" + nome + ", tipoCliente=" + tipoCliente +
               ", cpfCnpj=" + cpfCnpj + ", telefone=" + telefone + "]";
    }
}