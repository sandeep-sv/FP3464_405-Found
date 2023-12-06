import java.util.ArrayList;

class Receipt {
    private ArrayList<OrderItem> orderItems;
    private double total;
    private double customerAmount;
    private double change;

    public Receipt(ArrayList<OrderItem> orderItems, double total,double customerAmount,double change) {
        this.orderItems = orderItems;
        this.total = total;
        this.customerAmount=customerAmount;
        this.change=change;
    }

    public ArrayList<OrderItem> getOrderItems() {
        return orderItems;
    }

    public double getTotal() {
        return total;
    }

    public double getChange() {
        return change;
    }

    public double getCustomerAmount() {
        return customerAmount;
    }

    public void setChange(double change) {
        this.change = change;
    }

    public void setCustomerAmount(double customerAmount) {
        this.customerAmount = customerAmount;
    }
}