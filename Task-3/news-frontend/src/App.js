import React, { useState, useEffect } from "react";
import axios from "axios";
import "./App.css";

function App() {
  const [articles, setArticles] = useState([]);
  const [loading, setLoading] = useState(false);
  const [topic, setTopic] = useState("technology");
  const [searchTerm, setSearchTerm] = useState("");

  const fetchNews = (query) => {
    setLoading(true);
    setTopic(query);

    axios.get(`http://localhost:8081/api/news/${query}`)
      .then((response) => {
        setArticles(response.data);
        setLoading(false);
      })
      .catch((error) => {
        console.error("Error:", error);
        setLoading(false);
      });
  };

  useEffect(() => {
    fetchNews("technology");
  }, []);

  const handleSearch = (e) => {
    e.preventDefault();
    if (searchTerm.trim()) {
      fetchNews(searchTerm);
    }
  };

  return (
    <div className="app-container">
      <header className="navbar">
        <h1>AI News Summarizer</h1>
      </header>

      {/* SEARCH BAR SECTION */}
      <div className="search-container">
        <form onSubmit={handleSearch}>
            <input
              type="text"
              placeholder="Search (e.g., Bitcoin, NASA, India)..."
              value={searchTerm}
              onChange={(e) => setSearchTerm(e.target.value)}
            />
            <button type="submit">Search</button>
        </form>
      </div>

      <div className="category-bar">
        <button className={topic==="technology"?"active":""} onClick={() => fetchNews("technology")}>Tech Headlines</button>
        <button className={topic==="business"?"active":""} onClick={() => fetchNews("business")}>Business Headlines</button>
        <button className={topic==="sports"?"active":""} onClick={() => fetchNews("sports")}>Sports Headlines</button>
      </div>

      <div className="content-area">
        <h2 className="topic-header">Results for: <span className="highlight">{topic}</span></h2>

        {loading ? (
          <div className="loading">AI is finding and summarizing news...</div>
        ) : (
          <div className="news-grid">
            {articles.length > 0 ? (
              articles.map((article, index) => (
                <div key={index} className="news-card">
                  {article.imageUrl && <img src={article.imageUrl} alt="News" />}
                  <div className="card-body">
                    <h3>{article.title}</h3>
                    <div className="summary-box">
                      <strong>AI Summary:</strong> {article.summary}
                    </div>
                    <a href={article.originalUrl} target="_blank" rel="noreferrer" className="read-btn">
                      Read Full Article
                    </a>
                  </div>
                </div>
              ))
            ) : (
              <p className="no-data">No news found for this topic.</p>
            )}
          </div>
        )}
      </div>
    </div>
  );
}

export default App;