import java.util.Map;

public class Pearson implements ISimilarity {
    public double getDistance(Map<Long, Preference> originUser, Map<Long, Preference> targetUser) {
        double sumProductOriginTarget = 0.0;
        double sumOriginRating = 0.0;
        double sumTargetRating = 0.0;
        double sumPowerOriginRating = 0.0;
        double sumPowerTargetRating = 0.0;

        for (Long articleId : targetUser.keySet()) {
            Preference target = targetUser.get(articleId);
            Preference origin = originUser.get(articleId);

            if (origin != null) {
                sumProductOriginTarget += target.getRating() * origin.getRating();

                sumOriginRating += origin.getRating();
                sumTargetRating += target.getRating();

                sumPowerOriginRating += Math.pow(origin.getRating(), 2);
                sumPowerTargetRating += Math.pow(target.getRating(), 2);
            }
        }

        return (sumProductOriginTarget - ((sumOriginRating * sumTargetRating) / originUser.size())) /
                ((sumPowerOriginRating - (Math.pow(sumOriginRating, 2) / originUser.size())) * (sumPowerTargetRating - (Math.pow(sumTargetRating, 2) / targetUser.size())));
    }
}
