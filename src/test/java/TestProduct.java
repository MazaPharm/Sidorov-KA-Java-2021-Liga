import org.junit.Assert;
import org.junit.Test;
import store.Products;

public class TestProduct {

    @Test
    public void testGetProductMethod() {
        Products products = new Products();
        products.setProducts();
        String res = products.getProduct(0);
        Assert.assertEquals("Компьютер", res);
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testIndex() {
        Products products = new Products();
        products.setProducts();
        String res = products.getProduct(10);
    }
}
