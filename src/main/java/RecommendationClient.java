import java.io.IOException;
import java.util.*;

public class RecommendationClient {

    private ISimilarity similarityAlgorithm;

    private Comparator<List<Double>> comparator = new Comparator<List<Double>>() {
        public int compare(List<Double> i, List<Double> j) {
            return j.get(1).compareTo(i.get(1));
        }
    };

    public RecommendationClient(ISimilarity similarityAlgorithm) {
            this.similarityAlgorithm = similarityAlgorithm;
    }

    public List<List<Double>> getNearestNeighbours(int amountOfNeighbours, long originUserId, double threshold) throws IOException {
        Map<Long, Map<Long, Preference>> data = Main.getData();
        Map<Long, Preference> origin = data.get(originUserId);

        List<List<Double>> result = new ArrayList<List<Double>>();

        for (Long userId : data.keySet()) {
            if (userId != originUserId) {
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
}
