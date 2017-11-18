package com.feedr.controllers;

import com.feedr.dao.RestaurantDAO;
import com.feedr.dao.SenderDao;
import com.feedr.dao.UserDAO;
import com.feedr.protobuf.ProfileProto.Profile;
import com.feedr.util.ProtobufUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
public class ProfileController {

    @Autowired
    private UserDAO userDAO;
    @Autowired
    private SenderDao senderDao;
    @Autowired
    private RestaurantDAO restaurantDAO;

    @RequestMapping(path = "/profile/{username}", method = RequestMethod.GET)
    public String profile(@PathVariable String username) throws Exception {
          Profile.Builder builder;
          String phone = userDAO.getUser(username).getPhone();
          String password = userDAO.getPassword(username);
          String userType = userDAO.getUser(username).getType();

          if(userType.equals("USER")){
              int sender_rating = senderDao.getSender(username).getSenderRating();
              String sender_location = senderDao.getSender(username).getLocation();
              builder = Profile.newBuilder();
              builder.setUsername(username);
              builder.setPhone(phone);
              builder.setPassword(password);
              builder.setSenderRating(sender_rating);
              builder.setSenderLocation(sender_location);
              return ProtobufUtil.protobufToJSON(builder.build());
          }
          else if(userType.equals("RESTAURANT")){
              String res_name = restaurantDAO.getRestaurant(username).getRestName();
              int res_rating = restaurantDAO.getRestaurant(username).getRestRating();
              String res_location = restaurantDAO.getRestaurant(username).getLocation();
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

    @RequestMapping(path = "/createProfile", method = RequestMethod.POST)
    public String createProfile(HttpServletRequest request) throws Exception {
        Profile.Builder builder = Profile.newBuilder();
        Profile profile = ProtobufUtil.jsonToProtobuf(builder, request, Profile.class);
        userDAO.createUser(
                profile.getUsername(),
                profile.getPassword(),
                profile.getPhone(),
                profile.getType(),
                profile.getResName(),
                profile.getResLocation()
        );
        return "";
    }
}
