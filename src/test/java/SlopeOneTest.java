import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.util.List;
import java.util.Map;

public class SlopeOneTest {

    private Map<Long, Map<Long, Preference>> data;

    @Before
    public void setup() throws IOException {
        Main.setData();
        this.data = Main.data;
    }

    @Test
    public void testGetDeviation() {
        SlopeOne slopeOne = new SlopeOne(Main.data, Main.items);

        List<Double> deviation = slopeOne.getDeviation(101L, 102L);

        Assert.assertNotNull(deviation);
        Assert.assertTrue(deviation.size() == 2);
        Assert.assertEquals(-0.8, deviation.get(0));
    }

    @Test
    public void testGetAllDeviations() {
        SlopeOne slopeOne = new SlopeOne(Main.data, Main.items);

        Assert.assertNotNull(slopeOne.getDeviationMatrix());
    }

    @Test
    public void testGetPredictedRating() {
        SlopeOne slopeOne = new SlopeOne(Main.data, Main.items);

//        Double predictedRating = slopeOne.getPredictedRating(7L, 101L);
//        Double predictedRating2 = slopeOne.getPredictedRating(7L, 103L);
        Double predictedRating3 = slopeOne.getPredictedRating(7L, 106L);
        Assert.assertNotNull(predictedRating3);
    }

    @Test
    public void testGetTopPredictedRating() {
        SlopeOne slopeOne = new SlopeOne(Main.data, Main.items);

        List<List<Double>> topPredictions = slopeOne.getTopPredictions(4L, 5);
        Assert.assertNotNull(topPredictions);
    }

    @Test
    public void testUpdateDeviationMatrix() {
        SlopeOne slopeOne = new SlopeOne(Main.data, Main.items);
        Map<List<Long>, List<Double>> deviationMatrix = slopeOne.getDeviationMatrix();

        Main.addData(3L, 105L, 4.0);
        slopeOne.updateDeviationMatrix(105L);

        Assert.assertNotSame(deviationMatrix, slopeOne.getDeviationMatrix());
    }
}