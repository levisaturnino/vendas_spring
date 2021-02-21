package br.com.levisaturnino.rest.controllers;

import br.com.levisaturnino.model.entity.Client;
import br.com.levisaturnino.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("api/clients")
public class ClientControllerOld {

   /* @Autowired
    private ClientRepository repository;

    public ClientControllerOld(ClientRepository repository) {
        this.repository = repository;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public ResponseEntity getClientById( @PathVariable Integer id ){
       Optional<Client>  client = repository.findById(id);
       if(client.isPresent()){
           return ResponseEntity.ok(client.get());
       }
       return ResponseEntity.notFound().build();
    }

    @PostMapping
    @ResponseBody
    public ResponseEntity save( @RequestBody Client cli ){
        Client  client = repository.save(cli);
        return ResponseEntity.ok(client);
    }

    @DeleteMapping("/{id}")
    @ResponseBody
    public ResponseEntity delete( @PathVariable Integer id ){
        Optional<Client>  client = repository.findById(id);
        if(client.isPresent()){
            repository.delete(client.get());
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/{id}")
    @ResponseBody
    public ResponseEntity update( @PathVariable Integer id, @RequestBody Client cli ){
        Optional<Client>  client = repository.findById(id);
        if(client.isPresent()){
            cli.setId(client.get().getId());
            repository.save(cli);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }


    @GetMapping
    @ResponseBody
    public ResponseEntity find( Client filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase()
                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<Client> list = repository.findAll(example);

        return ResponseEntity.ok(list);
    }

    @PutMapping("/update/{id}")
    @ResponseBody
    public ResponseEntity update2( @PathVariable Integer id, @RequestBody Client cli ){
       return repository
               .findById(id)
               .map( client ->{
                       cli.setId(client.getId());
                       repository.save(cli);
                       return ResponseEntity.noContent().build();
                }).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @RequestMapping(value = "/hello/{name}",
            method = RequestMethod.POST,
            consumes = {"application/json","application/xml"},
            produces = {"application/json","application/xml"}
    )
    @ResponseBody
    public String helloClient(@PathVariable("name") String nameClient, @RequestBody Client client){
        return String.format("Hello %s ",nameClient);
    }*/
}
