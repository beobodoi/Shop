import java.util.ArrayList;
import java.util.List;

public class Customer {
private String customerId;
private String name;
private String email;
private List<PaymentMethod> paymentMethods;
public Customer(String customerId, String name, String email){
    this.customerId=customerId;
    this.name=name;
    this.email=email;
    this.paymentMethods= new ArrayList<>();
}
public void addPaymentMethod(PaymentMethod pm){
    paymentMethods.add(pm);
}
public boolean removePaymentMethod(String paymentId){
    return paymentMethods.removeIf(pm -> pm.getPaymentId().equals(paymentId));
}
public PaymentMethod getPreferredPaymentMethod(){
    return paymentMethods.isEmpty() ? null : paymentMethods.get(0);
}
public void displayAllPaymentMethods() {
    System.out.println("PM for " + name);

    if (paymentMethods.isEmpty()) {
        System.out.println("None");
    } else {
        for (PaymentMethod pm : paymentMethods) {
            System.out.println(pm);
        }
    }
}


    public String getCustomerId() {
        return customerId;
    }

    public void setCustomerId(String customerId) {
        this.customerId = customerId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public List<PaymentMethod> getPaymentMethods() {
        return paymentMethods;
    }

    public void setPaymentMethods(List<PaymentMethod> paymentMethods) {
        this.paymentMethods = paymentMethods;
    }
}
