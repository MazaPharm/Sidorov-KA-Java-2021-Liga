package user;

import user.User;

/**
 * Проверяет информацию пользователя на валидность
 */
public class CheckValidUserData {

    private final int MIN_NAME_LENGTH = 2;
    private final int PHONE_NUMBER_LENGTH = 12;
    private User user;

    public CheckValidUserData(User user) {
        this.user = user;
    }

    public boolean isValidDAta() {
        return (checkName() && checkAddress() && checkPhoneNumber());

    }

    /**
     * @return true или false  имя удовлетворяет условию
     */
    private boolean checkName() {
        return (user.getName().length() >= MIN_NAME_LENGTH);

    }

    /**
     * @return true или false  поле адрес заполнено
     */
    private boolean checkAddress() {
        return (user.getAddress() != null);

    }

    /**
     * @return true или false длинна мобильного телефона удовлетворяет длине Российских номеров
     */
    private boolean checkPhoneNumber() {
        return (user.getPhoneNumber().length() == PHONE_NUMBER_LENGTH);

    }

}
