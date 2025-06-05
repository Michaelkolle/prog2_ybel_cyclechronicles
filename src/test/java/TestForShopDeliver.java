import cyclechronicles.Order;
import cyclechronicles.Shop;
import cyclechronicles.Type;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;


public class TestForShopDeliver {

    Shop shop;
    Order order;

    @BeforeEach
    public void setup(){
        shop = mock(Shop.class);
        order = mock(Order.class);
    }

    @Test
    public void testDeliverOnNoOrder(){
        when(shop.deliver("")).thenReturn(Optional.empty());
        assertEquals(Optional.empty(), shop.deliver(""));
    }

    @Test
    public void testDeliverOrder(){
       when(order.bike()).thenReturn(Type.RACE);
       when(order.customerName()).thenReturn("first");
       when(shop.repair()).thenReturn(Optional.of(order));
       when(shop.deliver("first")).thenReturn(Optional.of(order));
       assertEquals(Optional.of(order), shop.deliver("first"));
    }
    @Test
    public void testOnMoreDeliveries(){
        when(order.bike()).thenReturn(Type.RACE);
        when(order.customerName()).thenReturn("first");
        when(shop.repair()).thenReturn(Optional.of(order));
        when(shop.deliver("first")).thenReturn(Optional.of(order));
        shop.accept(order);
        shop.repair();
        Order order2 = mock(Order.class);
        when(order2.bike()).thenReturn(Type.RACE);
        when(order2.customerName()).thenReturn("second");
        when(shop.repair()).thenReturn(Optional.of(order2));
        shop.accept(order2);
        shop.repair();
        Order order3 = mock(Order.class);
        when(order3.bike()).thenReturn(Type.RACE);
        when(order3.customerName()).thenReturn("second");
        when(shop.repair()).thenReturn(Optional.of(order3));
        shop.accept(order3);
        shop.repair();

        assertEquals(Optional.of(order), shop.deliver("first"));
    }

}
