package com.course.workshopmongo.resources;

import com.course.workshopmongo.domain.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "Usuários", description = "Endpoints relacionados a operações com usuários")
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados")
    @GetMapping
    public ResponseEntity<List<User>> findAll() {
        User maria = new User("1", "Maria Silva", "maria@gmail.com");
        User doanld = new User("2", "Doanld Alves", "doanld@gmail.com");
        List<User> list = new ArrayList<>();
        list.add(maria);
        list.add(doanld);

        return ResponseEntity.ok().body(list);
    }

}
