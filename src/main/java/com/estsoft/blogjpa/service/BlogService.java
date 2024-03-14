package com.estsoft.blogjpa.service;

import com.estsoft.blogjpa.dto.AddArticleRequest;
import com.estsoft.blogjpa.dto.ArticleResponse;
import com.estsoft.blogjpa.dto.UpdateArticleRequest;
import com.estsoft.blogjpa.model.Article;
import com.estsoft.blogjpa.repository.BlogRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class BlogService {
    private final BlogRepository blogRepository;

    public BlogService(BlogRepository blogRepository) {
        this.blogRepository = blogRepository;
    }

    public Article save(AddArticleRequest request) {
        return blogRepository.save(request.toEntity());
    }

    public List<Article> findAll() {
        return blogRepository.findAll();
    }

    public Article findById(Long id) {
        Optional<Article> optional = blogRepository.findById(id);
        optional.orElseThrow(() -> new IllegalArgumentException("not found id" + id));
        return optional.orElse(new Article());
    }

    public void delete(Long id) {
        blogRepository.deleteById(id);
    }

    @Transactional
    public Article update(Long id, UpdateArticleRequest request) {
        Article article = findById(id);
        article.update(request.getTitle(), request.getContent());
        return article;
    }

    @Transactional
    public Article updateTitle(Long id, UpdateArticleRequest request) {
        Article article = findById(id);
        blogRepository.updateTitle(id, request.getTitle());
        return article;
    }

    public List<Article> test(List<AddArticleRequest> requestList) {
        List<Article> articleList = new ArrayList<>();
        for (AddArticleRequest request : requestList) {
            articleList.add(request.toEntity());
        }

        return blogRepository.saveAll(articleList);
    }
}