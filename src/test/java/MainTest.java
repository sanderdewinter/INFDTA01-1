import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class MainTest {

    private Main main;

    @Before
    public void setup() {
        this.main = new Main();
    }

    @Test
    public void assignment1() throws IOException {
        Map<Long, List<UserPreference>> data = main.getData();

        Assert.assertNotNull(data);
        Assert.assertTrue(data.size() == 7);
    }
}