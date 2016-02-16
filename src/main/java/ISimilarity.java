import java.util.Map;

public interface ISimilarity {
    double getDistance(Map<Long, Preference> originUser, Map<Long, Preference> targetUser);
}
