import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainTest {

    private Main main;

    @Before
    public void setup() {
        this.main = new Main();
    }

    @Test
    public void assignment1() throws IOException {
        Map<Long, Map<Long, Preference>> data = main.getData();

        Assert.assertNotNull(data);
    }

    @Test
    public void testEuclidean() throws IOException {
        Map<Long, Map<Long, Preference>> data = main.getData();

        ISimilarity euclidean = new Euclidean();

        double distance = euclidean.getDistance(
                data.get(1L),
                data.get(3L)
        );

        Assert.assertNotNull(distance);
    }
}