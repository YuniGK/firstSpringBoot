package jung.yun.kim.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

/* Component 스프링에서 관리를 시작한다. */
@Component
public class RestaurantRespositoryaImpl implements RestaurantRespository {

    /* 코드에서 중복된 내용을 정의한다. */
    private List<Restaurant> restaurants = new ArrayList<>();

    /* 생성자를 통해서 restaurants에 대입 */
    public RestaurantRespositoryaImpl() {
        restaurants.add(new Restaurant(1005L, "Bob zip", "Seoul"));
        restaurants.add(new Restaurant(2020L, "Cyber Food", "Seoul"));
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }

    @Override
    public Restaurant findById(Long id) {
        return restaurants.stream()
                .filter(r -> r.getId().equals(id))
                .findFirst()
                //.get();
                .orElse(null);
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        restaurant.setId(1L);

        restaurants.add(restaurant);

        return restaurant;
    }
}
