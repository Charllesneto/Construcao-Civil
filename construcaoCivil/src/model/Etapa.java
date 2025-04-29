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
    private LocalDate dataInicio;
    private LocalDate dataFimPrevista;

    public Etapa(int idEtapa, String descricao, String status, LocalDate dataInicio, LocalDate dataFimPrevista) {
        this.idEtapa = idEtapa;
        this.descricao = descricao;
        this.status = status;
        this.dataInicio = dataInicio;
        this.dataFimPrevista = dataFimPrevista;
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

    public LocalDate getDataInicio() {
        return dataInicio;
    }

    public void setDataInicio(LocalDate dataInicio) {
        this.dataInicio = dataInicio;
    }

    public LocalDate getDataFimPrevista() {
        return dataFimPrevista;
    }

    public void setDataFimPrevista(LocalDate dataFimPrevista) {
        this.dataFimPrevista = dataFimPrevista;
    }

    @Override
    public String toString() {
        return "Etapa [idEtapa=" + idEtapa + ", descricao=" + descricao + ", status=" + status
                + ", dataInicio=" + dataInicio + ",dataFimPrevista=" + dataFimPrevista + "]";
    }

}
