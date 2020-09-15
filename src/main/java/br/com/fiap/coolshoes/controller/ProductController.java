package br.com.fiap.coolshoes.controller;

import br.com.fiap.coolshoes.dto.ProductCreateUpdateDTO;
import br.com.fiap.coolshoes.dto.ProductDTO;
import br.com.fiap.coolshoes.dto.ProductPriceUpdateDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("products")
public class ProductController {

    private List<ProductDTO> productDTOS = new ArrayList<ProductDTO>();

    public ProductController() {

        ProductDTO productDTO = new ProductDTO();
        productDTO.setId(1L);
        productDTO.setColor("Black");
        productDTO.setModel("Air Jordans");
        productDTO.setNumber(42);
        productDTO.setPrice(BigDecimal.valueOf(242.50));

        productDTOS.add(productDTO);

        ProductDTO productDTO1 = new ProductDTO();
        productDTO1.setId(2L);
        productDTO1.setColor("White");
        productDTO1.setModel("Air Jordans");
        productDTO1.setNumber(40);
        productDTO1.setPrice(BigDecimal.valueOf(200.50));

        productDTOS.add(productDTO1);

    }

    @GetMapping
    public List<ProductDTO> listAll(@RequestParam(required = false) Integer number) {
        return productDTOS
                .stream()
                .filter(product -> number == null || number.equals(product.getNumber()))
                .collect(Collectors.toList());
    }

    @GetMapping("{id}")
    public ProductDTO findById(@PathVariable Long id) {
        return productDTOS
                .stream()
                .filter(product -> id.equals(product.getId()))
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Produto não encontrado"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ProductDTO save(@RequestBody ProductCreateUpdateDTO productCreateUpdateDTO) {
        ProductDTO productDTO = new ProductDTO();

        productDTO.setId(productDTOS.size() + 1L);
        productDTO.setPrice(productCreateUpdateDTO.getPrice());
        productDTO.setNumber(productCreateUpdateDTO.getNumber());
        productDTO.setModel(productCreateUpdateDTO.getModel());
        productDTO.setColor(productCreateUpdateDTO.getColor());

        productDTOS.add(productDTO);
        return productDTO;
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO update(@PathVariable Long id, @RequestBody ProductCreateUpdateDTO productCreateUpdateDTO) {
        ProductDTO productDTO = findById(id);

        productDTO.setPrice(productCreateUpdateDTO.getPrice());
        productDTO.setNumber(productCreateUpdateDTO.getNumber());
        productDTO.setModel(productCreateUpdateDTO.getModel());
        productDTO.setColor(productCreateUpdateDTO.getColor());

        return productDTO;
    }

    @PatchMapping("{id}/price")
    @ResponseStatus(HttpStatus.OK)
    public ProductDTO updatePrice(@PathVariable Long id, @RequestBody ProductPriceUpdateDTO productPriceUpdateDTO) {
        ProductDTO productDTO = findById(id);

        productDTO.setPrice(productPriceUpdateDTO.getPrice());

        return productDTO;
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        ProductDTO productDTO = findById(id);
        productDTOS.remove(productDTO);
    }

}
