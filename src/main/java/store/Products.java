package store;

import java.util.ArrayList;
import java.util.List;


public class Products {
    private final List<String> products;


    {
        products = new ArrayList<>();
        products.add("Компьютер");
        products.add("Телефон");
        products.add("Стиральная машина");
        products.add("Холодильник");
        products.add("Ноутбук");
        products.add("Кофеварка");
        products.add("Наушники");
        products.add("Видеокарта");
        products.add("Телевизор");
        products.add("Аксессуар");
    }

    /**
     * @param i индекс товара из списка товаров
     * @return товар
     */
    public String getProduct(int i) {
        return products.get(i);
    }
}
