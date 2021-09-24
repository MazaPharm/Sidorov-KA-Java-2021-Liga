import org.junit.Test;
import org.mockito.Mock;
import store.builOrder.Basket;
import user.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class TestDeleteCreateFromBasket {

    private Basket basket = new Basket();

    @Mock
    User user;

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteFromBasket(){
        basket.addProducts(5);
        basket.delete(6);
    }

    @Test
    public void emptyBasketTest() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        basket.createOrderFromBasket(user);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        assertTrue(allWrittenLines.contains("Для создания заказа добавьте товары в корзину\n"));
    }
}

