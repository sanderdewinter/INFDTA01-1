import java.util.Map;

public interface ISimilarity {
    double getSimilarity(Map<Long, Preference> originUser, Map<Long, Preference> targetUser);
}
