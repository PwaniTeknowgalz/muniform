package ke.co.muniform.muniform.data;

import android.support.v7.app.AppCompatActivity;

import java.util.List;

/**
 * Created by  on 19/04/2017.
 */

public class Supplies  {
    int id;
    String name;
    String shop;
    String school_type;
    String location;
    String phone;
    String price;
    String photo;
    String photo_dir;
    String supply_type;

    String details;

    public Supplies() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getShop() {
        return shop;
    }

    public void setShop(String shop) {
        this.shop = shop;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getPhoto_dir() {
        return photo_dir;
    }

    public void setPhoto_dir(String photo_dir) {
        this.photo_dir = photo_dir;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getSupply_type() {
        return supply_type;
    }

    public void setSupply_type(String supply_type) {
        this.supply_type = supply_type;
    }

    public String getSchool_type() {
        return school_type;
    }

    public void setSchool_type(String school_type) {
        this.school_type = school_type;
    }
}
