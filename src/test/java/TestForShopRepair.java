import cyclechronicles.Order;
import cyclechronicles.Shop;
import cyclechronicles.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assumptions.assumeTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class TestForShopRepair {
    Shop shop;
    Order order;

    @BeforeEach
    public void setup(){
        shop = mock(Shop.class);
        order = mock(Order.class);
    }

    @Test
    public void testRepairOnEmpty(){
        when(shop.repair()).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), shop.repair());

    }
    @Test
    public void testRepair(){
        when(order.bike()).thenReturn(Type.RACE);
        when(order.customerName()).thenReturn("firstTest");
        assumeTrue(shop.accept(order));
        when(shop.repair()).thenReturn(Optional.of(order));
        assertEquals(Optional.of(order), shop.repair());
    }
    @Test
    public void testRepairOnMoreOrders(){
        Order order2 = mock(Order.class);
        Order order3 = mock(Order.class);

        when(order.bike()).thenReturn(Type.RACE);
        when(order.customerName()).thenReturn("first");
        when(shop.repair()).thenReturn(Optional.of(order));
        shop.accept(order);

        when(order2.bike()).thenReturn(Type.RACE);
        when(order2.customerName()).thenReturn("second");
        when(shop.repair()).thenReturn(Optional.of(order));
        shop.accept(order2);

        when(order3.bike()).thenReturn(Type.RACE);
        when(order3.customerName()).thenReturn("third");
        when(shop.repair()).thenReturn(Optional.of(order));
        shop.accept(order3);

        assertEquals("first", shop.repair().get().customerName());
    }
}
