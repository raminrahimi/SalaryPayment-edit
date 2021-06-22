package fileOperation;
import Entity.Inventory;
import Entity.Payment;
import Entity.Transaction;
import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.List;

public class Write {
    public void WritePaymentFile(List<Payment> payments) {

        String s = "";
        for (Payment payment : payments) {
            s += payment.toString() + "\n";

        }
        try {
            Files.write(
                    Paths.get(Generat.PaymentfilePath),
                    s.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void WriteInventoryFile(Inventory inventory) {
        String s = inventory.toString() + "\n";
        try {
            Files.write(
                    Paths.get(Generat.InventoryfilePath),
                    s.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void WriteTranactionFile(Transaction transaction) {
        String s = transaction.toString() + "\n";
        try {
            Files.write(
                    Paths.get(Generat.TransactionfilePath),
                    s.getBytes(),
                    StandardOpenOption.APPEND);
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    public void updateInventoryFile(List<Inventory> inventoryList) {
        try {
            BufferedWriter writer = Files.newBufferedWriter(Paths.get(Generat.InventoryfilePath));
            writer.write("");
            writer.flush();
            for (Inventory inventory : inventoryList) {
                String s = inventory.toString() + "\n";
                Files.write(
                        Paths.get(Generat.InventoryfilePath),
                        s.getBytes(),
                        StandardOpenOption.APPEND);
            }
        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }
}


