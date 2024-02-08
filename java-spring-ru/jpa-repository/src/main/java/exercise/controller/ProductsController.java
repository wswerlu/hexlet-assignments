package exercise.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.data.domain.Sort;

import java.util.List;

import exercise.model.Product;
import exercise.repository.ProductRepository;
import exercise.exception.ResourceNotFoundException;

@RestController
@RequestMapping("/products")
public class ProductsController {

    @Autowired
    private ProductRepository productRepository;

    // BEGIN
    @GetMapping(path = "")
    public List<Product> index(@RequestParam(defaultValue = "-1") int min, @RequestParam(defaultValue = "-1") int max) {
        if (min != -1 && max == -1) {
            return productRepository.findByPriceGreaterThanOrderByPrice(min);
        } else if (min == -1 && max != -1) {
            return productRepository.findByPriceLessThanOrderByPrice(max);
        } else if (min != -1 && max != -1) {
            return productRepository.findByPriceBetweenOrderByPrice(min, max);
        }
        return productRepository.findAll(Sort.by("price"));
    }
    // END

    @GetMapping(path = "/{id}")
    public Product show(@PathVariable long id) {

        var product =  productRepository.findById(id)
            .orElseThrow(() -> new ResourceNotFoundException("Product with id " + id + " not found"));

        return product;
    }
}
