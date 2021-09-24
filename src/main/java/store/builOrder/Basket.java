package store.builOrder;

import store.Errors;
import store.Products;
import user.User;
import java.util.ArrayList;
import java.util.List;


public class Basket {

    private List<String> basketList;
    private final AccessChangeBasket accessChangeBasket;

    {
        basketList = new ArrayList<>();
        accessChangeBasket = new AccessChangeBasket();
    }

    /**
     * добавляет рандомные товары в корзину из Product
     *
     * @param countProducts число продуктов для добавления в корзину basketList
     */
    public void addProducts(int countProducts) {
        if (accessChangeBasket.getAccess()) {
            Products products = new Products();
            for (int i = 0; i < countProducts; i++) {
                basketList.add(products.getProduct((int) (Math.random() * 10)));
            }
        } else {
            System.out.print(Errors.orderCreated());
        }
    }

    /**
     * Перенаправляет пользователя в класс, который оформляет заказ
     * после вызова метода корзину изменять нельзя
     *
     * @param user получатель с полями для доставки
     */
    public void createOrderFromBasket(User user) {
        if (basketList.size() > 0) {
            accessChangeBasket.setAccessToChange(false);
            Order order = new Order(user);
            order.createOrder(basketList, accessChangeBasket);
        } else System.out.println(Errors.emptyBasket());
    }

    /**
     * удаляет товар по индексу из списка basketList
     *
     * @param i индекс товара для удаления
     */
    public void delete(int i) {
        if (basketList.size() > 0)
            basketList.remove(i);
    }


}
