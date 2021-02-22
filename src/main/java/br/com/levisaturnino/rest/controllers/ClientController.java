package br.com.levisaturnino.rest.controllers;

import br.com.levisaturnino.model.entity.Client;
import br.com.levisaturnino.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
@RestController
@RequestMapping("api/clients")
public class ClientController {

    @Autowired
    private ClientRepository repository;

    public ClientController(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    public Client getClientById( @PathVariable Integer id ){
       return  repository
               .findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Client save( @RequestBody @Valid Client cli ){
        return repository.save(cli);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){

        repository
                .findById(id)
                .map( client ->{
                    repository.delete(client);
                    return client;
                })  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found"));

        /*Optional<Client>  client = repository.findById(id);
        if(client.isPresent()){
            repository.delete(client.get());
        }*/
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody @Valid Client cli ){
         repository
                .findById(id)
                .map( client ->{
                    cli.setId(client.getId());
                    repository.save(cli);
                    return client;
                })  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"Client not found"));
    }

    @GetMapping
    public  List<Client> find( Client filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase()
                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<Client> list = repository.findAll(example);

        return list;
    }
}
