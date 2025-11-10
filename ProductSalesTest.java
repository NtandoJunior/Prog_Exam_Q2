import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ProductSalesTest {

    @Test
    public void testGetTotalAndAverageAndCounts() {
        int[][] data = {
                {300, 150, 700},
                {250, 200, 600}
        };
        int limit = 500;
        ProductSales ps = new ProductSales(data, limit);

        assertEquals(2200, ps.getTotalSales(), "Total sales should be 2200");
        assertEquals(2200.0 / 6.0, ps.getAverageSales(), 0.0001, "Average must match exact double value");
        assertEquals(2, ps.getSalesOverLimit(), "Sales over limit (>500) should be 2 (700 & 600)");
        assertEquals(4, ps.getSalesUnderLimit(), "Sales under or equal to limit should be 4");
        assertEquals(2, ps.getYearsProcessed(), "Years processed should be 2");
    }

    @Test
    public void testEmptyData() {
        int[][] data = new int[0][];
        ProductSales ps = new ProductSales(data, 500);

        assertEquals(0, ps.getTotalSales());
        assertEquals(0.0, ps.getAverageSales());
        assertEquals(0, ps.getSalesOverLimit());
        assertEquals(0, ps.getSalesUnderLimit());
        assertEquals(0, ps.getYearsProcessed());
    }
}

