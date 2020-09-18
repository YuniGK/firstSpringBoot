package jung.yun.kim.eatgo.application;

import jung.yun.kim.eatgo.domain.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

class RespositoryServiceTest {

    private RestaurantService restaurantService;

    private RestaurantRespository restaurantRepository;

    private MenuItemRespository menuItemRespository;

    /* 테스트에서는 자동으로 의존성 주입이 되지 않아 의존성을 알려주어야 한다.
    *
    * @BeforeEach 모든 테스트가 실행되기 전에 한번씩 실행된다. */
    @BeforeEach
    public void setUp(){
        restaurantRepository = new RestaurantRespositoryaImpl();

        menuItemRespository = new MenuItemRespositoryImpl();

        restaurantService = new RestaurantService(restaurantRepository, menuItemRespository);
    }

    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();

        //목록 중 첫번째 항목
        Restaurant restaurant = restaurants.get(0);

        assertThat(restaurant.getId(), is(1005L));
    }

    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1005L);

        //메뉴 중 첫번째 항목을 가지고 온다.
        MenuItem menuItem = restaurant.getMenuItems().get(0);

        assertThat(restaurant.getId(), is(1005L));
        assertThat(menuItem.getMenuItem(), is("Kimchi"));
    }

}