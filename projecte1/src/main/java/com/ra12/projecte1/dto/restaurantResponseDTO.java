package com.ra12.projecte1.dto;

import java.time.LocalDateTime;

import com.ra12.projecte1.model.restaurantModel;

public class restaurantResponseDTO {
    
    private Long id;
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
    private LocalDateTime dataCreated;
    private LocalDateTime dataUpdated;
    
    // Constructor vacio
    public restaurantResponseDTO() {}
    
    // Constructor desde Model
    public restaurantResponseDTO(restaurantModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.rating = model.getRating();
        this.isVerified = model.getIsVerified();
        this.senseGluten = model.getSenseGluten();
        this.senseLactosa = model.getSenseLactosa();
        this.senseFruitsSecs = model.getSenseFruitsSecs();
        this.senseMarisc = model.getSenseMarisc();
        this.esVega = model.getEsVega();
        this.esHalal = model.getEsHalal();
        this.esKosher = model.getEsKosher();
        this.teOu = model.getTeOu();
        this.tipusCuina = model.getTipusCuina();
        this.rangPreu = model.getRangPreu();
        this.ubicacio = model.getUbicacio();
        this.telefon = model.getTelefon();
        this.dataCreated = model.getDataCreated();
        this.dataUpdated = model.getDataUpdated();
    }
    
    // Getters y Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    
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
    
    public LocalDateTime getDataCreated() { return dataCreated; }
    public void setDataCreated(LocalDateTime dataCreated) { this.dataCreated = dataCreated; }
    
    public LocalDateTime getDataUpdated() { return dataUpdated; }
    public void setDataUpdated(LocalDateTime dataUpdated) { this.dataUpdated = dataUpdated; }
    
    @Override
    public String toString() {
        return "restaurantResponseDTO [id=" + id + ", name=" + name + ", rating=" + rating + ", isVerified=" + isVerified
                + ", senseGluten=" + senseGluten + ", senseLactosa=" + senseLactosa + ", senseFruitsSecs="
                + senseFruitsSecs + ", senseMarisc=" + senseMarisc + ", esVega=" + esVega + ", esHalal=" + esHalal
                + ", esKosher=" + esKosher + ", teOu=" + teOu + ", tipusCuina=" + tipusCuina + ", rangPreu=" + rangPreu
                + ", ubicacio=" + ubicacio + ", telefon=" + telefon + ", dataCreated=" + dataCreated + ", dataUpdated="
                + dataUpdated + "]";
    }
}