package model;

/**
 *
 * @author Nodival Neto
 */
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

}
