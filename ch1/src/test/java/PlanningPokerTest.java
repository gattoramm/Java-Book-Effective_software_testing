import net.jqwik.api.ForAll;
import net.jqwik.api.Property;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PlanningPokerTest {
    @Test
    void rejectNullInput() {
        assertThatThrownBy(()->new PlanningPoker().identityExtremes(null))
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectEmptyList() {
        assertThatThrownBy(()->{
            List<Estimate> emptyList = Collections.emptyList();
            new PlanningPoker().identityExtremes(emptyList);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void rejectSingleEstimate() {
        assertThatThrownBy(()-> {
            List<Estimate> list = Collections.singletonList(new Estimate("Eleanor", 1));
            new PlanningPoker().identityExtremes(list);
        })
                .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    void twoEqualEstimates() {
        List<Estimate> list = Arrays.asList(
                new Estimate("Mauricio", 5),
                new Estimate("Frank", 5)
        );

        List<String> devs = new PlanningPoker().identityExtremes(list);

        assertThat(devs).containsExactlyInAnyOrder("Mauricio", "Frank");
    }

    @Test
    void manyEstimates() {
        List<Estimate> list = Arrays.asList(
                new Estimate("Mauricio", 10),
                new Estimate("Frank", 5),
                new Estimate("Arie", 7)
        );

        List<String> devs = new PlanningPoker().identityExtremes(list);

        assertThat(devs).containsExactlyInAnyOrder("Mauricio", "Frank");
    }

    @Property
    void estimatesInAnyOrder(@ForAll("estimates") List<Estimate> estimates) {
        estimates.add(new Estimate("MrLowEstimate", 1));
        estimates.add(new Estimate("MsHighEstimate", 100));
        Collections.shuffle(estimates);

        List<String> dev = new PlanningPoker().identityExtremes(estimates);

        assertThat(dev).containsExactlyInAnyOrder("MrLowEstimate", "MsHighEstimate");
    }
}
