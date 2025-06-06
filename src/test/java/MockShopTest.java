import cyclechronicles.Order;
import cyclechronicles.Shop;
import cyclechronicles.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class MockShopTest {
    private Shop shop;
    private Order order;

    @BeforeEach
    public void setup() {
        shop = new Shop();
        order = mock(Order.class);
    }

    @Test
    public void testNotAccseptEBike(){
        when(order.getBicycleType()).thenReturn(Type.EBIKE);
        when(order.getCustomer()).thenReturn("");
        assertFalse(shop.accept(order));
    }

    @Test
    public void testNotAccseptGravelBike(){
        when(order.getBicycleType()).thenReturn(Type.GRAVEL);
        when(order.getCustomer()).thenReturn("");
        assertFalse(shop.accept(order));
    }

    @Test
    public void testMoreThenOneOrderBySameCunstomer(){
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("First");
        assumeTrue(shop.accept(order));
        when(order.getBicycleType()).thenReturn(Type.SINGLE_SPEED);
        when(order.getCustomer()).thenReturn("First");
        assertFalse(shop.accept(order));
    }

    @Test
    public void testMoreThenFiveOrders(){
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("first");
        assumeTrue(order.getBicycleType() == Type.RACE);
        assumeTrue(order.getCustomer().equals("first"));

        shop.accept(order);

        when(order.getBicycleType()).thenReturn(Type.FIXIE);
        when(order.getCustomer()).thenReturn("second");
        assumeTrue(order.getBicycleType() == Type.FIXIE);
        assumeTrue(order.getCustomer().equals("second"));

        shop.accept(order);

        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("third");
        assumeTrue(order.getBicycleType() == Type.RACE);
        assumeTrue(order.getCustomer().equals("third"));

        shop.accept(order);

        when(order.getBicycleType()).thenReturn(Type.FIXIE);
        when(order.getCustomer()).thenReturn("fourth");
        assumeTrue(order.getBicycleType() == Type.FIXIE);
        assumeTrue(order.getCustomer().equals("fourth"));

        shop.accept(order);

        when(order.getBicycleType()).thenReturn(Type.SINGLE_SPEED);
        when(order.getCustomer()).thenReturn("fifth");
        assumeTrue(order.getBicycleType() == Type.SINGLE_SPEED);
        assumeTrue(order.getCustomer().equals("fifth"));

        assertFalse(shop.accept(order));
    }

    @Test
    public void testAccsepedAnOrder(){
        when(order.getBicycleType()).thenReturn(Type.SINGLE_SPEED);
        when(order.getCustomer()).thenReturn("test");
        assumeTrue(order.getBicycleType() == Type.SINGLE_SPEED);
        assumeTrue(order.getCustomer().equals("test"));

        assertTrue(shop.accept(order));
    }
    @Test
    public void testAccsepedAnOrderRACE(){
        when(order.getBicycleType()).thenReturn(Type.RACE);
        when(order.getCustomer()).thenReturn("test");
        assumeTrue(order.getBicycleType() == Type.RACE);
        assumeTrue(order.getCustomer().equals("test"));

        assertTrue(shop.accept(order));
    }

}
