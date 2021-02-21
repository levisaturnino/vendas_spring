package br.com.levisaturnino.rest.controllers;

import br.com.levisaturnino.model.entity.Client;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("api/clients")
public class ClientController {

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
