import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Main {
    public static Map<Long, Map<Long, Preference>> data;

    public static List<Item> items;

    public static void addData(Long userId, Long articleId, Double rating) {
        if (data.containsKey(userId)) {
            Map<Long, Preference> preferences = data.get(userId);

            if (preferences.containsKey(articleId)) {
                preferences.get(articleId).setRating(rating);
            } else {
                preferences.put(
                        articleId,
                        new Preference(new Item(articleId), rating)
                );
            }

            data.put(userId, preferences);
        } else {
            HashMap<Long, Preference> preferences = new HashMap<Long, Preference>();

            preferences.put(
                    articleId,
                    new Preference(new Item(articleId), rating)
            );

            data.put(userId, preferences);
        }
    }

    public static void setData() throws IOException {
        String file = "src/main/resources/userItem.data";
        BufferedReader in = new BufferedReader(new FileReader(file));

        Map<Long, Map<Long, Preference>> result = new HashMap<Long, Map<Long, Preference>>();
        List<Item> itemsResult = new ArrayList<Item>();

        String line;
        while((line = in.readLine()) != null) {
            String[] data = line.split(",");

            Map<Long, Preference> preferences;
            if (!result.containsKey(Long.parseLong(data[0]))) {
                preferences = new HashMap<Long, Preference>();
            } else {
                preferences = result.get(Long.parseLong(data[0]));
            }

            Item item = new Item(Long.parseLong(data[1]));
            preferences.put(
                    Long.parseLong(data[1]),
                    new Preference(
                            item,
                            Double.parseDouble(data[2])
                    )
            );

            if (!itemsResult.contains(item)) {
                itemsResult.add(item);
            }

            result.put(
                    Long.parseLong(data[0]),
                    preferences
            );
        }
        in.close();

        data = result;
        items = itemsResult;
    }
}
