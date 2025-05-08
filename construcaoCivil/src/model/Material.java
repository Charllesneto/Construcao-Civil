 package model;
        
public class Material {
    
    private int idMaterial;
    private String nome;
    private String unidadeMedida;
    private double precoUnitario;

    public Material(int idMaterial, String nome, String unidadeMedida, double precoUnitario){
        
        this.idMaterial = idMaterial;
        this.nome = nome;
        this.precoUnitario = precoUnitario;
        this.unidadeMedida = unidadeMedida;
        }
        //Getters e setters
        
        public int getIdMaterial(){
            return idMaterial;
        }
        
        public String getNome(){
            return nome;
        }
        
        public String getUnidadeMedida(){
            return unidadeMedida;
           
        }
        
        public double getPrecoUnitario(){
            return precoUnitario;
        }

        public void setIdMaterial(int idMaterial) {
            this.idMaterial = idMaterial;
        }

        public void setNome(String nome) {
            this.nome = nome;
        }

        public void setUnidadeMedida(String unidadeMedida) {
            this.unidadeMedida = unidadeMedida;
        }

        public void setPrecoUnitario(double precoUnitario) {
            this.precoUnitario = precoUnitario;
        }

        @Override
        public String toString(){
            return "Material [idMaterial="+ idMaterial +",nome="+nome+",unidadeMedida="+unidadeMedida+",precoUnitario="+precoUnitario+"]";
        }
        
        
    }



    

