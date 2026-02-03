
import java.util.Objects;

public class Transaction {

    private final String transactionId;

    private PaymentMethod paymentMethod;

    private double amount;
    private String description;
    private static int totalTransactions;

    public Transaction(PaymentMethod paymentMethod, double amount, String description) {
        totalTransactions++;
        this.transactionId = String.format("TXN%03d", totalTransactions);
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        this.description = description;
 
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null || getClass() != obj.getClass()) {
            return false;
        }
        Transaction other = (Transaction) obj;
        return Objects.equals(transactionId, other.transactionId);
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    @Override
    public int hashCode() {
        return Objects.hash(transactionId);
    }

    @Override
    public String toString() {
        return "TransId" + transactionId + "- Amount" + amount + "Des:" + description + "-Methos" + paymentMethod.getPaymentType();
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }
    public String getTransactionId() {
        return transactionId;
    }
    public void setDescription(String description) {
        this.description = description;
    }
}
