package br.com.levisaturnino.rest.controllers;

import br.com.levisaturnino.exception.PasswordInvalidException;
import br.com.levisaturnino.model.entity.User;
import br.com.levisaturnino.rest.dto.CredenciaisDTO;
import br.com.levisaturnino.rest.dto.TokenDTO;
import br.com.levisaturnino.security.jwt.JwtService;
import br.com.levisaturnino.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

@RestController
@RequestMapping("api/users")
public class UserController {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserServiceImpl userService;

    @Autowired
    private JwtService jwtService;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public User save( @RequestBody User user ){
        return userService.save(user);
    }

    @PostMapping("{auth}")
    public TokenDTO autenticar(@RequestBody CredenciaisDTO credenciaisDTO){

        try{
            User user = User
                    .builder()
                    .login(credenciaisDTO.getLogin())
                    .password(credenciaisDTO.getPassword()).build();
            userService.autenticar(user);
         String token =    jwtService.gerarToken(user);

         return new TokenDTO(user.getLogin(),token);

        }catch (UsernameNotFoundException | PasswordInvalidException ex){
            throw new ResponseStatusException(HttpStatus.UNAUTHORIZED,ex.getMessage());
        }
    }
}
