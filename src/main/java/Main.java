import domain.store.ElectronicStore;
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
        Store electronicsStore = new ElectronicStore(new Products(), new Basket());
        electronicsStore.addProductsInBasket(5);

        //при смене количества товаро сменить диапозон
        //Math.random()
        electronicsStore.deleteFromBasket((int) (Math.random() * 5));
        electronicsStore.newOrder(user);

        //если заказ был успешно создан товары больше не добавятся
        electronicsStore.addProductsInBasket(3);

        //замена информации у пользователя
//        electronicsShop.changePersonalData(user).changeUserName("Llirik");
//        electronicsShop.changePersonalData(user).changeUserAddress("Saint-Petesburg");
//        electronicsShop.changePersonalData(user).changeUserPhone("+79991112233");

    }

}
