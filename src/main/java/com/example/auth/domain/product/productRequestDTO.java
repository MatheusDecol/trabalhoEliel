package com.example.auth.domain.product;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class ProductRequestDTO {

        @NotBlank
        private String name;

        @NotNull
        private Integer price;

        public ProductRequestDTO() {}

        public ProductRequestDTO(String name, Integer price) {
                this.name = name;
                this.price = price;
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
