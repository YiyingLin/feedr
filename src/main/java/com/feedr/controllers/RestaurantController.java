package com.feedr.controllers;

import com.feedr.dao.FoodDAO;
import com.feedr.dao.RestaurantDAO;
import com.feedr.models.FoodModel;
import com.feedr.models.RestaurantModel;
import com.feedr.protobuf.RestaurantProto.Restaurant;
import com.feedr.protobuf.RestaurantProto.Food;
import com.feedr.util.ProtobufUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class RestaurantController {

    @Autowired
    private FoodDAO foodDAO;

    @Autowired
    private RestaurantDAO restaurantDAO;

    @RequestMapping(path = "/restaurants/{restaurantName}/food", method = RequestMethod.GET)
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

    @RequestMapping(path = "/new/restaurants/food/", method = RequestMethod.POST)
    public String createFood(HttpServletRequest request) throws Exception {
        Food.Builder builder = Food.newBuilder();
        Food food = ProtobufUtil.jsonToProtobuf(builder, request, Food.class);
        restaurantDAO.createFood(food.getResUsername(), food.getFoodname(), food.getPrice(), food.getType());
        return "";
    }

    @RequestMapping(path = "/restaurants", method = RequestMethod.GET)
    public String restaurants() throws Exception {
        List<RestaurantModel> restaurantModels = restaurantDAO.getRestaurants();
        return restaurantModels
                .stream()
                .map(restaurantModel -> "\""+restaurantModel.getUsername()+"\"") //a quick hack
                .collect(Collectors.toList()).toString();
    }

    @CrossOrigin(origins = {"http://localhost:3000"})
    @RequestMapping(path = "/restaurants/{restaurantName}/food/{foodname}", method = RequestMethod.DELETE)
    public String deleteFood(@PathVariable String restaurantName, @PathVariable String foodname) throws Exception {
        restaurantDAO.deleteFood(restaurantName, foodname);
        return "";
    }



}
