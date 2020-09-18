package jung.yun.kim.eatgo.domain;

import java.util.List;

public interface RestaurantRespository {
    List<Restaurant> findAll();

    Restaurant findById(Long id);
}
