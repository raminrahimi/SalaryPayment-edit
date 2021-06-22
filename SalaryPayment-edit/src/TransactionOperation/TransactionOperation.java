package TransactionOperation;
import fileOperation.Read;
import fileOperation.Write;
import Entity.Inventory;
import Entity.Payment;
import Entity.Transaction;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TransactionOperation {

    static Logger log = Logger.getLogger(Transaction.class.getName());

    public List<Transaction> transaction()  {
        Write fileWriteOperation = new Write();
        Payment debtorPayment = findDebtor();
        List<Payment> creditors = findcreditors();
        List<Transaction> transactionList = new ArrayList<>();
        BigDecimal debtorInventoryAmount = findDebtorInventory();
        BigDecimal sumOfCreditorsPaymentAmount = findCreditorsAmount();
        boolean check = checkIfTransactionPossible(debtorInventoryAmount, sumOfCreditorsPaymentAmount);
        if (check) {
            for (Payment creditorPayment : creditors) {
                Transaction transaction = new Transaction(debtorPayment.getDepositNumber(), creditorPayment.getDepositNumber(), creditorPayment.getAmount());
                transactionList.add(transaction);
                fileWriteOperation.WriteTranactionFile(transaction);
            }
            log.info("all transcations were successfull!!");
        } else {
            log.info("Transaction failed!!! Not enough Inventory!");
        }

        return transactionList;
    }



    private boolean checkIfTransactionPossible(BigDecimal debtorInventoryAmount, BigDecimal sumOfCreditorsPaymentAmount) {
        if (debtorInventoryAmount.compareTo(sumOfCreditorsPaymentAmount) == 1) return true;
        return false;
    }

        private BigDecimal findCreditorsAmount() {
        Payment debtorPayment = findDebtor();
        Read read = new Read();
        BigDecimal creditorsInventoryAmount = new BigDecimal(0);
        List<Inventory> inventoryList = read.readInventoryFile();
        for (Inventory inventory : inventoryList) {
            if (!inventory.getDepositNumber().equals(debtorPayment.getDepositNumber())) {
                creditorsInventoryAmount = creditorsInventoryAmount.add(inventory.getAmount());
            }
        }
        return creditorsInventoryAmount;
    }


    public BigDecimal findDebtorInventory() {
        Read read = new Read();
        Payment debtorPayment = findDebtor();
        BigDecimal debtorInventoryAmount = null;
        List<Inventory> inventoryList = read.readInventoryFile();
        for (Inventory inventory : inventoryList) {
            if (inventory.getDepositNumber().equals(debtorPayment.getDepositNumber())) {
                debtorInventoryAmount = new BigDecimal(String.valueOf(inventory.getAmount()));
            }
        }
        return debtorInventoryAmount;
    }


    public Payment findDebtor() {
        Payment debtor = null;
        Read read = new Read();
        List<Payment> paymentList = read.readPaymentFile();
        for (Payment payment : paymentList
        ) {
            if (payment.isDebtor()) {
                debtor = payment;
            }
        }
        return debtor;
    }

    public List<Payment> findcreditors() {
        List<Payment> creditorsPaymentList = new ArrayList<>();
        Read read = new Read();
        List<Payment> paymentList = read.readPaymentFile();
        for (Payment payment : paymentList) {
            if (!payment.isDebtor()) {
                creditorsPaymentList.add(payment);
            }
        }
        return creditorsPaymentList;
    }
    public Inventory debtorInfo(Payment payment) {
        Inventory debtorInventory = new Inventory();
        Read fileReadOperation = new Read();
        List<Inventory> inventoryList = fileReadOperation.readInventoryFile();
        for (Inventory inventory : inventoryList) {
            if (inventory.getDepositNumber().compareTo(payment.getDepositNumber())==1)
                debtorInventory.setDepositNumber(inventory.getDepositNumber());
        }
        return debtorInventory;
    }


    public List<Inventory> updateInventories(List<Transaction> transactionList) {
        Payment debtorPayment = findDebtor();
        BigDecimal sumOfCreditorsPaymentAmount = SumOfTransactions();
        Inventory debtorInventory = debtorInfo(debtorPayment);
        Read fileReadOperation = new Read();
        List<Inventory> inventoryList = fileReadOperation.readInventoryFile();
        for (Transaction transaction : transactionList) {
            for (Inventory inventory : inventoryList) {
                if (transaction.getCreditorDepositNumber().compareTo(inventory.getDepositNumber())==1) {
                    inventory.setAmount(inventory.getAmount().add(transaction.getAmount()));
                }

            }

        }

        for (Inventory inventory : inventoryList) {
            if (inventory.getDepositNumber().compareTo("1.10.100.1")==0) {
                sumOfCreditorsPaymentAmount = inventory.getAmount().subtract(sumOfCreditorsPaymentAmount);
                inventory.setAmount(sumOfCreditorsPaymentAmount);
            }
        }
        return inventoryList;

    }


    public BigDecimal SumOfTransactions() {
        BigDecimal sumOfTransactions = new BigDecimal(0);
        Read read = new Read();
        List<Payment> paymentList = read.readPaymentFile();
        for (Payment payment : paymentList) {
            if (payment.getDepositNumber().compareTo("1.10.100.1") != 0) {
                sumOfTransactions = sumOfTransactions.add(payment.getAmount());
            }
        }
        return sumOfTransactions;
    }
}
