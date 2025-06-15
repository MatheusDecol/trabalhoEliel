package com.example.auth.domain.product;

public class productResponseDTO {
    private String id;
    private String name;
    private Integer price;

    public productResponseDTO() {
    }

    public productResponseDTO(String id, String name, Integer price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }

    public productResponseDTO(Product product) {
        this(product.getId(), product.getName(), product.getPrice());
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }
}
