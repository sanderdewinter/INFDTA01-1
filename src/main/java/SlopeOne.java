import java.util.*;

public class SlopeOne {

    private Map<Long, Map<Long, Preference>> data;
    private List<Item> items;
    private Map<List<Long>, List<Double>> deviationMatrix;

    private Comparator<List<Double>> comparator = new Comparator<List<Double>>() {
        public int compare(List<Double> i, List<Double> j) {
            return j.get(1).compareTo(i.get(1));
        }
    };

    public SlopeOne(Map<Long, Map<Long, Preference>> data, List<Item> items) {
        this.data = data;
        this.items = items;
        this.deviationMatrix = setDeviationMatrix();
    }

    public Double getPredictedRating(long targetId, long targetItemId) {
        Map<Long, Preference> preferenceMap = data.get(targetId);

        double i = 0;
        int j = 0;

        for (Long itemId : preferenceMap.keySet()) {
            if (itemId != targetItemId) {
                double rating = preferenceMap.get(itemId).getRating();
                List<Double> deviation = deviationMatrix.get(Arrays.asList(targetItemId, itemId));

                i += (rating + deviation.get(0)) * deviation.get(1);
                j += deviation.get(1);
            }
        }

        if (i == 0 || j == 0) {
            return null;
        } else {
            return i / j;
        }
    }

    public List<List<Double>> getTopPredictions(long targetId, int amountOfRecommendations) {
        List<List<Double>> result = new ArrayList<List<Double>>();
        Map<Long, Preference> preferenceMap = data.get(targetId);

        for (Long itemId : preferenceMap.keySet()) {
            Double predictedRating = getPredictedRating(targetId, itemId);

            List<Double> row = new ArrayList<Double>();
            row.add(Double.valueOf(itemId));
            row.add(predictedRating);

            result.add(row);
            Collections.sort(result, comparator);

            if (amountOfRecommendations < result.size()) {
                result.remove(result.size() - 1);
            }
        }

        return result;
    }

    public List<Double> getDeviation(long itemId1, long itemId2) {
        Double currentDeviation = 0.0;
        int usersRated = 0;

        for (Long userId : getData().keySet()) {
            Map<Long, Preference> preference = getData().get(userId);

            if (preference.containsKey(itemId1) && preference.containsKey(itemId2)) {
                currentDeviation += preference.get(itemId1).getRating() - preference.get(itemId2).getRating();
                usersRated++;
            }
        }

        if (usersRated == 0) {
            return null;
        } else {
            return Arrays.asList(currentDeviation / usersRated, (double) usersRated);
        }
    }

    public Map<List<Long>, List<Double>> setDeviationMatrix() {
        Map<List<Long>, List<Double>> result = new HashMap<List<Long>, List<Double>>();

        for (Item i : getItems()) {
            Long iId = i.getId();

            for (Item j : getItems()) {
                Long jId = j.getId();

                if (!iId.equals(jId) && !result.containsKey(Arrays.asList(iId, jId))) {
                    List<Double> deviation = getDeviation(iId, jId);

                    if (deviation != null) {
                        result.put(Arrays.asList(iId, jId), deviation);
                        result.put(Arrays.asList(jId, iId), deviation);
                    }
                }
            }
        }

        return result;
    }

    public void updateDeviationMatrix(long itemId) {
//        Map<List<Long>, List<Double>> result = getDeviationMatrix();
//
//        for (List<Long> itemIds : deviationMatrix.keySet()) {
//            if (itemIds.contains(itemId)) {
//                List<Double> deviation = getDeviation(itemIds.get(0), itemIds.get(1));
//                result.remove(itemIds);
//                result.put(itemIds, deviation);
//            }
//        }
//
//        deviationMatrix = result;
    }

    public Map<List<Long>, List<Double>> getDeviationMatrix() {
        return deviationMatrix;
    }

    private static List<Preference> getPreferences(Map<Long, Map<Long, Preference>> data) {
        List<Preference> preferences = new ArrayList<Preference>();

        for (Long userId : data.keySet()) {
            Map<Long, Preference> preferenceMap = data.get(userId);

            for (Long itemId : preferenceMap.keySet()) {
                preferences.add(preferenceMap.get(itemId));
            }
        }

        return preferences;
    }

    public Map<Long, Map<Long, Preference>> getData() {
        return data;
    }

    public List<Item> getItems() {
        return items;
    }
}
