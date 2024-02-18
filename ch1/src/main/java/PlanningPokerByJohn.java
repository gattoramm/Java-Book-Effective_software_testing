import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class PlanningPokerByJohn {
    public List<String> identityExtremes(List<Estimate> estimates) {
        Estimate lowestEstimate = null;
        Estimate highestEstimate = null;

        for (Estimate estimate : estimates) {
            if (highestEstimate == null || estimate.getEstimate() > highestEstimate.getEstimate())
                highestEstimate = estimate;
            else if (lowestEstimate == null || estimate.getEstimate() < lowestEstimate.getEstimate())
                lowestEstimate = estimate;
        }

        return Arrays.asList(lowestEstimate.getDeveloper(), highestEstimate.getDeveloper());
    }
}
