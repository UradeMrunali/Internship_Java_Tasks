package com.internship.tasks.news_summarizer.model;

public class Article {
    private String title;
    private String summary; 
    private String originalUrl;
    private String imageUrl;

  
    public Article(String title, String summary, String originalUrl, String imageUrl) {
        this.title = title;
        this.summary = summary;
        this.originalUrl = originalUrl;
        this.imageUrl = imageUrl;
    }

    
    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getSummary() { return summary; }
    public void setSummary(String summary) { this.summary = summary; }

    public String getOriginalUrl() { return originalUrl; }
    public void setOriginalUrl(String originalUrl) { this.originalUrl = originalUrl; }

    public String getImageUrl() { return imageUrl; }
    public void setImageUrl(String imageUrl) { this.imageUrl = imageUrl; }
}
