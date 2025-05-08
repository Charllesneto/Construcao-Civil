
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
    
}
