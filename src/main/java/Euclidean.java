import java.util.Map;

public class Euclidean implements ISimilarity {

    public double getDistance(Map<Long, Preference> originUser, Map<Long, Preference> targetUser) {
        double result = 0.0;

        for (Long articleId : targetUser.keySet()) {
            Preference target = targetUser.get(articleId);
            Preference origin = originUser.get(articleId);

            if (origin != null) {
                result += Math.pow(target.getRating() - origin.getRating(), 2);
            }
        }

        return Math.sqrt(result);
    }
}
