package domain.store;

import domain.User;
import service.ChangeUserInfo;

public class ElectronicsShop implements Store {

    private Basket basket;
    private Products products;

    public ElectronicsShop(Products products, Basket basket) {
        this.products = products;
        this.basket = basket;
        basket.initializationList();
    }

    @Override
    public void addProductsInBasket(int i) {
        basket.addProducts(i, products);
    }

    @Override
    public void deleteFromBasket(int i) {
        basket.delete(i);
    }

    @Override
    public void newOrder(User user) {
        basket.createOrderFromBasket(user);
    }

    @Override
    public ChangeUserInfo changePersonalData(User user) {
        return new ChangeUserInfo(user);
    }


}
