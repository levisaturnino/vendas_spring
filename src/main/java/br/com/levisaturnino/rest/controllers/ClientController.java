package br.com.levisaturnino.rest.controllers;

import br.com.levisaturnino.model.entity.Client;
import br.com.levisaturnino.model.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
@RequestMapping("api/clients")
public class ClientController {


    @Autowired
    private ClientRepository repository;

    public ClientController(ClientRepository repository) {
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

    @RequestMapping(value = "/hello/{name}",
            method = RequestMethod.POST,
            consumes = {"application/json","application/xml"},
            produces = {"application/json","application/xml"}
    )
    @ResponseBody
    public String helloClient(@PathVariable("name") String nameClient, @RequestBody Client client){
        return String.format("Hello %s ",nameClient);
    }
}
