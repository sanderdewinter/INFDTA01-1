import java.util.Map;

public class Cosine implements ISimilarity {
    public double getDistance(Map<Long, Preference> originUser, Map<Long, Preference> targetUser) {
        double sumOriginTimesTargetRating = 0.0;
        double sumSquarePowerOriginRating = 0.0;
        double sumSquarePowerTargetRating = 0.0;

        for (Long articleId : targetUser.keySet()) {
            Preference target = targetUser.get(articleId);
            Preference origin = originUser.get(articleId);

            if (origin == null) {
                origin = new Preference(target.getArticle(), 0.0);
            }

            sumOriginTimesTargetRating += target.getRating() * origin.getRating();
            sumSquarePowerOriginRating += Math.pow(origin.getRating(), 2);
            sumSquarePowerTargetRating += Math.pow(target.getRating(), 2);
        }

        return sumOriginTimesTargetRating / (Math.sqrt(sumSquarePowerOriginRating) * Math.sqrt(sumSquarePowerTargetRating));
    }
}
