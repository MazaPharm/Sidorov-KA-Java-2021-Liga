import store.ElectronicsShop;
import store.Store;
import user.User;

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
        Store electronicsShop = new ElectronicsShop();
        electronicsShop.addProductsInBasket(5);
        //при смене количества товаро сменить диапозон
        //Math.random()
        electronicsShop.deleteFromBasket((int) (Math.random() * 5));
        electronicsShop.newOrder(user);
        electronicsShop.addProductsInBasket(3);//если заказ был успешно создан товары больше не добавятся
        //замена информации у пользователя
//        electronicsShop.changePersonalData(user).changeUserName("Llirik");
//        electronicsShop.changePersonalData(user).changeUserAddress("Saint-Petesburg");
//        electronicsShop.changePersonalData(user).changeUserPhone("+79991112233");

    }

}
