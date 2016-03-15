import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

public class MovieLensService {
    public static List<Movie> movies;

    public static void loadMovies() throws IOException, ParseException {
        String file = "src/main/resources/u.item";
        BufferedReader in = new BufferedReader(new FileReader(file));

        List<Movie> result = new ArrayList<Movie>();

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

            result.add(new Movie(
                    Long.valueOf(data[0]),
                    data[1],
                    dateRelease,
                    dateVideoRelease,
                    data[4],
                    genres
            ));
        }

        movies = result;
    }
}
