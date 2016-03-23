import java.time.Instant;

public class Preference {
    private Item item;
    private double rating;
    private Instant timestamp;

    public Preference(Item item, double rating) {
        this.item = item;
        this.rating = rating;
    }

    public Preference(Item item, double rating, Instant timestamp) {
        this.item = item;
        this.rating = rating;
        this.timestamp = timestamp;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public Instant getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Instant timestamp) {
        this.timestamp = timestamp;
    }
}
