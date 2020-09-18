package jung.yun.kim.eatgo.domain;

import java.util.ArrayList;
import java.util.List;

public class Restaurant {

    //필드
    private Long id;
    private String name;
    private String address;

    public List<MenuItem> getMenuItems() {
        return menuItems;
    }

    //생성자
    public Restaurant() {
    }

    public Restaurant(Long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    //getter
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;//"Bob zip";
    }
    public String getAddress() {
        return address;
    }
    private List<MenuItem> menuItems = new ArrayList<MenuItem>();

    //메소드
    public String getInformation() {
        return name + " in " + address;
    }

    public void addMenuItem(MenuItem menuItem) {
        menuItems.add(menuItem);
    }

    public void setMenuItems(List<MenuItem> menuItems) {
        for (MenuItem menuItem : menuItems) {
           //addMenuItem() 메소드 호출하여 menuItem를 추가한다.
           addMenuItem(menuItem);
        }
    }
}

