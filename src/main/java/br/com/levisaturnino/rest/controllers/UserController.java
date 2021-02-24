package br.com.levisaturnino.rest.controllers;

import br.com.levisaturnino.model.entity.User;
import br.com.levisaturnino.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public UserController(UserRepository repository) {
        this.repository = repository;
    }

    @GetMapping("{id}")
    public User getUserById( @PathVariable Integer id ){
       return  repository
               .findById(id)
               .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save( @RequestBody @Valid User user ){

        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return

                repository.save(user);
    }

    @DeleteMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete( @PathVariable Integer id ){

        repository
                .findById(id)
                .map( User ->{
                    repository.delete(User);
                    return User;
                })  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    }

    @PutMapping("{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update( @PathVariable Integer id, @RequestBody @Valid User cli ){
         repository
                .findById(id)
                .map( User ->{
                    cli.setId(User.getId());
                    repository.save(cli);
                    return User;
                })  .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND,"User not found"));
    }

    @GetMapping
    public  List<User> find( User filtro ){
        ExampleMatcher matcher = ExampleMatcher
                                            .matching()
                                            .withIgnoreCase()
                                            .withStringMatcher(ExampleMatcher.StringMatcher.CONTAINING);

        Example example = Example.of(filtro, matcher);
        List<User> list = repository.findAll(example);

        return list;
    }
}
