public interface IProductSales {
    /**
     * @return the total of all sales entries
     */
    int getTotalSales();

    /**
     * @return the average sales (double)
     */
    double getAverageSales();

    /**
     * Number of sales entries strictly greater than the sales limit.
     * @return count of sales > limit
     */
    int getSalesOverLimit();

    /**
     * Number of sales entries strictly less than or equal to the sales limit.
     * (We treat "under limit" as sales < limit in the sample — see implementation notes.)
     * @return count of sales < limit
     */
    int getSalesUnderLimit();

    /**
     * Number of years processed (rows of data)
     * @return years processed
     */
    int getYearsProcessed();
}

