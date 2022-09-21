package com.example.demo.controller;

import com.example.demo.model.PostRequest;
import com.example.demo.model.PostResponse;
import com.example.demo.service.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class PostController {

    private final PostService postService;

    @Autowired
    public PostController(PostService postService) {
        this.postService = postService;
    }


    @PostMapping(path = "/posts", consumes = MediaType.APPLICATION_JSON_VALUE, produces =
            MediaType.APPLICATION_JSON_VALUE)
    public PostResponse createPosts(@RequestBody PostRequest postRequest) {
        return postService.createPosts(postRequest);
    }

    @GetMapping(path = "/posts", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse[]> getPosts() {
        return new ResponseEntity<>(postService.getPosts(), HttpStatus.OK);
    }

    @GetMapping(path = "/posts/{id}",produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> getPosts(@PathVariable Long id){
        return new ResponseEntity<>(postService.getPostByID(id),HttpStatus.OK);
    }

    @PutMapping(path = "/posts/{id}",produces = MediaType.APPLICATION_JSON_VALUE,consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<PostResponse> updatePosts(@PathVariable Long id,@RequestBody PostRequest postRequest){
        return new ResponseEntity<>(postService.updatePosts(id,postRequest),HttpStatus.OK);
    }

    @DeleteMapping(path = "/posts/{id}")
    public ResponseEntity<Void> deletePosts(@PathVariable Long id){
        postService.deletePosts(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
