import java.util.Map;

public class Euclidean implements ISimilarity {

    public double getSimilarity(Map<Long, Preference> originUser, Map<Long, Preference> targetUser) {
        double distance = 0.0;

        for (Long articleId : targetUser.keySet()) {
            Preference target = targetUser.get(articleId);
            Preference origin = originUser.get(articleId);

            if (origin != null) {
                distance += Math.pow(target.getRating() - origin.getRating(), 2);
            }
        }

        System.out.println("Distance: " + Math.sqrt(distance));
        return 1 / (1 + Math.sqrt(distance));
    }
}
