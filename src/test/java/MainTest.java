import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class MainTest {

    private Map<Long, Map<Long, Preference>> data;

    @Before
    public void setup() throws IOException {
        this.data = Main.getData();
    }

    @Test
    public void assignment1() {
        Assert.assertNotNull(data);
    }

    @Test
    public void testEuclidean() {
        ISimilarity euclidean = new Euclidean();

        double distance = euclidean.getSimilarity(
                data.get(1L),
                data.get(2L)
        );

        Assert.assertNotNull(distance);
    }

    @Test
    public void testPearson() {
        ISimilarity pearson = new Pearson();

        double distance = pearson.getSimilarity(
                data.get(1L),
                data.get(5L)
        );

        Assert.assertNotNull(distance);
    }

    @Test
    public void testCosine() {
        ISimilarity cosine = new Cosine();

        double distance = cosine.getSimilarity(
                data.get(3L),
                data.get(1L)
        );

        Assert.assertNotNull(distance);
    }

    @Test
    public void testRecommendationClient() throws IOException {
        RecommendationClient recommendationClient = new RecommendationClient(new Cosine());
        List<List<Double>> neighbours = recommendationClient.getNearestNeighbours(3, 7, 0.35);
        System.out.println(neighbours);
    }
}