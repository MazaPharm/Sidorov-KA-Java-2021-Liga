package domain.store;

import domain.Errors;
import domain.User;
import service.AccessChangeBasket;
import service.CheckValidUserData;

import java.util.List;

public class Order {

    private User user;

    public Order(User user) {
        this.user = user;
    }

    /**
     * \
     * Печатает список товаров из корзины если у пользователя данные валидны
     * если нет, то печатает сообщение, что заказ не создан.
     * При этом в корзину снова можно добавить товары, так кк заказ не создался
     *
     * @param productsList лист заказов из корзины
     */
    public void createOrder(List<String> productsList, AccessChangeBasket accessChangeBasket) {
        CheckValidUserData checkValidUserData = new CheckValidUserData(user);
        if (checkValidUserData.isValidDAta()) {
            System.out.println("Ваш заказ:");
            productsList.stream().forEach(System.out::println);
            getUserInfo();
        } else {
            System.out.print(Errors.invalidUserData());
            accessChangeBasket.setAccessToChange(true);
        }
    }

    /**
     * информация по пользователю
     */
    private void getUserInfo() {
        System.out.println("информация о получателе:");
        System.out.println(String.format("имя: %s \nТелефон: %s \nАдресс: %s",
                user.getName(),
                user.getPhoneNumber(),
                user.getAddress()));
    }


}
