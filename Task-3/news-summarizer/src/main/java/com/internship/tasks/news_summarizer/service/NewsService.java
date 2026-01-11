package com.internship.tasks.news_summarizer.service;

import com.internship.tasks.news_summarizer.model.Article;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NewsService {

    private static final String API_KEY = "eb2f5595e96742ed903d3fc04d3926bc";

    // URL for specific categories (Tech, Business, Sports)
    private static final String HEADLINES_URL = "https://newsapi.org/v2/top-headlines?country=us&category=%s&apiKey=" + API_KEY;

    // URL for searching any topic (Bitcoin, NASA, etc)
    private static final String SEARCH_URL = "https://newsapi.org/v2/everything?q=%s&apiKey=" + API_KEY;

    public List<Article> fetchArticles(String category) {
        String finalUrl;

        // Logic: Is this a standard category or a custom search?
        if (category.equals("technology") || category.equals("business") || category.equals("sports")) {
            System.out.println("Fetching Headlines for: " + category);
            finalUrl = String.format(HEADLINES_URL, category);
        } else {
            System.out.println("Searching for topic: " + category);
            finalUrl = String.format(SEARCH_URL, category);
        }

        RestTemplate restTemplate = new RestTemplate();
        Map<String, Object> response = restTemplate.getForObject(finalUrl, Map.class);

        List<Map<String, Object>> articlesData = (List<Map<String, Object>>) response.get("articles");
        List<Article> summarizedArticles = new ArrayList<>();

        if (articlesData != null) {
            for (Map<String, Object> data : articlesData) {
                String title = (String) data.get("title");
                String description = (String) data.get("description");
                String url = (String) data.get("url");
                String imageUrl = (String) data.get("urlToImage");

                if (description == null || description.isEmpty()) description = title;

                // Summarize
                String shortSummary = summarizeText(description);

                summarizedArticles.add(new Article(title, shortSummary, url, imageUrl));
            }
        }
        return summarizedArticles;
    }

    private String summarizeText(String text) {
        if (text == null) return "";
        String[] sentences = text.split("\\.");
        if (sentences.length <= 1) return text;
        return sentences[0] + ". " + sentences[1] + ".";
    }
}