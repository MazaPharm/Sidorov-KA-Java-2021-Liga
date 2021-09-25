package store;

import store.builOrder.Basket;
import user.ChangeUserInfo;
import store.Store;
import user.User;

public class ElectronicsShop implements Store {

    private Basket basket;
    private Products products;

    public ElectronicsShop(Products products, Basket basket) {
        this.products = products;
        this.basket = basket;
        basket.initializationList();
    }

    /**
     * @param i какое количество товара надо добавить в корзину
     */
    @Override
    public void addProductsInBasket(int i) {
        basket.addProducts(i, products);
    }

    /**
     * @param i удалить товар по индексу
     */
    @Override
    public void deleteFromBasket(int i) {
        basket.delete(i);
    }

    /**
     * @param user пользователь которому создается заказ
     */
    @Override
    public void newOrder(User user) {
        basket.createOrderFromBasket(user);
    }

    /**
     * @param user пользователь, данные которого надо поменять
     * @return экземпляр класса для доступа к методам с заменой полей пользователя
     */
    @Override
    public ChangeUserInfo changePersonalData(User user) {
        return new ChangeUserInfo(user);
    }


}
