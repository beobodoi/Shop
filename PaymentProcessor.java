
public class PaymentProcessor {

    public static boolean process(PaymentMethod pm, double amount) {
        if (pm instanceof Payable) {
            return ((Payable)pm) .processPayment(amount);
        }
        return false;
    }
    public static boolean process(PaymentMethod pm, double amount, String description){
boolean success= process(pm,amount);
if (success){
    new Transaction(pm, amount, description);
}return success;
}
    public static boolean process(Transaction transaction){
if (transaction==null)return false;
return process(transaction.getPaymentMethod(),transaction.getAmount());
    }
    public static void executePayment(Payable payable, double amount){
boolean success=payable.processPayment(amount);
System.out.println("PAYMENT EXE:"+(success? "S":"F"));
    }
    public static void executeRefund(Refundable refundable, double amount)
    {boolean success=refundable.refund(amount);
System.out.println("REFUND EXE:"+(success? "S":"F"));}
}
