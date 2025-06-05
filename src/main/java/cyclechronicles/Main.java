package cyclechronicles;

import java.util.logging.Level;

public class Main {
    public static void main(String[] args) {
        Shop shop = new Shop();

        ShopLogger.getLogger().setLevel(Level.INFO);

        Order o1 = new Order(Type.RACE, "first");
        Order o2 = new Order(Type.SINGLE_SPEED, "second");
        Order o3 = new Order(Type.EBIKE, "third"); // Wird abgelehnt

        shop.accept(o1);
        shop.accept(o2);
        shop.accept(o3);

        shop.repair();
        shop.deliver("Anna");

        ShopLogger.getLogger().setLevel(Level.OFF);

        shop.accept(new Order(Type.FIXIE, "Dave"));
    }
}
