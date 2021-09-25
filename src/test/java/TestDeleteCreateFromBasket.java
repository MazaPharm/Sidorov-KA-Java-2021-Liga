import org.junit.Test;
import org.mockito.Mock;
import domain.store.Products;
import domain.store.Basket;
import domain.User;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;

import static org.junit.Assert.assertTrue;

public class TestDeleteCreateFromBasket {

    private Basket basket = new Basket();

    @Mock
    User user;

    @Test(expected = IndexOutOfBoundsException.class)
    public void testDeleteFromBasket() {
        Products products = new Products();
        products.setProducts();
        basket.initializationList();
        basket.addProducts(5, products);
        basket.delete(6);
    }

    @Test
    public void emptyBasketTest() throws IOException {
        ByteArrayOutputStream bo = new ByteArrayOutputStream();
        System.setOut(new PrintStream(bo));
        basket.initializationList();
        basket.createOrderFromBasket(user);
        bo.flush();
        String allWrittenLines = new String(bo.toByteArray());
        assertTrue(allWrittenLines.contains("Для создания заказа добавьте товары в корзину\n"));
    }
}

