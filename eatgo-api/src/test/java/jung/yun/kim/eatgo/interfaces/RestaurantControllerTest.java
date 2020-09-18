package jung.yun.kim.eatgo.interfaces;

import jung.yun.kim.eatgo.application.RestaurantService;
import jung.yun.kim.eatgo.domain.MenuItemRespository;
import jung.yun.kim.eatgo.domain.MenuItemRespositoryImpl;
import jung.yun.kim.eatgo.domain.RestaurantRespository;
import jung.yun.kim.eatgo.domain.RestaurantRespositoryaImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.core.StringContains.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

    /* @SpyBean - 원하는 객체의 주입이 가능하다.
    test의 경우 메모리에 데이터가 저장되어 서버를 다시 시작시 데이터가 사라진다. */
    @SpyBean(RestaurantRespositoryaImpl.class)
    private RestaurantRespository restaurantRespository;

    @SpyBean(MenuItemRespositoryImpl.class)
    private MenuItemRespository menuItemRespository;

    @SpyBean(RestaurantService.class)
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
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

}