import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;
import domain.store.ElectronicStore;
import domain.store.Products;
import domain.store.Basket;
import domain.User;
import service.ChangeUserInfo;

import static org.hamcrest.core.IsInstanceOf.instanceOf;

public class TestElectronicStore {

    @Test
    public void testAddProducts() {
        Products products = new Products();
        Basket basket = Mockito.mock(Basket.class);
        ElectronicStore electronicStore = new ElectronicStore(products, basket);
        ElectronicStore electronicsShopspy = Mockito.spy(electronicStore);
        electronicStore.addProductsInBasket(1);
        Mockito.verify(basket, Mockito.times(1)).addProducts(1, products);

    }

    @Test
    public void testDeleteFromBasket() {
        Basket basket = Mockito.mock(Basket.class);
        ElectronicStore electronicStore = new ElectronicStore(new Products(), basket);
        electronicStore.deleteFromBasket(1);
        Mockito.verify(basket, Mockito.times(1)).delete(1);
    }

    @Test
    public void testNewOrder() {
        User user = Mockito.mock(User.class);
        Basket basket = Mockito.mock(Basket.class);
        ElectronicStore electronicStore = new ElectronicStore(new Products(), basket);
        electronicStore.newOrder(user);
        Mockito.verify(basket, Mockito.times(1)).createOrderFromBasket(user);
    }


    @Test
    public void testChangePersonalData(){
        ElectronicStore electronicStore = new ElectronicStore(new Products(), new Basket());
        User user = Mockito.mock(User.class);
        electronicStore.changePersonalData(user);
        Assert.assertThat(electronicStore.changePersonalData(user), instanceOf(ChangeUserInfo.class));
    }

}
