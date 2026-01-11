package com.internship.tasks.news_summarizer.controller;

import com.internship.tasks.news_summarizer.model.Article;
import com.internship.tasks.news_summarizer.service.NewsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/news")
@CrossOrigin(origins = "http://localhost:3000")
public class NewsController {

    @Autowired
    private NewsService newsService;

    @GetMapping("/{category}")
    public List<Article> getNews(@PathVariable String category) {
        return newsService.fetchArticles(category);
    }
}
