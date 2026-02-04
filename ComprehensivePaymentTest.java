import java.util.*;

public class ComprehensivePaymentTest {
    public static void main(String[] args) {
        System.out.println("========================================");
        System.out.println("   PAYMENT SYSTEM COMPREHENSIVE TEST   ");
        System.out.println("========================================\n");
        
        // ============================================
        // TEST 1: CONSTRUCTORS (Multiple Constructors)
        // ============================================
        System.out.println(">>> TEST 1: TESTING CONSTRUCTORS <<<\n");
        
        // CreditCard - 3 different constructors
        CreditCard cc1 = new CreditCard(); // Default constructor
        CreditCard cc2 = new CreditCard("Alice Johnson", "1234-5678-9012-3456", "123", "12/25");
        CreditCard cc3 = new CreditCard("Bob Smith", "9876-5432-1098-7654", "456", "06/28", 5000.0);
        
        System.out.println("CreditCard 1 (Default): " + cc1);
        System.out.println("CreditCard 2 (No balance): " + cc2);
        System.out.println("CreditCard 3 (With balance): " + cc3);
        
        // DigitalWallet - 3 different constructors
        DigitalWallet dw1 = new DigitalWallet("Charlie Brown", "charlie@email.com");
        DigitalWallet dw2 = new DigitalWallet("Diana Prince", "diana@email.com", "555-1234");
        DigitalWallet dw3 = new DigitalWallet("Eve Adams", 1000.0, "eve@email.com", "555-5678");
        
        System.out.println("\nDigitalWallet 1 (Email only): " + dw1.getPaymentDetails());
        System.out.println("DigitalWallet 2 (Email + Phone): " + dw2.getPaymentDetails());
        System.out.println("DigitalWallet 3 (Full): " + dw3.getPaymentDetails());
        
        // BankTransfer
        BankTransfer bt1 = new BankTransfer("Frank Wilson", 2000.0, "12345678", "Chase Bank", "123456789");
        System.out.println("\nBankTransfer: " + bt1.getPaymentDetails());
        
        // ============================================
        // TEST 2: POLYMORPHISM (Parent Reference, Child Object)
        // ============================================
        System.out.println("\n\n>>> TEST 2: TESTING POLYMORPHISM <<<\n");
        
        PaymentMethod pm1 = new CreditCard("Grace Lee", "1111-2222-3333-4444", "789", "03/27", 3000.0);
        PaymentMethod pm2 = new DigitalWallet("Henry Ford", 1500.0, "henry@email.com", "555-9999");
        PaymentMethod pm3 = new BankTransfer("Ivy Chen", 5000.0, "98765432", "Bank of America", "987654321");
        
        PaymentMethod[] allPayments = {pm1, pm2, pm3};
        
        System.out.println("Testing polymorphic behavior:");
        for (PaymentMethod pm : allPayments) {
            System.out.println("Type: " + pm.getPaymentType());
            System.out.println("Valid: " + pm.validate());
            System.out.println("Owner: " + pm.getOwnerName());
            pm.displayBalance();
            System.out.println("---");
        }
        
        // ============================================
        // TEST 3: INTERFACE SEGREGATION
        // ============================================
        System.out.println("\n>>> TEST 3: TESTING INTERFACES <<<\n");
        
        // CreditCard implements BOTH Payable and Refundable
        CreditCard cc4 = new CreditCard("Jack Black", "5555-6666-7777-8888", "234", "11/27", 2000.0);
        Payable payable1 = cc4;
        Refundable refundable1 = cc4;
        
        System.out.println("CreditCard as Payable:");
        System.out.println("Payment Details: " + payable1.getPaymentDetails());
        System.out.println("Service Fee for $100: $" + payable1.applyServiceFee(100.0));
        
        System.out.println("\nCreditCard as Refundable:");
        System.out.println("Refund Policy: " + refundable1.getRefundPolicy());
        
        // DigitalWallet implements ONLY Payable (NOT Refundable)
        DigitalWallet dw4 = new DigitalWallet("Kate Miller", 800.0, "kate@email.com", "555-1111");
        Payable payable2 = dw4; // ✅ OK
        // Refundable refundable2 = dw4; // ❌ Compilation Error!
        
        System.out.println("\nDigitalWallet as Payable:");
        System.out.println("Payment Details: " + payable2.getPaymentDetails());
        System.out.println("Service Fee for $100: $" + payable2.applyServiceFee(100.0));
        
        // BankTransfer implements BOTH
        BankTransfer bt2 = new BankTransfer("Liam Davis", 3000.0, "11223344", "Wells Fargo", "111222333");
        Payable payable3 = bt2;
        Refundable refundable2 = bt2;
        
        System.out.println("\nBankTransfer as Payable:");
        System.out.println("Payment Details: " + payable3.getPaymentDetails());
        
        System.out.println("\nBankTransfer as Refundable:");
        System.out.println("Refund Policy: " + refundable2.getRefundPolicy());
        
        // ============================================
        // TEST 4: PAYMENT PROCESSING
        // ============================================
        System.out.println("\n\n>>> TEST 4: TESTING PAYMENT PROCESSING <<<\n");
        
        CreditCard cc5 = new CreditCard("Mia Taylor", "2222-3333-4444-5555", "567", "09/26", 1000.0);
        System.out.println("Before payment - Balance: $" + cc5.getBalance());
        boolean success1 = cc5.processPayment(250.0);
        System.out.println("Payment $250.00: " + (success1 ? "SUCCESS" : "FAILED"));
        System.out.println("After payment - Balance: $" + cc5.getBalance());
        
        // Try insufficient funds
        boolean success2 = cc5.processPayment(10000.0);
        System.out.println("\nPayment $10,000.00: " + (success2 ? "SUCCESS" : "FAILED (Insufficient Funds)"));
        System.out.println("Balance unchanged: $" + cc5.getBalance());
        
        // ============================================
        // TEST 5: REFUND PROCESSING
        // ============================================
        System.out.println("\n\n>>> TEST 5: TESTING REFUND PROCESSING <<<\n");
        
        System.out.println("Before refund - Balance: $" + cc5.getBalance());
        boolean refundSuccess = cc5.refund(100.0);
        System.out.println("Refund $100.00: " + (refundSuccess ? "SUCCESS" : "FAILED"));
        System.out.println("After refund - Balance: $" + cc5.getBalance());
        
        BankTransfer bt3 = new BankTransfer("Noah King", 500.0, "55667788", "Citibank", "555666777");
        System.out.println("\nBankTransfer before refund: $" + bt3.getBalance());
        bt3.refund(200.0);
        System.out.println("BankTransfer after $200 refund: $" + bt3.getBalance());
        
        // ============================================
        // TEST 6: STATIC FIELDS AND METHODS
        // ============================================
        System.out.println("\n\n>>> TEST 6: TESTING STATIC MEMBERS <<<\n");
        
        // Create some payment methods to increase counter
        new CreditCard("Test1", "1111-1111-1111-1111", "111", "01/25", 100.0);
        new DigitalWallet("Test2", 200.0, "test2@email.com", "555-0000");
        new BankTransfer("Test3", 300.0, "12341234", "TestBank", "123123123");
        
        System.out.println("Total Payment Methods Created: " + PaymentMethod.getPaymentCount());
        
        // Test static method for BankTransfer
        System.out.println("\n--- Testing Bank Holiday Logic ---");
        BankTransfer bt4 = new BankTransfer("Olivia White", 1000.0, "99887766", "TD Bank", "999888777");
        
        BankTransfer.setBankHoliday(false);
        System.out.println("Bank Holiday: false");
        boolean paymentResult1 = bt4.processPayment(100.0);
        System.out.println("Payment attempt: " + (paymentResult1 ? "SUCCESS" : "FAILED"));
        System.out.println("Balance after: $" + bt4.getBalance());
        
        BankTransfer.setBankHoliday(true);
        System.out.println("\nBank Holiday: true");
        boolean paymentResult2 = bt4.processPayment(100.0);
        System.out.println("Payment attempt: " + (paymentResult2 ? "SUCCESS" : "FAILED (Bank Holiday!)"));
        System.out.println("Balance unchanged: $" + bt4.getBalance());
        
        // Reset for further testing
        BankTransfer.setBankHoliday(false);
        
        // ============================================
        // TEST 7: TRANSACTION CLASS
        // ============================================
        System.out.println("\n\n>>> TEST 7: TESTING TRANSACTIONS <<<\n");
        
        Transaction t1 = new Transaction(pm1, 500.0, "Online Purchase - Electronics");
        Transaction t2 = new Transaction(pm2, 150.0, "Subscription Fee");
        Transaction t3 = new Transaction(pm3, 1000.0, "Bill Payment");
        
        System.out.println("Transaction 1: " + t1);
        System.out.println("Transaction 2: " + t2);
        System.out.println("Transaction 3: " + t3);
        
        System.out.println("\nTotal Transactions Created: " + Transaction.getTotalTransactions());
        
        // ============================================
        // TEST 8: EQUALS AND HASHCODE (HashSet/HashMap)
        // ============================================
        System.out.println("\n\n>>> TEST 8: TESTING EQUALS/HASHCODE <<<\n");
        
        Set<Transaction> transactionSet = new HashSet<>();
        
        Transaction tx1 = new Transaction(pm1, 100.0, "Purchase 1");
        Transaction tx2 = new Transaction(pm2, 200.0, "Purchase 2");
        Transaction tx3 = new Transaction(pm1, 100.0, "Purchase 1"); // Different object, same ID potentially
        
        transactionSet.add(tx1);
        transactionSet.add(tx2);
        transactionSet.add(tx3);
        
        System.out.println("Added 3 transactions to HashSet");
        System.out.println("HashSet size: " + transactionSet.size());
        System.out.println("(Should be 3 if Transaction IDs are unique, or less if equals() considers them same)");
        
        // HashMap test
        Map<Transaction, String> transactionMap = new HashMap<>();
        transactionMap.put(tx1, "First transaction");
        transactionMap.put(tx2, "Second transaction");
        transactionMap.put(tx3, "Third transaction");
        
        System.out.println("\nHashMap size: " + transactionMap.size());
        System.out.println("Value for tx1: " + transactionMap.get(tx1));
        
        // ============================================
        // TEST 9: PAYMENT PROCESSOR
        // ============================================
        System.out.println("\n\n>>> TEST 9: TESTING PAYMENT PROCESSOR <<<\n");
        
        CreditCard cc6 = new CreditCard("Peter Parker", "6666-7777-8888-9999", "999", "12/29", 2000.0);
        DigitalWallet dw5 = new DigitalWallet("Quinn Ross", 1500.0, "quinn@email.com", "555-2222");
        BankTransfer bt5 = new BankTransfer("Rachel Green", 3000.0, "44556677", "Capital One", "444555666");
        
        System.out.println("--- Method 1: process(PaymentMethod, amount) ---");
        boolean result1 = PaymentProcessor.process(cc6, 500.0);
        System.out.println("Process $500 from CreditCard: " + (result1 ? "SUCCESS" : "FAILED"));
        
        System.out.println("\n--- Method 2: process(PaymentMethod, amount, description) ---");
        boolean result2 = PaymentProcessor.process(dw5, 300.0, "Monthly Subscription");
        System.out.println("Process $300 from Wallet with description: " + (result2 ? "SUCCESS" : "FAILED"));
        
        System.out.println("\n--- Method 3: process(Transaction) ---");
        Transaction tx4 = new Transaction(bt5, 750.0, "Utility Bill");
        boolean result3 = PaymentProcessor.process(tx4);
        System.out.println("Process transaction: " + (result3 ? "SUCCESS" : "FAILED"));
        
        System.out.println("\n--- Method 4: executePayment(Payable, amount) ---");
        PaymentProcessor.executePayment(cc6, 250.0);
        
        System.out.println("\n--- Method 5: executeRefund(Refundable, amount) ---");
        PaymentProcessor.executeRefund(cc6, 100.0);
        
        // ============================================
        // TEST 10: CUSTOMER CLASS
        // ============================================
        System.out.println("\n\n>>> TEST 10: TESTING CUSTOMER CLASS <<<\n");
        
        Customer customer1 = new Customer("CUST001", "Sarah Connor", "sarah@skynet.com");
        
        CreditCard customerCC = new CreditCard("Sarah Connor", "7777-8888-9999-0000", "777", "06/27", 5000.0);
        DigitalWallet customerDW = new DigitalWallet("Sarah Connor", 2000.0, "sarah@skynet.com", "555-3333");
        BankTransfer customerBT = new BankTransfer("Sarah Connor", 10000.0, "88889999", "HSBC", "888999000");
        
        customer1.addPaymentMethod(customerCC);
        customer1.addPaymentMethod(customerDW);
        customer1.addPaymentMethod(customerBT);
        
        System.out.println("Customer: " + customer1.getName());
        System.out.println("Email: " + customer1.getEmail());
        
        System.out.println("\n--- All Payment Methods ---");
        customer1.displayAllPaymentMethods();
        
        System.out.println("\n--- Preferred Payment Method ---");
        PaymentMethod preferred = customer1.getPreferredPaymentMethod();
        System.out.println("Preferred: " + (preferred != null ? preferred.getPaymentType() : "None"));
        
        System.out.println("\n--- Remove Payment Method ---");
        String idToRemove = customerDW.getPaymentId();
        boolean removed = customer1.removePaymentMethod(idToRemove);
        System.out.println("Removed " + idToRemove + ": " + removed);
        
        System.out.println("\n--- Payment Methods After Removal ---");
        customer1.displayAllPaymentMethods();
        
        // ============================================
        // TEST 11: VALIDATION LOGIC
        // ============================================
        System.out.println("\n\n>>> TEST 11: TESTING VALIDATION <<<\n");
        
        // Invalid CreditCard (CVV wrong)
        CreditCard invalidCC = new CreditCard("Tom Hanks", "1234-5678-9012-3456", "12", "12/25", 1000.0);
        System.out.println("CreditCard with CVV '12' (invalid): " + invalidCC.validate());
        
        // Valid CreditCard
        CreditCard validCC = new CreditCard("Uma Thurman", "1234-5678-9012-3456", "123", "12/25", 1000.0);
        System.out.println("CreditCard with CVV '123' (valid): " + validCC.validate());
        
        // Invalid BankTransfer (account number too short)
        BankTransfer invalidBT = new BankTransfer("Victor Hugo", 1000.0, "1234", "TestBank", "123456789");
        System.out.println("\nBankTransfer with account '1234' (invalid): " + invalidBT.validate());
        
        // Valid BankTransfer
        BankTransfer validBT = new BankTransfer("Wendy Williams", 1000.0, "12345678", "TestBank", "123456789");
        System.out.println("BankTransfer with account '12345678' (valid): " + validBT.validate());
        
        // Invalid DigitalWallet (no @ in email)
        DigitalWallet invalidDW = new DigitalWallet("Xavier Smith", 500.0, "notanemail", "555-4444");
        System.out.println("\nDigitalWallet with email 'notanemail' (invalid): " + invalidDW.validate());
        
        // Valid DigitalWallet
        DigitalWallet validDW = new DigitalWallet("Yara Jones", 500.0, "yara@email.com", "555-5555");
        System.out.println("DigitalWallet with email 'yara@email.com' (valid): " + validDW.validate());
        
        // ============================================
        // TEST 12: SERVICE FEES (Default vs Override)
        // ============================================
        System.out.println("\n\n>>> TEST 12: TESTING SERVICE FEES <<<\n");
        
        CreditCard feeCC = new CreditCard("Zoe Anderson", "9999-8888-7777-6666", "888", "08/28", 1000.0);
        DigitalWallet feeDW = new DigitalWallet("Adam West", 800.0, "adam@email.com", "555-6666");
        
        double amount = 1000.0;
        
        System.out.println("Base Amount: $" + amount);
        System.out.println("\nCreditCard Service Fee (default 2%): $" + feeCC.applyServiceFee(amount));
        System.out.println("DigitalWallet Service Fee (override 1.5%): $" + feeDW.applyServiceFee(amount));
        
        // ============================================
        // TEST 13: EDGE CASES
        // ============================================
        System.out.println("\n\n>>> TEST 13: TESTING EDGE CASES <<<\n");
        
        // Payment with exactly available balance
        CreditCard edgeCC = new CreditCard("Betty White", "1111-1111-1111-1111", "111", "01/26", 500.0);
        System.out.println("Balance: $" + edgeCC.getBalance());
        boolean exactPayment = edgeCC.processPayment(500.0);
        System.out.println("Payment of exact balance ($500): " + (exactPayment ? "SUCCESS" : "FAILED"));
        System.out.println("Balance after: $" + edgeCC.getBalance());
        
        // Payment with $0.01 over balance
        CreditCard edgeCC2 = new CreditCard("Carl Sagan", "2222-2222-2222-2222", "222", "02/27", 100.0);
        System.out.println("\nBalance: $" + edgeCC2.getBalance());
        boolean overPayment = edgeCC2.processPayment(100.01);
        System.out.println("Payment $0.01 over balance: " + (overPayment ? "SUCCESS" : "FAILED (Should fail)"));
        System.out.println("Balance unchanged: $" + edgeCC2.getBalance());
        
        // Customer with no payment methods
        Customer emptyCustomer = new Customer("CUST002", "Empty User", "empty@test.com");
        System.out.println("\nCustomer with no payment methods:");
        PaymentMethod noMethod = emptyCustomer.getPreferredPaymentMethod();
        System.out.println("Preferred payment: " + (noMethod == null ? "NULL (correct)" : "ERROR"));
        
        // ============================================
        // FINAL SUMMARY
        // ============================================
        System.out.println("\n\n========================================");
        System.out.println("         TEST SUMMARY");
        System.out.println("========================================");
        System.out.println("Total Payment Methods Created: " + PaymentMethod.getPaymentCount());
        System.out.println("Total Transactions Created: " + Transaction.getTotalTransactions());
        System.out.println("\n✅ ALL TESTS COMPLETED!");
        System.out.println("========================================\n");
    }
}