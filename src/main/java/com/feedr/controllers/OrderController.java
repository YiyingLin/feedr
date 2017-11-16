package com.feedr.controllers;

import com.feedr.dao.OrderDAO;
import com.feedr.models.FoodModel;
import com.feedr.models.OrderModel;
import com.feedr.protobuf.FoodProto.Food;
import com.feedr.protobuf.OrderProto.Order;
import com.feedr.protobuf.OrderProto.OrderList;
import com.feedr.util.ProtobufUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    @RequestMapping(path = "/publicOrders", method = RequestMethod.GET)
    public String getPublicOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        for (OrderModel orderModel : orderDAO.getPublicOrders()) {
            Order.Builder orderBuilder = Order.newBuilder();
            orderBuilder.setOrderId(orderModel.getOrderID());
            orderBuilder.setReceiverName(orderModel.getReceiver());
            orderBuilder.setDeliveryLocation(orderModel.getLocation());
            orderBuilder.setPhone(orderModel.getPhone());
            orderBuilder.setDeliverTip(orderModel.getDeliverTip());
            orderBuilder.setDeadline(orderModel.getDeadline());
            orderBuilder.setRestaurantName(orderModel.getRestaurant());
            List<FoodModel> foodModels = orderDAO.getFoodsOfOrder(orderModel.getOrderID());
            orderBuilder.addAllFoodMap(
                    foodModels.stream().map(foodModel -> {
                        Food.Builder foodBuilder = Food.newBuilder();
                        foodBuilder.setQuantity(foodModel.getQuantity());
                        foodBuilder.setFoodname(foodModel.getFoodname());
                        return foodBuilder.build();
                    }).collect(Collectors.toList())
            );
            orders.add(orderBuilder.build());
        }
        OrderList.Builder listBuilder = OrderList.newBuilder();
        listBuilder.addAllOrders(orders);
        return ProtobufUtil.protobufToJSON(listBuilder.build());
    }
}
