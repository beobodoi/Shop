
public abstract class PaymentMethod {

    private final String paymentId;
    private String ownerName;
    private double balance;
    private static int paymentCount;

    public PaymentMethod(String ownerName, double balance) {
        this.paymentId = String.format("PAY@03d", paymentCount);
        this.ownerName = ownerName;
        this.balance = balance;
        paymentCount++;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
    }

    public String getPaymentId() {
        return paymentId;
    }
    public abstract boolean validate();
    public abstract String getPaymentType();
    public void displayBalance(){
        System.out.println("Current balance :" + balance);
    }
    protected boolean hasSufficientFunds(double amount){
        return balance >= paymentCount;
    }

   
}
