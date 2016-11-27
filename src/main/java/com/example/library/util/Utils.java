package com.example.library.util;

import org.joda.time.DateTime;
import org.joda.time.Months;

import java.math.BigDecimal;

/**
 * A set of helper methods related to library manipulation.
 *
 * @author Liliya Rafikova
 */
public class Utils {

    private Utils() {
        throw new IllegalAccessError("Utility class");
    }

    /**
     * Creates a <code>Months</code> representing the number of whole months
     * between the two specified datetime.
     *
     * @param start date of rent
     * @param end date of rent
     * @return the period in months
     */
    public static int diffMonths(DateTime start, DateTime end) {
        int months = Months.monthsBetween(start, end).getMonths();
        if (months < 0) {
            throw new IllegalArgumentException("The start data of rent in future");
        }
        return Months.monthsBetween(start, end).getMonths();
    }

    /**
     * Count the debt for whole period from start date till current date add discount
     *
     * @param months
     * @param priceOfRent
     * @param discount
     * @return debt
     */
    public static BigDecimal countDebt(int months, BigDecimal priceOfRent, BigDecimal discount) {
        if (months == 0) {
            return new BigDecimal(0);
        }
        return (priceOfRent.multiply(BigDecimal.valueOf(months))).subtract(discount);
    }


}
