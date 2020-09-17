package br.com.fiap.coolshoes.service;

import br.com.fiap.coolshoes.dto.ProductCreateUpdateDTO;
import br.com.fiap.coolshoes.dto.ProductDTO;
import br.com.fiap.coolshoes.dto.ProductPriceUpdateDTO;
import br.com.fiap.coolshoes.entity.Product;
import br.com.fiap.coolshoes.repository.ProductRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDTO> findAll(Integer number) {
        return productRepository.findAllByActiveIsTrue()
                .stream()
                .map(product -> new ProductDTO(product))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDTO findById(Long id) {
        Product product = productRepository.findFirstByIdAndActiveIsTrue(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        return new ProductDTO(product);
    }

    @Override
    public ProductDTO create(ProductCreateUpdateDTO productCreateUpdateDTO) {
        Product product = new Product();

        product.setPrice(productCreateUpdateDTO.getPrice());
        product.setNumber(productCreateUpdateDTO.getNumber());
        product.setModel(productCreateUpdateDTO.getModel());
        product.setColor(productCreateUpdateDTO.getColor());
        product.setActive(true);

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(savedProduct);
    }

    @Override
    public ProductDTO update(Long id, ProductCreateUpdateDTO productCreateUpdateDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        product.setPrice(productCreateUpdateDTO.getPrice());
        product.setNumber(productCreateUpdateDTO.getNumber());
        product.setModel(productCreateUpdateDTO.getModel());
        product.setColor(productCreateUpdateDTO.getColor());
        product.setActive(true);

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(savedProduct);
    }

    @Override
    public ProductDTO updatePrice(Long id, ProductPriceUpdateDTO productPriceUpdateDTO) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        product.setPrice(productPriceUpdateDTO.getPrice());

        Product savedProduct = productRepository.save(product);

        return new ProductDTO(savedProduct);
    }

    @Override
    public void delete(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));

        product.setActive(false);
        productRepository.save(product);
    }

}
