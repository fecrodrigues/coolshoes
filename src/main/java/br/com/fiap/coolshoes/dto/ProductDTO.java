package br.com.fiap.coolshoes.dto;

import br.com.fiap.coolshoes.entity.Product;

import java.math.BigDecimal;

public class ProductDTO {

    private Long id;
    private String model;
    private Integer number;
    private String color;
    private BigDecimal price;

    public ProductDTO() { }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.color = product.getColor();
        this.model = product.getModel();
        this.number = product.getNumber();
        this.price = product.getPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
