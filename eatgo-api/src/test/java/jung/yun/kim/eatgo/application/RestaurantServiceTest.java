package jung.yun.kim.eatgo.application;

import jung.yun.kim.eatgo.domain.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.*;
import static org.mockito.ArgumentMatchers.*;
import static org.hamcrest.MatcherAssert.*;
import static org.mockito.BDDMockito.*;

class RestaurantServiceTest {

    private RestaurantService restaurantService;

    @Mock
    private RestaurantRespository restaurantRepository;

    @Mock
    private MenuItemRespository menuItemRespository;

    private void mockRestaurantRespository() {
        List<Restaurant> restaurants = new ArrayList<>();

        Restaurant restaurant = new Restaurant(1005L, "Bob zip", "Seoul");

        restaurants.add(new Restaurant(1005L, "Bob zip", "Seoul"));

        given(restaurantRepository.findAll()).willReturn(restaurants);
        given(restaurantRepository.findById(1005L)).willReturn(restaurant);

    }

    private void mockMenuItemRespository() {
        List<MenuItem> menuItems = new ArrayList<>();

        menuItems.add(new MenuItem("Kimchi"));

        given(menuItemRespository.findAllByRestaurantId(1005L)).willReturn(menuItems);
    }

    /* 테스트에서는 자동으로 의존성 주입이 되지 않아 의존성을 알려주어야 한다.
    *
    * @BeforeEach 모든 테스트가 실행되기 전에 한번씩 실행된다. */

    @BeforeEach
    public void setUp(){
        /* @Mock 붙은 객체로 초기화 해준다. */
        MockitoAnnotations.initMocks(this);

        mockRestaurantRespository();
        mockMenuItemRespository();

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

    @Test
    public void addRestaurant(){
        Restaurant restaurant = restaurantService.addRestaurant(new Restaurant("BeRyong", "Busan"));
        Restaurant saved = new Restaurant(1L, "BeRyong", "Busan");

        given(restaurantRepository.save(any())).willReturn(saved);

        assertThat(restaurant.getId(), is(1L));
    }

}