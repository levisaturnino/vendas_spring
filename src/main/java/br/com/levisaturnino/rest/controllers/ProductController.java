package br.com.levisaturnino.rest.controllers;

import br.com.levisaturnino.model.entity.Product;
import br.com.levisaturnino.model.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("api/products")
public class ProductController {

    @Autowired
    private ProductRepository repository;

    public ProductController(ProductRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    public Product getProductById( @PathVariable Integer id ){
       return  repository
               .findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Product save( @RequestBody Product product ){
        return repository.save(product);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){

        repository
                .findById(id)
                .map( Product ->{
                    repository.delete(Product);
                    return Product;
                })  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
    }

    @PutMapping("{id}")
    public void update( @PathVariable Integer id, @RequestBody Product cli ){
         repository
                .findById(id)
                .map( Product ->{
                    cli.setId(Product.getId());
                    repository.save(cli);
                    return Product;
                })  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Product not found"));
    }

    @GetMapping
    public  List<Product> find( Product filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase()
                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<Product> list = repository.findAll(example);

        return list;
    }


}
