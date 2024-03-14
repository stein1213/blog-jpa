package com.estsoft.blogjpa.controller;

import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.external.ExampleAPIClient;
import com.estsoft.blogjpa.model.Article;
import com.estsoft.blogjpa.service.BlogService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class JsonParseTestController {
    private final BlogService blogService;
    private final ExampleAPIClient apiClient;

    public JsonParseTestController(BlogService blogService, ExampleAPIClient apiClient) {
        this.blogService = blogService;
        this.apiClient = apiClient;
    }

    @PostMapping("/api/test")
    public ResponseEntity<List<Article>> test() {
        List<Article> articleList = blogService.test(apiClient.fetchDataFromApi());
        return ResponseEntity.status(HttpStatus.CREATED).body(articleList);
    }
}
