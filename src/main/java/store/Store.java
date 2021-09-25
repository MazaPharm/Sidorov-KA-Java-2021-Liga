package store;

import user.ChangeUserInfo;
import user.User;

public interface Store {

    void addProductsInBasket(int i);

    void deleteFromBasket(int i);

    void newOrder(User user);

    ChangeUserInfo changePersonalData(User user);


}
