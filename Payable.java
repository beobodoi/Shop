
public interface Payable {

    boolean processPayment(double amount);

    String getPaymentDetails();

    default double applyServiceFee(double amount) {
        return amount * 1.02;
    }

}
