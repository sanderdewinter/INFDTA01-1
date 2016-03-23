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
        this.data = Main.data;
    }

    @Test
    public void assignment1() {
        Assert.assertNotNull(data);
    }

    @Test
    public void testEuclidean() {
        ISimilarity euclidean = new Euclidean();

        double distance = euclidean.getSimilarity(
                data.get(3L),
                data.get(7L)
        );

        Assert.assertNotNull(distance);
    }

    @Test
    public void testPearson() {
        ISimilarity pearson = new Pearson();

        double similarity = pearson.getSimilarity(
                data.get(3L),
                data.get(4L)
        );

        Assert.assertNotNull(similarity);
    }

    @Test
    public void testCosine() {
        ISimilarity cosine = new Cosine();

        double similarity = cosine.getSimilarity(
                data.get(3L),
                data.get(7L)
        );

        Assert.assertNotNull(similarity);
    }

    @Test
    public void testGetNearestNeighbours() {
        RecommendationClient recommendationClient = new RecommendationClient(this.data, new Pearson());
        List<List<Double>> neighbours = recommendationClient.getNearestNeighbours(3, 7, 0.35);
        System.out.println(neighbours);

        List<List<Double>> nearestNeighboursCosine = new RecommendationClient(this.data, new Cosine()).getNearestNeighbours(3, 7, 0.35);
        System.out.println(nearestNeighboursCosine);
        List<List<Double>> nearestNeighboursEuclidean = new RecommendationClient(this.data, new Euclidean()).getNearestNeighbours(3, 2, 0.35);
        System.out.println(nearestNeighboursEuclidean);
    }

    @Test
    public void testGetPredictedRating() {
        RecommendationClient recommendationClient = new RecommendationClient(this.data, new Pearson());
        List<List<Double>> nearestNeighbours = recommendationClient.getNearestNeighbours(3, 7L, 0.01);

        Double predictedRating101 = recommendationClient.getPredictedRating(101L, nearestNeighbours);
        Double predictedRating103 = recommendationClient.getPredictedRating(103L, nearestNeighbours);
        Double predictedRating106 = recommendationClient.getPredictedRating(106L, nearestNeighbours);

        List<List<Double>> nearestNeighbours4 = recommendationClient.getNearestNeighbours(3, 4L, 0.01);
        Double predictedRating101user4 = recommendationClient.getPredictedRating(101L, nearestNeighbours4);

        int sizeBefore = Main.data.get(7L).size();
        Main.addData(7L, 106L, 2.8);
        Assert.assertTrue(Main.data.get(7L).size() == sizeBefore + 1);

        Double predictedRating101New = recommendationClient.getPredictedRating(101L, nearestNeighbours);
        Double predictedRating103New = recommendationClient.getPredictedRating(103L, nearestNeighbours);

        Assert.assertNotSame(predictedRating101New, predictedRating101);
        Assert.assertNotSame(predictedRating103New, predictedRating103);
    }
}