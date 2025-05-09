package model;

/**
 *
 * @author Nodival Neto
 */

import java.time.LocalDate;

public class Etapa {

    private int idEtapa;
    private String descricao;
    private String status;
    private String dataInicio;
    private String dataFimPrevista;
    private Projeto projeto;
    private Profissional  profissional;

    public Etapa(int idEtapa, String descricao, String status, String dataInicio, String dataFimPrevista,Projeto projeto, Profissional profissional) {
        this.idEtapa = idEtapa;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
        this.projeto = projeto;
        this.profissional = profissional;
    }
    public Etapa(int idEtapa, String descricao, String status, String dataInicio, String dataFimPrevista) {
        this.idEtapa = idEtapa;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
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

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        this.idEtapa = idEtapa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

    @Override
    public String toString() {
        return "Etapa [idEtapa=" + idEtapa + ", descricao=" + descricao + ", status=" + status
                + ", dataInicio=" + dataInicio + ",dataFimPrevista=" + dataFimPrevista + "]";
    }

}
