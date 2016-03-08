import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

public class MovieLensServiceTest {

    private List<Movie> movies;

    @Before
    public void setup() throws IOException, ParseException {
        MovieLensService.loadMovies();
        this.movies = MovieLensService.movies;
    }

    @Test
    public void testDataNotNull() {
        Assert.assertNotNull(movies);
    }
}