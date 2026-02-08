package com.ra12.projecte1.dto;

public class restaurantRequestDTO {
    
    private String name;
    private Double rating;
    private Boolean isVerified;
    private Boolean senseGluten;
    private Boolean senseLactosa;
    private Boolean senseFruitsSecs;
    private Boolean senseMarisc;
    private Boolean esVega;
    private Boolean esHalal;
    private Boolean esKosher;
    private Boolean teOu;
    private String tipusCuina;
    private String rangPreu;
    private String ubicacio;
    private Integer telefon;
    
    // Constructor vacio
    public restaurantRequestDTO() {}
    
    // Constructor con todos los parametros
    public restaurantRequestDTO(String name, Double rating, Boolean isVerified, 
                               Boolean senseGluten, Boolean senseLactosa, 
                               Boolean senseFruitsSecs, Boolean senseMarisc, 
                               Boolean esVega, Boolean esHalal, Boolean esKosher, 
                               Boolean teOu, String tipusCuina, String rangPreu, 
                               String ubicacio, Integer telefon) {
        this.name = name;
        this.rating = rating;
        this.isVerified = isVerified;
        this.senseGluten = senseGluten;
        this.senseLactosa = senseLactosa;
        this.senseFruitsSecs = senseFruitsSecs;
        this.senseMarisc = senseMarisc;
        this.esVega = esVega;
        this.esHalal = esHalal;
        this.esKosher = esKosher;
        this.teOu = teOu;
        this.tipusCuina = tipusCuina;
        this.rangPreu = rangPreu;
        this.ubicacio = ubicacio;
        this.telefon = telefon;
    }
    
    // Getters y Setters
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    
    public Double getRating() { return rating; }
    public void setRating(Double rating) { this.rating = rating; }
    
    public Boolean getIsVerified() { return isVerified; }
    public void setIsVerified(Boolean isVerified) { this.isVerified = isVerified; }
    
    public Boolean getSenseGluten() { return senseGluten; }
    public void setSenseGluten(Boolean senseGluten) { this.senseGluten = senseGluten; }
    
    public Boolean getSenseLactosa() { return senseLactosa; }
    public void setSenseLactosa(Boolean senseLactosa) { this.senseLactosa = senseLactosa; }
    
    public Boolean getSenseFruitsSecs() { return senseFruitsSecs; }
    public void setSenseFruitsSecs(Boolean senseFruitsSecs) { this.senseFruitsSecs = senseFruitsSecs; }
    
    public Boolean getSenseMarisc() { return senseMarisc; }
    public void setSenseMarisc(Boolean senseMarisc) { this.senseMarisc = senseMarisc; }
    
    public Boolean getEsVega() { return esVega; }
    public void setEsVega(Boolean esVega) { this.esVega = esVega; }
    
    public Boolean getEsHalal() { return esHalal; }
    public void setEsHalal(Boolean esHalal) { this.esHalal = esHalal; }
    
    public Boolean getEsKosher() { return esKosher; }
    public void setEsKosher(Boolean esKosher) { this.esKosher = esKosher; }
    
    public Boolean getTeOu() { return teOu; }
    public void setTeOu(Boolean teOu) { this.teOu = teOu; }
    
    public String getTipusCuina() { return tipusCuina; }
    public void setTipusCuina(String tipusCuina) { this.tipusCuina = tipusCuina; }
    
    public String getRangPreu() { return rangPreu; }
    public void setRangPreu(String rangPreu) { this.rangPreu = rangPreu; }
    
    public String getUbicacio() { return ubicacio; }
    public void setUbicacio(String ubicacio) { this.ubicacio = ubicacio; }
    
    public Integer getTelefon() { return telefon; }
    public void setTelefon(Integer telefon) { this.telefon = telefon; }
    
    @Override
    public String toString() {
        return "restaurantRequestDTO [name=" + name + ", rating=" + rating + ", isVerified=" + isVerified
                + ", senseGluten=" + senseGluten + ", senseLactosa=" + senseLactosa + ", senseFruitsSecs="
                + senseFruitsSecs + ", senseMarisc=" + senseMarisc + ", esVega=" + esVega + ", esHalal=" + esHalal
                + ", esKosher=" + esKosher + ", teOu=" + teOu + ", tipusCuina=" + tipusCuina + ", rangPreu=" + rangPreu
                + ", ubicacio=" + ubicacio + ", telefon=" + telefon + "]";
    }
}