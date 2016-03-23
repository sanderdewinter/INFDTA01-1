import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.*;
import java.util.regex.Pattern;

public class MovieLensService {
    public static List<Item> items;
    public static List<User> users;

    public static Map<Long, Map<Long, Preference>> data;

    public static void loadMovies() throws IOException, ParseException {
        String file = "src/main/resources/u.item";
        BufferedReader in = new BufferedReader(new FileReader(file));

        List<Item> result = new ArrayList<Item>();

        String line;
        while((line = in.readLine()) != null) {
            String[] data = line.split(Pattern.quote("|"));

            DateFormat formatter = new SimpleDateFormat("dd-MMM-yyyy");

            Date dateRelease;
            if (!data[2].equals("")) {
                dateRelease = formatter.parse(data[2]);
            } else {
                dateRelease = null;
            }

            Date dateVideoRelease;
            if (!data[3].equals("")) {
                dateVideoRelease = formatter.parse(data[3]);
            } else {
                dateVideoRelease = null;
            }

            List<Integer> genres = new ArrayList<Integer>();
            for (int i = 5; i < 5 + 18 + 1; i++) {
                genres.add(Integer.valueOf(data[i]));
            }

            result.add(new Item(
                    Long.valueOf(data[0]),
                    data[1],
                    dateRelease,
                    dateVideoRelease,
                    data[4],
                    genres
            ));
        }
        in.close();

        items = result;
    }

    public static void loadUsers() throws IOException {
        String file = "src/main/resources/u.user";
        BufferedReader in = new BufferedReader(new FileReader(file));

        List<User> result = new ArrayList<User>();

        String line;
        while((line = in.readLine()) != null) {
            String[] data = line.split(Pattern.quote("|"));

            result.add(new User(
                    Integer.valueOf(data[0]),
                    Integer.valueOf(data[1]),
                    data[2].charAt(0),
                    data[3],
                    data[4]
            ));
        }
        in.close();

        users = result;
    }

    public static void loadUserPreference() throws IOException {
        String file = "src/main/resources/u.data";
        BufferedReader in = new BufferedReader(new FileReader(file));

        Map<Long, Map<Long, Preference>> result = new HashMap<Long, Map<Long, Preference>>();

        String line;
        while((line = in.readLine()) != null) {
            String[] data = line.split("\t");

            Map<Long, Preference> preferences;
            if (!result.containsKey(Long.parseLong(data[0]))) {
                preferences = new HashMap<Long, Preference>();
            } else {
                preferences = result.get(Long.parseLong(data[0]));
            }

            int movieId = Integer.valueOf(data[1]) - 1;

            preferences.put(
                    Long.parseLong(data[1]),
                    new Preference(
                            items.get(movieId),
                            Double.parseDouble(data[2]),
                            Instant.ofEpochSecond(Long.parseLong(data[3]))
                    )
            );

            result.put(
                    Long.parseLong(data[0]),
                    preferences
            );
        }
        in.close();

        data = result;
    }
}
