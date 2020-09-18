package jung.yun.kim.eatgo.domain;

import org.junit.jupiter.api.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;

class RestaurantTests {
    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant(1005L,"Bob zip", "Seoul");

        /* assertThat(actual, is(expected));
        * “actual이 expected와 같다(= 실제 값이 예상하는 값과 같다)” */
        assertThat(restaurant.getId(), is(1005L));
        assertThat(restaurant.getName(), is("Bob zip"));
        assertThat(restaurant.getAddress(), is("Seoul"));
    }

    @Test
    public void information(){
        Restaurant restaurant = new Restaurant(1005L, "Bob zip", "Seoul");

        assertThat(restaurant.getInformation(), is("Bob zip in Seoul"));
    }

}