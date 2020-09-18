package jung.yun.kim.eatgo.interfaces;

import jung.yun.kim.eatgo.application.RestaurantService;
import jung.yun.kim.eatgo.domain.Restaurant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/* @RestController - @ResponseBody와 같이 Json 형태로 객체 데이터를 반환 */
@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restrantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){

        List<Restaurant> restaurants = restrantService.getRestaurants();

        return restaurants;
    }

    @GetMapping("/restaurants/{id}")
    /* @PathVariable - URI의 일부를 변수로 전달할 수 있다.
    * {변수} */
    public Restaurant detail(@PathVariable("id") Long id){

        Restaurant restaurant = restrantService.getRestaurant(id);
        /*
        //가게 기본정보 조회
        Restaurant restaurant = respositoryRepository.findById(id);

        //가게 메뉴정보 조회
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);

        restaurant.setMenuItems(menuItems);
        */

        return restaurant;
    }

}
