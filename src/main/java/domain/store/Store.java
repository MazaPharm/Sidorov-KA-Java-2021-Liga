package domain.store;

import domain.User;
import service.ChangeUserInfo;

public interface Store {

    /**
     * @param i какое количество товара надо добавить в корзину
     */
    void addProductsInBasket(int i);

    /**
     * @param i удалить товар по индексу
     */
    void deleteFromBasket(int i);

    /**
     * @param user пользователь которому создается заказ
     */
    void newOrder(User user);

    /**
     * @param user пользователь, данные которого надо поменять
     * @return экземпляр класса для доступа к методам с заменой полей пользователя
     */
    ChangeUserInfo changePersonalData(User user);


}
