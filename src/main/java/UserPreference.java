public class UserPreference {
    private User user;
    private Article article;
    private double rating;

    public UserPreference(User user, Article article, double rating) {
        this.user = user;
        this.article = article;
        this.rating = rating;
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

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
