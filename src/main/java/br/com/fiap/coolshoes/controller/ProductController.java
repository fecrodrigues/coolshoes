package br.com.fiap.coolshoes.controller;

import br.com.fiap.coolshoes.dto.ProductCreateUpdateDTO;
import br.com.fiap.coolshoes.dto.ProductDTO;
import br.com.fiap.coolshoes.dto.ProductPriceUpdateDTO;
import br.com.fiap.coolshoes.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    private final ProductService productService;

    private ProductController(ProductService productService) {
        this.productService = productService;
    }

    @GetMapping
    public List<ProductDTO> findAll(@RequestParam(required = false) Integer number) {
        return productService.findAll(number);
    }

    @GetMapping("{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productService.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO create(@RequestBody ProductCreateUpdateDTO productCreateUpdateDTO) {
        return productService.create(productCreateUpdateDTO);
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductCreateUpdateDTO productCreateUpdateDTO) {
        return productService.update(id, productCreateUpdateDTO);
    }

    @PatchMapping("{id}/price")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updatePrice(@PathVariable Long id, @RequestBody ProductPriceUpdateDTO productPriceUpdateDTO) {
        return productService.updatePrice(id, productPriceUpdateDTO);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        productService.delete(id);
    }

}
