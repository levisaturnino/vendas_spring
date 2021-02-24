package br.com.levisaturnino.service.impl;

import br.com.levisaturnino.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserDetailsService {

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserRepository userRepository;

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
