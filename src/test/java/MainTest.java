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
        Main.setData();
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
    public void testGetNearestNeighbours() {
        RecommendationClient recommendationClient = new RecommendationClient(new Pearson());
        List<List<Double>> neighbours = recommendationClient.getNearestNeighbours(3, 7, 0.01);
        System.out.println(neighbours);

        List<List<Double>> nearestNeighboursCosine = new RecommendationClient(new Cosine()).getNearestNeighbours(3, 7, 0.35);
        System.out.println(nearestNeighboursCosine);
        List<List<Double>> nearestNeighboursEuclidean = new RecommendationClient(new Euclidean()).getNearestNeighbours(3, 7, 0.35);
        System.out.println(nearestNeighboursEuclidean);
    }

    @Test
    public void testGetPredictedRating() {
        RecommendationClient recommendationClient = new RecommendationClient(new Pearson());
        Double predictedRating101 = recommendationClient.getPredictedRating(7L, 101L);
        Double predictedRating103 = recommendationClient.getPredictedRating(7L, 103L);
        Double predictedRating106 = recommendationClient.getPredictedRating(7L, 106L);

        int sizeBefore = Main.getData().get(7L).size();
        Main.addData(7L, 106L, 2.8);
        Assert.assertTrue(Main.getData().get(7L).size() == sizeBefore + 1);

        Double predictedRating101New = recommendationClient.getPredictedRating(7L, 101L);
        Double predictedRating103New = recommendationClient.getPredictedRating(7L, 103L);

        Assert.assertNotSame(predictedRating101New, predictedRating101);
        Assert.assertNotSame(predictedRating103New, predictedRating103);
    }
}