package com.feedr.controllers;

import com.feedr.dao.FoodDAO;
import com.feedr.models.FoodModel;
import com.feedr.protobuf.RestaurantProto.Restaurant;
import com.feedr.protobuf.RestaurantProto.Food;
import com.feedr.util.ProtobufUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;

@RestController
public class RestaurantController {

    @Autowired
    private FoodDAO foodDAO;

    @RequestMapping(path = "/restaurant/{restaurantName}/food", method = RequestMethod.GET)
    public String foods(@PathVariable String restaurantName) throws Exception {
        ArrayList<Food> foodlist = new ArrayList<>();
        for (FoodModel foodModel : foodDAO.getFoods(restaurantName)) {
            Food.Builder builder = Food.newBuilder();
            builder.setResUsername(restaurantName);
            builder.setFoodname(foodModel.getFoodname());
            builder.setPrice(foodModel.getPrice());
            builder.setType(foodModel.getType());
            foodlist.add(builder.build());
        }
        Restaurant.Builder restaurantBuilder = Restaurant.newBuilder();
        restaurantBuilder.addAllFoodlist(foodlist);
        return ProtobufUtil.protobufToJSON(restaurantBuilder.build());
    }

}
