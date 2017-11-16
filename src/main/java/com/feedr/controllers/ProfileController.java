package com.feedr.controllers;

import com.feedr.dao.RestaurantDAO;
import com.feedr.dao.SenderDao;
import com.feedr.dao.UserDAO;
import com.feedr.protobuf.ProfileProto.Profile;
import com.feedr.util.CookieService;
import com.feedr.util.ProtobufUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class ProfileController {

    @Autowired
    private UserDAO userDAO;
    private SenderDao senderDao;
    private RestaurantDAO restaurantDAO;

    @RequestMapping(path = "/profile", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
          Profile.Builder builder = Profile.newBuilder();
          Profile profile = ProtobufUtil.jsonToProtobuf(builder, request, Profile.class);
          String username = CookieService.getUsernameCookie(request).get();
          String phone = userDAO.getUser(username).getPhone();
          String password = userDAO.getPassword(username);
          String userType = userDAO.getUser(username).getType();
          long sender_rating = senderDao.getSender(username).getSenderRating();
          String sender_location = senderDao.getSender(username).getLocation();
          String res_name = restaurantDAO.getRestaurant(username).getRestName();
          long res_rating = restaurantDAO.getRestaurant(username).getRestRating();
          String res_location = restaurantDAO.getRestaurant(username).getLocation();

          if(userType.equals("USER")){
              builder = Profile.newBuilder();
              builder.setUsername(username);
              builder.setPhone(phone);
              builder.setPassword(password);
              builder.setSenderRating(sender_rating);
              builder.setSenderLocation(sender_location);
              return ProtobufUtil.protobufToJSON(builder.build());
          }
          else if(userType.equals("RESTAURANT")){
              builder = Profile.newBuilder();
              builder.setUsername(username);
              builder.setPhone(phone);
              builder.setPassword(password);
              builder.setResName(res_name);
              builder.setResRating(res_rating);
              builder.setResLocation(res_location);
              return ProtobufUtil.protobufToJSON(builder.build());
          }
          else{
              throw new Exception("No such user type!");
          }
    }
}
