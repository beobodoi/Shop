
public class DigitalWallet extends PaymentMethod implements Payable {

    public DigitalWallet(String ownerName, String email) {

        this(ownerName, email, "N/A");

    }

    public DigitalWallet(String ownerName, String email, String phoneNumber) {
        this(ownerName, 0.0, email, phoneNumber);
    }

    public DigitalWallet(String ownerName, double balance, String email, String phoneNumber) {
        super(ownerName, balance);
        this.email = email;
        this.phoneNumber = phoneNumber;
    }
    private String email;
    private String phoneNumber;
    private final String WALLET_PROVIDER = "PayNow";

    @Override
    public boolean validate() {

        return email.contains("@") && phoneNumber != null;
    }

    @Override
    public boolean processPayment(double amount) {
        if (!validate()) {
            return false;
        }
        if (!hasSufficientFunds(amount)) {
            return false;
        }
        setBalance(getBalance() - amount);
        return true;
    }

    @Override
    public String getPaymentDetails() {
        return "Email:" + email + "- PhoneNumber:" + phoneNumber;
    }

    @Override
    public double applyServiceFee(double amount) {
        return amount * 1.015;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getWALLET_PROVIDER() {
        return WALLET_PROVIDER;
    }

    @Override
    public String getPaymentType() {
        return "Digital Wallet";
    }

}
