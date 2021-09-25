package service;

import domain.User;


/**
 * Класс предоставляет возможность изменить онформацию пользователя
 */
public class ChangeUserInfo {
    private User user;


    public ChangeUserInfo(User user) {
        this.user = user;
    }

    public void changeUserName(String name) {
        user.setName(name);
    }

    public void changeUserPhone(String phone) {
        user.setPhoneNumber(phone);
    }

    public void changeUserAddress(String address) {
        user.setAddress(address);
    }
}
