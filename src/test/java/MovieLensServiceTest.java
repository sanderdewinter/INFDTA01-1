import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

public class MovieLensServiceTest {

    private List<Movie> movies;
    private List<User> users;

    public Map<Long, Map<Long, Preference>> data;

    @Before
    public void setup() throws IOException, ParseException {
        MovieLensService.loadMovies();
        MovieLensService.loadUsers();
        MovieLensService.loadUserPreference();
        this.movies = MovieLensService.movies;
        this.users = MovieLensService.users;
        this.data = MovieLensService.data;
    }

    @Test
    public void testDataNotNull() {
        Assert.assertNotNull(movies);
        Assert.assertNotNull(users);
        Assert.assertNotNull(data);
    }

    @Test
    public void testTopRecommendations() {
        RecommendationClient recommendationClient = new RecommendationClient(MovieLensService.data, new Pearson());
        List<List<Double>> topRecommendations = recommendationClient.getTopRecommendations(8, 186L);
        System.out.println(topRecommendations);
    }

    @Test
    public void testTopRecommendationsFixed() {
        RecommendationClient recommendationClient = new RecommendationClient(MovieLensService.data, new Pearson());
        List<List<Double>> topRecommendations = recommendationClient.getTopRecommendations(8, 186L, 3);
        System.out.println(topRecommendations);
    }
}