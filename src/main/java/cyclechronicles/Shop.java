package cyclechronicles;

import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Shop {
    private final Queue<Order> pendingOrders = new LinkedList<>();
    private final Set<Order> completedOrders = new HashSet<>();
    private final Logger logger = ShopLogger.getLogger();

    public boolean accept(Order o) {
        if (o.getBicycleType() == Type.GRAVEL || o.getBicycleType() == Type.EBIKE) return false;
        if (pendingOrders.stream().anyMatch(x -> x.getCustomer().equals(o.getCustomer()))) return false;
        if (pendingOrders.size() >= 5) return false;

        boolean added = pendingOrders.add(o);
        if (added) {
            logChange(Level.INFO, "accept", o, "pendingOrders");
        }
        return added;
    }

    public Optional<Order> repair() {
        Order order = pendingOrders.poll();
        if (order == null) return Optional.empty();

        logChange(Level.INFO, "repair", order, "pendingOrders");

        completedOrders.add(order);
        logChange(Level.INFO, "repair", order, "completedOrders");

        return Optional.of(order);
    }

    public Optional<Order> deliver(String customer) {
        for (Order order : completedOrders) {
            if (order.getCustomer().equals(customer)) {
                completedOrders.remove(order);
                logChange(Level.INFO, "deliver", order, "completedOrders");
                return Optional.of(order);
            }
        }
        return Optional.empty();
    }

    private void logChange(Level level, String methodName, Order order, String collectionName) {
        logger.logp(level, this.getClass().getName(), methodName,
            String.format("%s,%s,%s", order.getBicycleType(), order.getCustomer(), collectionName));
    }
}
