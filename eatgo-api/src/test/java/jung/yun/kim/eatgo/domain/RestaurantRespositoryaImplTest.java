package jung.yun.kim.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.*;

class RestaurantRespositoryaImplTest {

    @Test
    public void save(){
        RestaurantRespository respository = new RestaurantRespositoryaImpl();

        int oldCount = respository.findAll().size();

        Restaurant restaurant = new Restaurant("BeRyong", "Busan");
        respository.save(restaurant);

        assertThat(restaurant.getId(), is(1L));

        int newCount = respository.findAll().size();

        assertThat(newCount - oldCount, is(1));

    }

}