package com.course.workshopmongo.services;

import com.course.workshopmongo.domain.User;
import com.course.workshopmongo.repository.UserRepository;
import com.course.workshopmongo.services.exception.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository repository;

    public List<User> findAll() {
        return repository.findAll();
    }

    public User findById(String id) {
        return repository.findById(id).orElseThrow(() -> new ObjectNotFoundException("Usuário não encontrado"));

    }

}
