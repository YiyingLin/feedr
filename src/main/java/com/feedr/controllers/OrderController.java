package com.feedr.controllers;

import com.feedr.dao.OrderDAO;
import com.feedr.dao.ReceiverDao;
import com.feedr.dao.SenderDao;
import com.feedr.models.CheckOrderModel;
import com.feedr.models.FoodModel;
import com.feedr.models.OrderModel;
import com.feedr.protobuf.RestaurantProto.Food;
import com.feedr.protobuf.OrderProto.Order;
import com.feedr.protobuf.OrderProto.OrderList;
import com.feedr.util.ProtobufUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
public class OrderController {

    @Autowired
    private OrderDAO orderDAO;

    @Autowired
    private ReceiverDao receiverDao;

    @Autowired
    private SenderDao senderDao;

    @RequestMapping(path = "/publicOrders", method = RequestMethod.GET)
    public String getPublicOrders() throws Exception {
        List<Order> orders = new ArrayList<>();
        for (OrderModel orderModel : orderDAO.getPublicOrders()) {
            Order.Builder orderBuilder = Order.newBuilder();
            setupBasicOrder(orderBuilder, orderModel);
            orders.add(orderBuilder.build());
        }
        OrderList.Builder listBuilder = OrderList.newBuilder();
        listBuilder.addAllOrders(orders);
        return ProtobufUtil.protobufToJSON(listBuilder.build());
    }

    @RequestMapping(path = "/receiverPrivateOrders/{receiverName}", method = RequestMethod.GET)
    public String getReceiverPrivateOrders(@PathVariable String receiverName) throws Exception {
        List<Order> orders = new ArrayList<>();
        for (CheckOrderModel orderModel : receiverDao.checkOrders(receiverName)) {
            Order.Builder orderBuilder = Order.newBuilder();
            setupPrivateOrder(orderBuilder, orderModel);
            orders.add(orderBuilder.build());
        }
        OrderList.Builder listBuilder = OrderList.newBuilder();
        listBuilder.addAllOrders(orders);
        return ProtobufUtil.protobufToJSON(listBuilder.build());
    }

    @RequestMapping(path = "/senderPrivateOrders/{senderName}", method = RequestMethod.GET)
    public String getSenderPrivateOrders(@PathVariable String senderName) throws Exception {
        List<Order> orders = new ArrayList<>();
        for (CheckOrderModel orderModel : senderDao.checkOrders(senderName)) {
            Order.Builder orderBuilder = Order.newBuilder();
            setupPrivateOrder(orderBuilder, orderModel);
            orders.add(orderBuilder.build());
        }
        OrderList.Builder listBuilder = OrderList.newBuilder();
        listBuilder.addAllOrders(orders);
        return ProtobufUtil.protobufToJSON(listBuilder.build());
    }

    private void setupBasicOrder(Order.Builder orderBuilder, OrderModel orderModel) throws SQLException {
        orderBuilder.setOrderId(orderModel.getOrderID());
        orderBuilder.setReceiverName(orderModel.getReceiver());
        orderBuilder.setDeliveryLocation(orderModel.getLocation());
        orderBuilder.setPhone(orderModel.getPhone());
        orderBuilder.setDeliverTip(orderModel.getDeliverTip());
        orderBuilder.setDeadline(orderModel.getDeadline());
        orderBuilder.setRestaurantName(orderModel.getRestaurant());
        orderBuilder.addAllFoodMap(getFoodsHelper(orderModel.getOrderID()));
    }

    private void setupPrivateOrder(Order.Builder orderBuilder, CheckOrderModel orderModel) throws SQLException {
        setupBasicOrder(orderBuilder, orderModel);

        orderBuilder.setSenderName(orderModel.getSender());
        orderBuilder.setCanceled(orderModel.isCancelled());
        orderBuilder.setDelivered(orderModel.isDelivered());
    }

    private List<Food> getFoodsHelper(int orderId) throws SQLException {
        List<FoodModel> foodModels = orderDAO.getFoodsOfOrder(orderId);
        return foodModels.stream().map(foodModel -> {
            Food.Builder foodBuilder = Food.newBuilder();
            foodBuilder.setQuantity(foodModel.getQuantity());
            foodBuilder.setFoodname(foodModel.getFoodname());
            return foodBuilder.build();
        }).collect(Collectors.toList());
    }
}
