import org.junit.*;
import domain.store.Products;
import domain.store.Basket;
import domain.User;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class TestBasket {

    Products products = new Products();
    private Basket basket = new Basket();
    private ByteArrayOutputStream output = new ByteArrayOutputStream();

    @Before
    public void addProducts() {
        products.setProducts();
        User user = new User("Kirill", "123456789012", "email");
        basket.initializationList();
        basket.addProducts(3, products);
        basket.createOrderFromBasket(user);
        System.setOut(new PrintStream(output));
    }

    @Test
    public void testChangeAfterCreateOrder() {
        basket.addProducts(3, products);
        Assert.assertEquals("Заказ был отправлен на создание" +
                ", в корзину больше ничего нельзя добавить и изменить\n", output.toString());
    }

    @After
    public void cleanUpStream() {
        System.setOut(null);
    }
}
