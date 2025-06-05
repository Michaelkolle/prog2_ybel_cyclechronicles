package cyclechronicles;

/** An order for a bike shop. */
public class Order {
    Type biketype;
    String customerName;

    public Order(Type bike, String name){
        biketype = bike;
        customerName = name;
    }
    /**
     * Determine the type of bike to be repaired.
     *
     * @return type of bicycle
     */
    public Type getBicycleType() {
       return this.biketype;
    }

    /**
     * Determine the customer who placed this order.
     *
     * @return name of customer
     */
    public String getCustomer() {
        return this.customerName;
    }
}
