import java.util.*;

public class RecommendationClient {

    private Map<Long, Map<Long, Preference>> data;

    private ISimilarity similarityAlgorithm;

    private Comparator<List<Double>> comparator = new Comparator<List<Double>>() {
        public int compare(List<Double> i, List<Double> j) {
            return j.get(1).compareTo(i.get(1));
        }
    };

    public RecommendationClient(ISimilarity similarityAlgorithm) {
            this.similarityAlgorithm = similarityAlgorithm;
    }

    public RecommendationClient(Map<Long, Map<Long, Preference>> data, ISimilarity similarityAlgorithm) {
        this.data = data;
        this.similarityAlgorithm = similarityAlgorithm;
    }

    public List<List<Double>> getNearestNeighbours(int amountOfNeighbours, long originUserId, double threshold) {
        Map<Long, Map<Long, Preference>> data = getData();
        Map<Long, Preference> origin = data.get(originUserId);

        List<List<Double>> result = new ArrayList<List<Double>>();

        for (Long userId : data.keySet()) {
            if (userId != originUserId && hasUnratedItems(origin, data.get(userId))) {
                double similarity = similarityAlgorithm.getSimilarity(origin, data.get(userId));

                if (similarity >= threshold) {
                    List<Double> row = new ArrayList<Double>();
                    row.add(Double.valueOf(userId));
                    row.add(similarity);

                    result.add(row);
                    Collections.sort(result, comparator);

                    if (amountOfNeighbours < result.size()) {
                        result.remove(result.size() - 1);
                    }
                }
            }
        }

        return result;
    }

    public List<List<Double>> getTopRecommendations(int amountOfRecommendations, Long originUserId) {
        List<Movie> movies = MovieLensService.movies;

        List<List<Double>> result = new ArrayList<List<Double>>();

        for (Movie movie : movies) {
            Double predictedRating = getPredictedRating(originUserId, movie.getId(), 25, 0.35);
            if (predictedRating == null) {
                continue;
            }

            List<Double> row = new ArrayList<Double>();
            row.add((double) movie.getId());
            row.add(predictedRating);

            result.add(row);
            Collections.sort(result, comparator);

            if (amountOfRecommendations < result.size()) {
                result.remove(result.size() - 1);
            }
        }

        return result;
    }

    public Double getPredictedRating(Long targetId, Long articleId) {
        return getPredictedRating(targetId, articleId, 3, 0.01);
    }

    public Double getPredictedRating(Long targetId, Long articleId, int amountOfNeighbours, Double threshold) {
        Map<Long, Map<Long, Preference>> data = getData();
        List<List<Double>> neighbours = getNearestNeighbours(amountOfNeighbours, targetId, threshold);

        double sumRatingTimesCoefficient = 0.0;
        double sumCoefficient = 0.0;
        for (List<Double> neighbour : neighbours) {
            Map<Long, Preference> userPreference = data.get(neighbour.get(0).longValue());

            if (userPreference.containsKey(articleId)) {
                Preference preference = userPreference.get(articleId);

                sumRatingTimesCoefficient += preference.getRating() * neighbour.get(1);
                sumCoefficient += neighbour.get(1);
            }
        }

        if (sumCoefficient == 0.0) {
            return null;
        } else {
            return sumRatingTimesCoefficient / sumCoefficient;
        }
    }

    private boolean hasUnratedItems(Map<Long, Preference> origin, Map<Long, Preference> target) {
        for (Long itemId : target.keySet()) {
            if (!origin.containsKey(itemId)) {
                return true;
            }
        }
        return false;
    }

    public Map<Long, Map<Long, Preference>> getData() {
        return data;
    }

    public void setData(Map<Long, Map<Long, Preference>> data) {
        this.data = data;
    }
}
