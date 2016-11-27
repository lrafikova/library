package com.example.library.model;

import java.math.BigDecimal;

/**
 * Class describe the object of Borrower
 *
 * @author Liliya Rafikova
 */
public class Borrower {

    private String name;
    private BigDecimal debt;

    /**
     * @param name the name of Borrower
     * @param debt the debt for books
     */
    public Borrower(String name, BigDecimal debt) {
        this.name = name;
        this.debt = debt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getDebt() {
        return debt;
    }

    public void setDebt(BigDecimal debt) {
        this.debt = debt;
    }
}
