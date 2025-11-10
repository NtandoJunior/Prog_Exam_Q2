import java.util.ArrayList;
import java.util.List;

public class ProductSales implements IProductSales {
    private final int[][] sales; // [year][product]
    private final int salesLimit;

    private int totalSales;
    private double averageSales;
    private int salesOverLimit;
    private int salesUnderLimit;
    private int yearsProcessed;

    /**
     * Construct with a 2D array of sales and a limit.
     * The sales array is expected to be rows = years, cols = products per year.
     */
    public ProductSales(int[][] sales, int salesLimit) {
        if (sales == null) throw new IllegalArgumentException("sales data cannot be null");
        this.sales = sales;
        this.salesLimit = salesLimit;
        process();
    }

    private void process() {
        totalSales = 0;
        salesOverLimit = 0;
        salesUnderLimit = 0;
        int count = 0;
        yearsProcessed = sales.length;

        for (int i = 0; i < sales.length; i++) {
            if (sales[i] == null) continue;
            for (int j = 0; j < sales[i].length; j++) {
                int s = sales[i][j];
                totalSales += s;
                count++;
                if (s > salesLimit) {
                    salesOverLimit++;
                } else {
                    // sample output treats values <= 500 as "under limit"
                    salesUnderLimit++;
                }
            }
        }
        if (count > 0) {
            averageSales = (double) totalSales / (double) count;
        } else {
            averageSales = 0.0;
        }
    }

    @Override
    public int getTotalSales() {
        return totalSales;
    }

    @Override
    public double getAverageSales() {
        return averageSales;
    }

    @Override
    public int getSalesOverLimit() {
        return salesOverLimit;
    }

    @Override
    public int getSalesUnderLimit() {
        return salesUnderLimit;
    }

    @Override
    public int getYearsProcessed() {
        return yearsProcessed;
    }

    /** Optional helper to get flattened list of sales values. */
    public List<Integer> getAllSales() {
        List<Integer> list = new ArrayList<>();
        for (int[] row : sales) {
            if (row == null) continue;
            for (int v : row) list.add(v);
        }
        return list;
    }

    /** Pretty text summary matching the sample format. */
    public String getSummaryText() {
        StringBuilder sb = new StringBuilder();
        sb.append("DATA LOG\n");
        sb.append("*******************************\n");
        sb.append("Total Sales: ").append(getTotalSales()).append("\n");
        sb.append("Average Sales: ").append(Math.round(getAverageSales())).append("\n");
        sb.append("Sales over limit: ").append(getSalesOverLimit()).append("\n");
        sb.append("Sales under limit: ").append(getSalesUnderLimit()).append("\n");
        sb.append("*******************************\n");
        return sb.toString();
    }
}

