
import org.junit.Test;
import org.mockito.Mockito;
import service.AccessChangeBasket;
import domain.store.Basket;
import domain.store.Order;
import domain.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.List;

import static org.junit.Assert.assertTrue;

public class TestOrder {
    private Basket basket = new Basket();

    @Test
    public void testCreateOrderFromOrderClass() throws Exception {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        User user = new User("k", "123456789012", "email");
        List list = Mockito.mock(List.class);
        AccessChangeBasket accessChangeBasket = Mockito.mock(AccessChangeBasket.class);
        Order order = new Order(user);
        order.createOrder(list, accessChangeBasket);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        assertTrue(allWrittenLines.contains("Заказ не создан.Проверьте данные пользователя!\n"));

    }


}
