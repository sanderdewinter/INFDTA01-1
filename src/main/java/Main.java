import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class Main {
    public Map<Long, Map<Long, Preference>> getData() throws IOException {
        String file = "src/main/resources/userItem.data";
        BufferedReader in = new BufferedReader(new FileReader(file));

        Map<Long, Map<Long, Preference>> result = new HashMap<Long, Map<Long, Preference>>();

        String line;
        while((line = in.readLine()) != null) {
            String[] data = line.split(",");

            Map<Long, Preference> preferences;
            if (!result.containsKey(Long.parseLong(data[0]))) {
                preferences = new HashMap<Long, Preference>();
            } else {
                preferences = result.get(Long.parseLong(data[0]));
            }

            preferences.put(
                    Long.parseLong(data[1]),
                    new Preference(
                            new Article(Long.parseLong(data[1])),
                            Double.parseDouble(data[2])
                    )
            );

            result.put(
                    Long.parseLong(data[0]),
                    preferences
            );
        }
        in.close();

        return result;
    }
}
