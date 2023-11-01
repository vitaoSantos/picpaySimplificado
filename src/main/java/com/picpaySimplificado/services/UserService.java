package com.picpaySimplificado.services;


import com.picpaySimplificado.domain.user.User;
import com.picpaySimplificado.domain.user.UserType;
import com.picpaySimplificado.dtos.UserDTO;
import com.picpaySimplificado.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validadeTransaction(User sender, BigDecimal amount) throws Exception {
        if (sender.getUserType() == UserType.MERCHAN) {
            throw new Exception("Usuário do tipo logista não está autorizado a realizar transação");
        }

        if (sender.getBalance().compareTo(amount) < 0) {
            throw new Exception("Saldo insuficiente");
        }
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuário não encontrado"));
    }

    public User createUser(UserDTO data) {
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers() {
        return this.repository.findAll();
    }

    public void saveUser(User user) {
        this.repository.save(user);
    }
}
