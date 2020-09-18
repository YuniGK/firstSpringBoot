package jung.yun.kim.eatgo.application;

import jung.yun.kim.eatgo.domain.MenuItem;
import jung.yun.kim.eatgo.domain.MenuItemRespository;
import jung.yun.kim.eatgo.domain.Restaurant;
import jung.yun.kim.eatgo.domain.RestaurantRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RestaurantService {

    @Autowired
    RestaurantRespository restaurantRespository;

    @Autowired
    MenuItemRespository menuItemRespository;

    //생성자를 통한 빈 주입
    public RestaurantService(RestaurantRespository restaurantRespository, MenuItemRespository menuItemRespository) {
        this.restaurantRespository = restaurantRespository;
        this.menuItemRespository = menuItemRespository;
    }

    //메서드
    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRespository.findAll();

        return restaurants;
    }

    public Restaurant getRestaurant(Long id) {
        Restaurant restaurant = restaurantRespository.findById(id);

        List<MenuItem> menuItem = menuItemRespository.findAllByRestaurantId(id);

        restaurant.setMenuItems(menuItem);

        return restaurant;
    }

    public void addRestaurant(Restaurant restaurant) {

    }
}
