import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class PaymentSystemDemo {
public static void main(String[] args) {
    // Test all constructors for CreditCard
CreditCard card1 = new CreditCard(); // Default
CreditCard card2 = new CreditCard("John Doe", "1234-5678-9012-3456", "123", "12/25");
CreditCard card3 = new CreditCard("Jane Smith", "9876-5432-1098-7654", "456", "06/26", 1000.0);// Parent class reference, child class objects
PaymentMethod pm1 = new CreditCard("Alice", "1111-2222-3333-4444", "111", "03/27");
PaymentMethod pm2 = new DigitalWallet("Bob", 300.0, "bob@email.com", "555-1234");
PaymentMethod pm3 = new BankTransfer("Charlie", 1000.0, "12345678", "ABC Bank", "123456789");

// Store in array/list
PaymentMethod[] methods = {pm1, pm2, pm3};

// Call methods - different implementations execute based on actual object type
for (PaymentMethod pm : methods) {
    System.out.println(pm.getPaymentType()); // Different output for each
    System.out.println(pm.validate()); // Different validation logic
}// Same object, different interface references
CreditCard cc = new CreditCard("David", "4444-3333-2222-1111", "789", "09/28",1500.0 );

Payable payable = cc; // Can be assigned to Payable
Refundable refundable = cc; // Can be assigned to Refundable

payable.processPayment(100.0);
refundable.refund(50.0);

// DigitalWallet can ONLY be assigned to Payable (not Refundable)
DigitalWallet wallet = new DigitalWallet("Eve", 200.0, "eve@email.com", "555-5678");
Payable payable2 = wallet; // ✓ OK
// Refundable refundable2 = wallet; // ✗ Compilation error!// Access static fields without creating instances
System.out.println("Total payment methods created: " + PaymentMethod.getpaymentCount);
System.out.println("Total transactions: " + Transaction.totalTransactions());

// Use static methods
BankTransfer.setBankHoliday(true);
BankTransfer bt = new BankTransfer("Frank", 500.0, "98765432", "XYZ Bank", "987654321");
System.out.println(bt.processPayment(100.0)); // Should fail because it's a holiday!

BankTransfer.setBankHoliday(false);
System.out.println(bt.processPayment(100.0)); // Now should succeed// Test with HashSet
Set<Transaction> transactionSet = new HashSet<>();

Transaction t1 = new Transaction(pm1, 100.0, "Purchase 1");
Transaction t2 = new Transaction(pm2, 200.0, "Purchase 2");
Transaction t3 = new Transaction(pm1, 100.0, "Purchase 1"); // Same transactionId as t1

transactionSet.add(t1);
transactionSet.add(t2);
transactionSet.add(t3);

// Should only contain 2 transactions (t1 and t3 are equal)
System.out.println("Transaction set size: " + transactionSet.size());

// Test with HashMap
Map<Transaction, String> transactionMap = new HashMap<>();
transactionMap.put(t1, "First transaction");
transactionMap.put(t3, "Updated transaction"); // Should update, not add new entry

System.out.println("Map size: " + transactionMap.size()); // Should be 1
System.out.println(transactionMap.get(t1)); // Should print "Updated transaction"PaymentProcessor.process(pm1, 50.0);
PaymentProcessor.process(pm2, 75.0, "Online purchase");
Transaction t = new Transaction(pm3, 100.0, "Bill payment");
PaymentProcessor.process(t);CreditCard card = new CreditCard("Grace", "5555-6666-7777-8888", "234", "11/27");
DigitalWallet wallet2 = new DigitalWallet("Henry", 500.0, "henry@email.com", "555-9999");

// CreditCard uses default 2% service fee
double amountWithFee1 = card.applyServiceFee(100.0);
System.out.println("Credit card fee: " + amountWithFee1); // 102.0

// DigitalWallet overrides to use 1.5% service fee
double amountWithFee2 = wallet2.applyServiceFee(100.0);
System.out.println("Digital wallet fee: " + amountWithFee2); // 101.5
}
}
