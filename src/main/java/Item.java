import java.util.*;

public class Item {
    private long id;
    private String title;
    private Date releaseDate;
    private Date videoReleaseDate;
    private String url;

    private Map<String, Boolean> genres;

    public Item(long id) {
        this.id = id;
    }

    public Item(long id, String title, Date releaseDate, Date videoReleaseDate, String url, List<Integer> genres) {
        this.id = id;
        this.title = title;
        this.releaseDate = releaseDate;
        this.videoReleaseDate = videoReleaseDate;
        this.url = url;

        this.genres = getGenreHashMap(genres);
    }

    private Map<String, Boolean> getGenreHashMap(List<Integer> genresInteger) {
        List<Boolean> genresList = new ArrayList<Boolean>();
        Map<String, Boolean> genres = new HashMap<String, Boolean>();

        for (Integer genre : genresInteger) {
            if (genre == 0) {
                genresList.add(false);
            } else {
                genresList.add(true);
            }
        }

        genres.put("unknown", genresList.get(0));
        genres.put("Action", genresList.get(1));
        genres.put("Adventure", genresList.get(2));
        genres.put("Animation", genresList.get(3));
        genres.put("Children's", genresList.get(4));
        genres.put("Comedy", genresList.get(5));
        genres.put("Crime", genresList.get(6));
        genres.put("Documentary", genresList.get(7));
        genres.put("Drama", genresList.get(8));
        genres.put("Fantasy", genresList.get(9));
        genres.put("Film-Noir", genresList.get(10));
        genres.put("Horror", genresList.get(11));
        genres.put("Musical", genresList.get(12));
        genres.put("Mystery", genresList.get(13));
        genres.put("Romance", genresList.get(14));
        genres.put("Sci-Fi", genresList.get(15));
        genres.put("Thriller", genresList.get(16));
        genres.put("War", genresList.get(17));
        genres.put("Western", genresList.get(18));

        return genres;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Map<String, Boolean> getGenres() {
        return genres;
    }

    public void setGenres(Map<String, Boolean> genres) {
        this.genres = genres;
    }

    public Date getVideoReleaseDate() {
        return videoReleaseDate;
    }

    public void setVideoReleaseDate(Date videoReleaseDate) {
        this.videoReleaseDate = videoReleaseDate;
    }

    @Override
    public boolean equals(Object object) {
        return object != null && object instanceof Item && this.id == ((Item) object).id;
    }
}
