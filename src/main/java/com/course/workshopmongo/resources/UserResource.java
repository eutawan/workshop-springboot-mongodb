package com.course.workshopmongo.resources;

import com.course.workshopmongo.domain.Post;
import com.course.workshopmongo.domain.User;
import com.course.workshopmongo.dto.UserDTO;
import com.course.workshopmongo.services.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;
import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

@Tag(name = "Usuários", description = "Endpoints relacionados a operações com usuários")
@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    private UserService service;

    @Operation(summary = "Listar todos os usuários", description = "Retorna uma lista com todos os usuários cadastrados")
    @GetMapping
    public ResponseEntity<List<UserDTO>> findAll() {
        List<User> list = service.findAll();
        List<UserDTO> listDto = list.stream().map(x -> new UserDTO(x)).collect(Collectors.toList());
        return ResponseEntity.ok().body(listDto);
    }

    @Operation(summary = "buscar usuário por ID", description = "retorna apenas um usuário encontrado pelo ID")
    @GetMapping("/{id}")
    public ResponseEntity<UserDTO> findById(@PathVariable String id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(new UserDTO(obj));
    }

    @Operation(summary = "Inserir usuário", description = "Inserir um novo usuário no banco")
    @PostMapping
    public ResponseEntity<Void> insert(@RequestBody UserDTO objDto) {
        User obj = service.fromDTO(objDto);
        obj = service.insert(obj);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
        return ResponseEntity.created(uri).build();

    }

    @Operation(summary = "Deletar usuário", description = "Deletar usuário pelo ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable String id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Atualizar usuário", description = "Atualizar um usuário no banco de dados")
    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@RequestBody UserDTO objDto, @PathVariable String id) {
        User obj = service.fromDTO(objDto);
        obj.setId(id);
        obj = service.update(obj);
        return ResponseEntity.noContent().build();
    }

    @Operation(summary = "Buscar posts", description = "Buscar posts de um usuário por ID")
    @GetMapping("/{id}/posts")
    public ResponseEntity<List<Post>> findPosts(@PathVariable String id) {
        User obj = service.findById(id);
        return ResponseEntity.ok().body(obj.getPosts());
    }


}
