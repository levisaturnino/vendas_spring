package br.com.levisaturnino.service.impl;


import br.com.levisaturnino.exception.PasswordInvalidException;
import br.com.levisaturnino.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public br.com.levisaturnino.model.entity.User save( br.com.levisaturnino.model.entity.User user ){
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public UserDetails autenticar(br.com.levisaturnino.model.entity.User user){
        UserDetails userDetails = loadUserByUsername(user.getLogin());
        boolean passwordBatem = passwordEncoder.matches(user.getPassword(),userDetails.getPassword());

        if(passwordBatem){
            return userDetails;
        }
        throw new PasswordInvalidException();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        br.com.levisaturnino.model.entity.User user = userRepository
                .findByLogin(username)
                .orElseThrow(() ->  new UsernameNotFoundException("Usuario não encontrado na base.")
        );

        String[] roles = user.isAdmin() ? new String[]{"USER","ADMIN"}:  new String[]{"USER"};

        return User.builder()
                .username(user.getLogin())
                .password(user.getPassword())
                .roles(roles)
                .build();
    }
}
