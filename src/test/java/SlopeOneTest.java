import junit.framework.Assert;
import org.junit.BeforeClass;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SlopeOneTest {

    private static Map<Long, Map<Long, Preference>> data;
    private static List<Item> items;
    private static SlopeOne slopeOne;

    @BeforeClass
    public static void setup() throws IOException {
        Main.setData();
        data = Main.data;
        items = Main.items;

        slopeOne = new SlopeOne(data, items);
    }

    @Test
    public void testGetDeviation() {
        List<Double> deviation = slopeOne.getDeviation(101L, 102L);

        Assert.assertNotNull(deviation);
        Assert.assertTrue(deviation.size() == 2);
        Assert.assertEquals(-0.8, deviation.get(0));
    }

    @Test
    public void testGetAllDeviations() {
        Assert.assertNotNull(slopeOne.getDeviationMatrix());
    }

    @Test
    public void testGetPredictedRating() {
        Double predictedRating = slopeOne.getPredictedRating(7L, 101L);
        Double predictedRating2 = slopeOne.getPredictedRating(7L, 103L);
        Double predictedRating3 = slopeOne.getPredictedRating(7L, 106L);
        Assert.assertNotNull(predictedRating3);
    }

    @Test
    public void testGetTopPredictedRating() {
        List<List<Double>> topPredictions = slopeOne.getTopPredictions(4L, 5);
        Assert.assertNotNull(topPredictions);
    }

    @Test
    public void testUpdateDeviationMatrix() {
        Map<List<Long>, List<Double>> deviationMatrix = slopeOne.getDeviationMatrix();

        Main.addData(3L, 105L, 4.0);
        SlopeOne slopeOne2 = new SlopeOne(data, items);
        slopeOne2.updateDeviationMatrix(105L);

        Assert.assertNotSame(deviationMatrix, slopeOne2.getDeviationMatrix());
    }
}