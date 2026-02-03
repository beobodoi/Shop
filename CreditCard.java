
public class CreditCard extends PaymentMethod implements Payable, Refundable {

    private String cardNumber;
    private String cvv;
    private String expiryDate;

    public CreditCard(String ownerName, String cardNumber, String cvv, String expiryDate, double balance) {
        super(ownerName, balance);
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;
    }

    public CreditCard() {
        this("Unknown", "0000-0000-0000-0000", "000", "00/00");
    }

    public CreditCard(String ownerName, String cardNumber, String cvv, String expiryDate) {
        super(ownerName, 0.0);
        this.cardNumber = cardNumber;
        this.cvv = cvv;
        this.expiryDate = expiryDate;

    }

    @Override
    public boolean validate() {
        return cvv.length() == 3;

    }

    @Override
    public String getPaymentType() {
        return "CreditCard";
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
        if (cardNumber == null || cardNumber.length() < 4) {
            return "the ko hop le";
        }
        String last4 = cardNumber.substring(cardNumber.length() - 4);
        return "creditcard:" + last4;
    }

    @Override
    public boolean refund(double amount) {
        setBalance(getBalance() + amount);
        return true;
    }

    @Override
    public String getRefundPolicy() {
        return "Refunds processed within 5-7 business days";
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCvv() {
        return cvv;
    }

    public void setCvv(String cvv) {
        this.cvv = cvv;
    }

    public String getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(String expiryDate) {
        this.expiryDate = expiryDate;
    }

    @Override
    public String toString() {
        String masked = cardNumber != null && cardNumber.length() >= 4
                ? "xxxx-xxxx-xxxx-" + cardNumber.substring(cardNumber.length() - 4)
                : "xxxx-xxxx-xxxxx-xxxxx";
        return "Id:" + getPaymentId() + "-OwnerName:" + getOwnerName() + "-Type:" + getPaymentType() + "-Card:" + masked + "-ExpiryDate" + expiryDate + "-Balance" + getBalance();
    }

}
