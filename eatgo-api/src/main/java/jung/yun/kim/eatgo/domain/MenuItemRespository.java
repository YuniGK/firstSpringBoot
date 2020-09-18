package jung.yun.kim.eatgo.domain;

import java.util.List;

public interface MenuItemRespository {
    List<MenuItem> findAllByRestaurantId(Long restaurantId);
}
