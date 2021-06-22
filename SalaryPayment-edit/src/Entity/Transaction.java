package Entity;

import java.math.BigDecimal;

public class Transaction {
    private String debtorDepositNumber;
    private String creditorDepositNumber;
    private BigDecimal amount;

    public Transaction(String debtorDepositNumber, String creditorDepositNumber, BigDecimal amount) {
        this.debtorDepositNumber = debtorDepositNumber;
        this.creditorDepositNumber = creditorDepositNumber;
        this.amount = amount;
    }

    public String getDebtorDepositNumber() {
        return debtorDepositNumber;
    }

    public void setDebtorDepositNumber(String debtorDepositNumber) {
        this.debtorDepositNumber = debtorDepositNumber;
    }

    public String getCreditorDepositNumber() {
        return creditorDepositNumber;
    }

    public void setCreditorDepositNumber(String creditorDepositNumber) {
        this.creditorDepositNumber = creditorDepositNumber;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return debtorDepositNumber + "\t" + creditorDepositNumber + "\t" + amount;
    }
}
