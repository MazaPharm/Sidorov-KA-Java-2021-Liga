import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import store.ElectronicsShop;
import store.Products;
import store.builOrder.Basket;
import user.ChangeUserInfo;
import user.User;

public class TestElectronicsShops {

    @Test
    public void testAddProducts() {
        Products products = new Products();
        Basket basket = Mockito.mock(Basket.class);
        ElectronicsShop electronicsShop = new ElectronicsShop(products, basket);
        ElectronicsShop electronicsShopspy = Mockito.spy(electronicsShop);
        electronicsShop.addProductsInBasket(1);
        Mockito.verify(basket, Mockito.times(1)).addProducts(1, products);

    }

    @Test
    public void testDeleteFromBasket() {
        Basket basket = Mockito.mock(Basket.class);
        ElectronicsShop electronicsShop = new ElectronicsShop(new Products(), basket);
        electronicsShop.deleteFromBasket(1);
        Mockito.verify(basket, Mockito.times(1)).delete(1);
    }

    @Test
    public void testNewOrder() {
        User user = Mockito.mock(User.class);
        Basket basket = Mockito.mock(Basket.class);
        ElectronicsShop electronicsShop = new ElectronicsShop(new Products(), basket);
        electronicsShop.newOrder(user);
        Mockito.verify(basket, Mockito.times(1)).createOrderFromBasket(user);
    }

}
