import domain.store.ElectronicsShop;
import domain.store.Products;
import domain.store.Store;
import domain.store.Basket;
import domain.User;

public class Main {
    public static void main(String[] args) {
        User user = new User("Kirill", "+79998887766", "Peterhof");
        storeOptions(user);
    }

    /**
     * Метод реализует
     * добавления товара в корзину(указывается сколько товваров),
     * удаление из корзины,
     * оформдения заказа,
     * смена пероснальных данных
     */
    public static void storeOptions(User user) {
        Store electronicsShop = new ElectronicsShop(new Products(), new Basket());
        electronicsShop.addProductsInBasket(5);

        //при смене количества товаро сменить диапозон
        //Math.random()
        electronicsShop.deleteFromBasket((int) (Math.random() * 5));
        electronicsShop.newOrder(user);

        //если заказ был успешно создан товары больше не добавятся
        electronicsShop.addProductsInBasket(3);

        //замена информации у пользователя
//        electronicsShop.changePersonalData(user).changeUserName("Llirik");
//        electronicsShop.changePersonalData(user).changeUserAddress("Saint-Petesburg");
//        electronicsShop.changePersonalData(user).changeUserPhone("+79991112233");

    }

}
