package com.example.auth.domain.product;

public class productResponseDTO {

    private String id;
    private String productName;
    private Integer productValue;


    public ProductResponseDTO() {}

    public ProductResponseDTO(String id, String productName, Integer productValue) {
        this.id = id;
        this.productName = productName;
        this.productValue = productValue;
    }

    public ProductResponseDTO(Product product) {
        this.id = product.getId();
        this.productName = product.getName();
        this.productValue = product.getPrice();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Integer getProductValue() {
        return productValue;
    }

    public void setProductValue(Integer productValue) {
        this.productValue = productValue;
    }
}
