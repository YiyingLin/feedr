package com.feedr.controllers;

import com.feedr.dao.*;
import com.feedr.models.CheckOrderModel;
import com.feedr.models.FoodModel;
import com.feedr.models.OrderModel;
import com.feedr.protobuf.RatingProto.Rating;
import com.feedr.protobuf.RestaurantProto.Food;
import com.feedr.protobuf.OrderProto.Order;
import com.feedr.protobuf.OrderProto.OrderList;
import com.feedr.util.ProtobufUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private RestaurantDAO restaurantDAO;

    @Autowired
    private CancellationDAO cancellationDAO;

    @Autowired
    private RatingDAO ratingDAO;

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

    @RequestMapping(path = "/restaurants/{restaurantName}/orders", method = RequestMethod.GET)
    public String getOrdersOfRestaurant(@PathVariable String restaurantName) throws Exception {
        List<Order> orders = new ArrayList<>();
        for (CheckOrderModel orderModel : restaurantDAO.getRestaurantOrders(restaurantName)) {
            Order.Builder orderBuilder = Order.newBuilder();
            setupPrivateOrder(orderBuilder, orderModel);
            orders.add(orderBuilder.build());
        }
        OrderList.Builder listBuilder = OrderList.newBuilder();
        listBuilder.addAllOrders(orders);
        return ProtobufUtil.protobufToJSON(listBuilder.build());
    }

    @RequestMapping(path = "/cancelOrders", method = RequestMethod.GET)
    public String cancelOrder(@RequestParam int orderId, @RequestParam String username, @RequestParam String reason) throws SQLException {
        cancellationDAO.createCancellation(orderId, username, reason);
        return username + reason + orderId;
    }

    @RequestMapping(path = "/createNewOrder", method = RequestMethod.POST)
    public String createNewOrder(HttpServletRequest request) throws Exception {
        Order.Builder builder = Order.newBuilder();
        Order order = ProtobufUtil.jsonToProtobuf(builder, request, Order.class);
        orderDAO.createOrder(
                order.getReceiverName(),
                order.getRestaurantName(),
                order.getOrderCost(),
                order.getDeliverTip(),
                order.getDeadline(),
                order.getDeliveryLocation(),
                order.getFoodMapList()
        );
        return "";
    }

    @RequestMapping(path = "/takeOrder", method = RequestMethod.GET)
    public String takeOrder(@RequestParam String senderUsername, @RequestParam int orderId) throws Exception {
        senderDao.takeOrder(senderUsername, orderId);
        return senderUsername + orderId;
    }

    @RequestMapping(path = "/confirmDelivery", method = RequestMethod.POST)
    public String confirmDelivery(HttpServletRequest request) throws Exception {
        Rating.Builder builder = Rating.newBuilder();
        Rating rating = ProtobufUtil.jsonToProtobuf(builder, request, Rating.class);
        receiverDao.confirmDelivered(rating.getOrderId());
        ratingDAO.createRating(
                rating.getOrderId(),
                rating.getSenderRating(),
                rating.getRestaurantRating(),
                rating.getCommentSender(),
                rating.getCommentRestaurant()
        );
        return "";
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
