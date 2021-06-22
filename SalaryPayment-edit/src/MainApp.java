import TransactionOperation.TransactionOperation;
import fileOperation.Generat;
import fileOperation.Read;
import fileOperation.Write;
import Entity.Inventory;
import Entity.Payment;
import Entity.Transaction;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;


public class MainApp {
    static Logger log = Logger.getLogger(Transaction.class.getName());
    public static void main(String[] args)  {

        Generat generat = new Generat();
        Read read = new Read();
        Write write = new Write();
        try {
            generat.createFiles();
        } catch (Exception e) {
            e.printStackTrace();
            log.info("PaymentFile creation failed!");
            log.info("InventoryFile creation failed!");
            log.info("Transaction creation failed!");
        }
        Random random = new Random();
        BigDecimal debtorInventory = new BigDecimal(random.nextInt(60000000 - 40000000) + 40000000);
        generat.generateRandomPaymentData(50, 0, 5000);
        List<Payment> paymentList = read.readPaymentFile();
        generat.generateRandomInventories(paymentList, debtorInventory);
        TransactionOperation transactionOperation = new TransactionOperation();
        List<Transaction> transactionList = transactionOperation.transaction();
        List<Inventory> inventories = transactionOperation.updateInventories(transactionList);
        write.updateInventoryFile(inventories);

    }
}
