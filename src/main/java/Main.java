import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static void main(String[] args) {
        try {
            Map<Long, List<Preference>> data = getData();
            System.out.println(data);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private static Map<Long, List<Preference>> getData() throws IOException {
        String file = "src/main/resources/userItem.data";
        BufferedReader in = new BufferedReader(new FileReader(file));

        Map<Long, List<Preference>> result = new HashMap<Long, List<Preference>>();

        String line;
        while((line = in.readLine()) != null) {
            String[] data = line.split(",");

            List<Preference> preferences;
            if (!result.containsKey(Long.parseLong(data[0]))) {
                preferences = new ArrayList<Preference>();
            } else {
                preferences = result.get(Long.parseLong(data[0]));
            }

            preferences.add(new Preference(
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
