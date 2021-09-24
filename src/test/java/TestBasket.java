import org.junit.*;
import store.builOrder.Basket;
import user.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestBasket {


    private Basket basket = new Basket();
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void addProducts() {
        User user = new User("Kirill", "123456789012", "email");
        basket.addProducts(3);
        basket.createOrderFromBasket(user);
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testChangeAfterCreateOrder() {
        basket.addProducts(3);
        Assert.assertEquals("Заказ был отправлен на создание" +
                ", в корзину больше ничего нельзя добавить и изменить\n", output.toString());
    }

    @After
    public void cleanUpStream() {
        System.setOut(null);
    }
}
