package store.builOrder;


/**
 * Класс устанавливает флаг для доступа к изменению корзины
 */
public class AccessChangeBasket {

    private boolean accessToChange = true;

    public boolean getAccess() {
        return accessToChange;
    }

    public void setAccessToChange(boolean accessToChange) {
        this.accessToChange = accessToChange;
    }
}
