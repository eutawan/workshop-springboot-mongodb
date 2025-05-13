package com.course.workshopmongo.resources;

import com.course.workshopmongo.domain.Post;
import com.course.workshopmongo.services.PostService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Tag(name = "Posts", description = "Endpoints relacionados a operações com os posts")
@RestController
@RequestMapping(value = "/posts")
public class PostResource {

    @Autowired
    private PostService service;

    @Operation(summary = "Listar todos os posts", description = "Retorna uma lista com todos os posts feitos")
    @GetMapping
    public ResponseEntity<List<Post>> findAll() {
        List<Post> list = service.findAll();
        return ResponseEntity.ok().body(list);
    }

}
