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

        Double predictedRating = slopeOne.getPredictedRating(4L, 101L);
        Assert.assertNotNull(predictedRating);
    }
}