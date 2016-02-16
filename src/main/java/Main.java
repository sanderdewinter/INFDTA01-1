import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public Map<Long, List<UserPreference>> getData() throws IOException {
        String file = "src/main/resources/userItem.data";
        BufferedReader in = new BufferedReader(new FileReader(file));

        Map<Long, List<UserPreference>> result = new HashMap<Long, List<UserPreference>>();

        String line;
        while((line = in.readLine()) != null) {
            String[] data = line.split(",");

            List<UserPreference> preferences;
            if (!result.containsKey(Long.parseLong(data[0]))) {
                preferences = new ArrayList<UserPreference>();
            } else {
                preferences = result.get(Long.parseLong(data[0]));
            }

            preferences.add(new UserPreference(
                    new User(Long.parseLong(data[0])),
                    new Article(Long.parseLong(data[1])),
                    Double.parseDouble(data[2])
            ));

            result.put(
                    Long.parseLong(data[0]),
                    preferences
            );
        }
        in.close();

        return result;
    }
}
