
package model;

/**
 *
 * @author David
 */
public class UsoMaterial {
    private int idUsoMaterial;
    private Material material;
    private Etapa etapa;
    private double quantidade;
    
    public UsoMaterial(int idUsoMaterial, Material material, Etapa etapa, double quantidade) {
    this.idUsoMaterial = idUsoMaterial;
    this.material = material;
    this.etapa = etapa;
    this.quantidade = quantidade;
    }

    public int getIdUsoMaterial() {
        return idUsoMaterial;
    }

    public void setIdUsoMaterial(int idUsoMaterial) {
        this.idUsoMaterial = idUsoMaterial;
    }

    public Material getMaterial() {
        return material;
    }

    public void setMaterial(Material material) {
        this.material = material;
    }

    public Etapa getEtapa() {
        return etapa;
    }

    public void setEtapa(Etapa etapa) {
        this.etapa = etapa;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        this.quantidade = quantidade;
    }
    
}
