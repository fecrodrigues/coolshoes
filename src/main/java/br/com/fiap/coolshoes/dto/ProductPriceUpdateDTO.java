package br.com.fiap.coolshoes.dto;

import java.math.BigDecimal;

public class ProductPriceUpdateDTO {

    private BigDecimal price;

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }
}
