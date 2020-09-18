package jung.yun.kim.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class MenuItemRespositoryImpl implements MenuItemRespository {

    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    public MenuItemRespositoryImpl() {
        menuItems.add(new MenuItem("Kimchi"));
    }

    @Override
    public List<MenuItem> findAllByRestaurantId(Long restaurantId) {
        return menuItems;
    }
}
