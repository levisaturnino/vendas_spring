package br.com.levisaturnino.model.repository;

import br.com.levisaturnino.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository  extends JpaRepository<User,Integer> {

    Optional<User> findByLogin(String username);
}
