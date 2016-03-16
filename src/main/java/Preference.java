import java.time.Instant;

public class Preference {
    private Article article;
    private Movie movie;
    private double rating;
    private Instant timestamp;

    public Preference(Article article, double rating) {
        this.article = article;
        this.rating = rating;
    }

    public Preference(Movie movie, double rating, Instant timestamp) {
        this.movie = movie;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public Movie getMovie() {
        return movie;
    }

    public void setMovie(Movie movie) {
        this.movie = movie;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Article getArticle() {
        return article;
    }

    public void setArticle(Article article) {
        this.article = article;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
