




import com.labas.TariffTypeComparator;
import generated.TariffType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ComparatorTest {

    private TariffTypeComparator comparator;
    private TariffType tariffType1;
    private TariffType tariffType2;
    private TariffType tariffType3;

    @BeforeEach
    public void setUp() {
        comparator = new TariffTypeComparator();

        // Creating instances of Orangery.Flower
        tariffType1 = new TariffType();
        tariffType1.setName("Safsf");

        tariffType2 = new TariffType();
        tariffType2.setName("Vsafad");

        tariffType3 = new TariffType();
        tariffType3.setName("Safsf");
    }

    @Test
    public void testCompare_LexicographicalOrder() {
        int result = comparator.compare(tariffType1, tariffType2);
        assertEquals(true, result < 0, "Expected Rose to be lexicographically before Tulip");
    }

    @Test
    public void testCompare_SameName() {
        int result = comparator.compare(tariffType1, tariffType3);
        assertEquals(0, result, "Expected comparison to return 0 for flowers with the same name");
    }

    @Test
    public void testCompare_ReverseOrder() {
        int result = comparator.compare(tariffType2, tariffType1);

        assertEquals(true, result > 0, "Expected Tulip to be lexicographically after Rose");
    }
}
