package jung.yun.kim.eatgo.interfaces;

import jung.yun.kim.eatgo.application.RestaurantService;
import jung.yun.kim.eatgo.domain.MenuItem;
import jung.yun.kim.eatgo.domain.Restaurant;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.core.StringContains.containsString;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/* @RunWith() → @ExtendWith()
* 반복적으로 실행되는 클래스나 메서드에 선언한다.
*
* 누구에게서 실행되는지를 알려준다.
* 스프링 확장 기능으로 스프링 연동 테스트를 실행할 수 있게 한다. */
@ExtendWith(SpringExtension.class)

/* @WebMvcTest 특정컨트롤러 내용을 테스트한다. */
@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {

    /* MockMvc 브라우저에서 요청과 응답을 의미하는 객체로서 Controller 테스테 사용을 용이하게 해주는 라이브러리이다.
    * 테스트에 필요한 기능만 가지는 가짜 객체를 만들어 서버에 배포하지 않고도 스프링 MVC 동작을 재현*/
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    /* @SpyBean - 원하는 객체의 주입이 가능하다.
    test의 경우 메모리에 데이터가 저장되어 서버를 다시 시작시 데이터가 사라진다. */

    @Test
    public void list() throws Exception {

        List<Restaurant> restaurants = new ArrayList<>();

        restaurants.add(new Restaurant(1005L, "Bob zip", "Seoul"));
        restaurants.add(new Restaurant(2020L, "Cyber Food", "Seoul"));

        /* restaurantService.getRestaurants()를 누군가 실행하면, restaurants을 반환한다.

         * given 해당 Mock Bean이 어떤 행동을 취하면 어떤 결과를 반환한다 선언 */
        given(restaurantService.getRestaurants()).willReturn(restaurants);

        /* perform() - 요청을 전송하는 역할
        *
        * get() - HTTP 메소드를 결정( get(), post(), put(), delete() )
        *         인자로는 경로를 보냄
        *
        * andExpect() - 응답을 검증하는 역할
        *
        * status() - 상태 코드
        *
        * isOk() - 200
        *
        * https://shinsunyoung.tistory.com/52 */
        mvc.perform(get("/restaurants"))
                /* 응답이 되는지 확인 */
                .andExpect(status().isOk())
                /* 응답 문자열 확인 */
                .andExpect(content().string(
                        containsString("\"id\":1005")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ));

    }

    @Test
    public void detail() throws Exception {

        Restaurant restaurant1 = new Restaurant(1005L, "Bob zip", "Seoul");

        restaurant1.addMenuItem(new MenuItem("Kimchi"));

        given(restaurantService.getRestaurant(1005L)).willReturn(restaurant1);

        Restaurant restaurant2 = new Restaurant(2020L, "Cyber Food", "Seoul");

        given(restaurantService.getRestaurant(2020L)).willReturn(restaurant2);

        mvc.perform(get("/restaurants/1005"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":1005")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Bob zip\"")
                ))
                .andExpect(content().string(
                        containsString("Kimchi")
                ));

        mvc.perform(get("/restaurants/2020"))
                .andExpect(status().isOk())
                .andExpect(content().string(
                        containsString("\"id\":2020")
                ))
                .andExpect(content().string(
                        containsString("\"name\":\"Cyber Food\"")
                ));
    }

    @Test
    public void create() throws Exception {
        mvc.perform(post("/restaurants")
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"name\":\"BeRyong\",\"address\":\"Busan\"}"))
                .andExpect(status().isCreated())
                .andExpect(header().string("location", "/restaurants/1"))
                .andExpect(content().string("{}"));

        /* .addRestaurant(restaurant) 해당 메소드 실행했는지 검증한다.
        * any() - 어떤 내용이 와도 실행된다. */
        verify(restaurantService).addRestaurant(any());

    }

}