
public class BankTransfer extends PaymentMethod implements Payable, Refundable {

    public static boolean isIsBankHoliday() {
        return isBankHoliday;
    }

   
    private String accountNumber;
    private String bankName;
    private final String routingNumber;
    private static boolean isBankHoliday = false;

    public static void setBankHoliday(boolean isHoliday) {
        isBankHoliday = isHoliday;
    }

    public BankTransfer(String ownerName, double balance, String accountNumber, String bankName, String routingNumber) {
        super(ownerName, balance);
        this.accountNumber = accountNumber;
        this.bankName = bankName;
        this.routingNumber = routingNumber;
    }

    @Override
    public boolean refund(double amount) {
        setBalance(getBalance() + amount);
        return true;
    }

    @Override
    public String getRefundPolicy() {
        return "Refunds processed within 3-5 business days";
    }

    public String getRoutingNumber() {
        return routingNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        if (isBankHoliday) {
            return false;
        }
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
        String masked = accountNumber != null && accountNumber.length() >= 4
                ? "xxxx" + accountNumber.substring(accountNumber.length() - 4)
                : "xxxx";
        return "BankName:" + bankName + "-AccountNumber:" + masked;
    }

    @Override
    public boolean validate() {
        return accountNumber.length() >= 8 && routingNumber.length() == 9;
    }

    @Override
    public String getPaymentType() {
        return "Bank Transfer";
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }
 @Override
    public String toString() {
        return "Id:" + getPaymentId() + "- OwnerName:" + getOwnerName() + "- BankName:" + bankName +"- Type:" + getPaymentType() + "- Card: N/A" + "- EX: N/A " + "- Balance :" + getBalance();
    }
}
