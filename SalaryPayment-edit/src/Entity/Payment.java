package Entity;

import java.math.BigDecimal;

public class Payment {
    private boolean debtor;
    private String depositNumber;
    private BigDecimal amount;


    public Payment(boolean debtor, String depositNumber) {
        this.debtor = debtor;
        this.depositNumber = depositNumber;
    }


    public Payment(boolean debtor, String depositNumber, BigDecimal amount) {
        this.debtor = debtor;
        this.depositNumber = depositNumber;
        this.amount = amount;
    }

    public boolean isDebtor() {
        return debtor;
    }

    public void setDebtor(boolean debtor) {
        this.debtor = debtor;
    }

    public String getDepositNumber() {
        return depositNumber;
    }

    public void setDepositNumber(String depositNumber) {
        this.depositNumber = depositNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        if (isDebtor()) return "debtor" + "\t" + depositNumber + "\t" + amount;
        return "creditor" + "\t" + depositNumber + "\t" + amount;
    }
}
