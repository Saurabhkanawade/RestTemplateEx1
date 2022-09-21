package com.example.demo.service;

import com.example.demo.model.PostRequest;
import com.example.demo.model.PostResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Collections;

@Service
public class PostService {


    private final RestTemplate restTemplate;

    @Value("${test.url}")
    private String url;


    @Autowired
    public PostService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public PostResponse createPosts(PostRequest postRequest) {
        HttpEntity httpEntity = new HttpEntity<>(postRequest);
        ResponseEntity<PostResponse> postResponse = restTemplate.exchange(url + "/posts", HttpMethod.POST, httpEntity,
                PostResponse.class);
        return postResponse.getBody();
    }


    public PostResponse[] getPosts() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity = new HttpEntity(httpHeaders);
        ResponseEntity<PostResponse[]> posts = restTemplate.exchange(url + "/posts", HttpMethod.GET, httpEntity,
                PostResponse[].class);
        return posts.getBody();
    }

    public PostResponse getPostByID(Long id) {
        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity httpEntity=new HttpEntity<>(httpHeaders);
        ResponseEntity<PostResponse> postResponse= restTemplate.exchange(url+"/posts/"+id,HttpMethod.GET,httpEntity,
                PostResponse.class);
         return postResponse.getBody();
    }

    public void deletePosts(Long id) {

        HttpHeaders httpHeaders=new HttpHeaders();
        httpHeaders.setAccept(Collections.singletonList(MediaType.APPLICATION_JSON));
        HttpEntity entity=new HttpEntity(httpHeaders);
         restTemplate.exchange(url+"/posts/"+id,HttpMethod.DELETE,entity,PostResponse.class).getBody();

    }
    public PostResponse updatePosts(Long id, PostRequest postRequest) {
        HttpEntity httpEntity=new HttpEntity<>(postRequest);
        ResponseEntity<PostResponse> postResponse= restTemplate.exchange(url+"/posts/"+id,HttpMethod.PUT,httpEntity,
                PostResponse.class);
        return postResponse.getBody();
    }
}
























