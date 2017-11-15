package com.feedr.controllers;

import com.feedr.dao.UserDAO;
import com.feedr.protobuf.UserProto.User;
import com.feedr.util.ProtobufUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RestController
public class AuthenticationController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(path = "/signin", method = RequestMethod.POST)
    public String login(HttpServletRequest request, HttpServletResponse response) throws Exception {
        User.Builder builder = User.newBuilder();
        User user = ProtobufUtil.jsonToProtobuf(builder, request, User.class);
        String password = userDAO.getPassword(user.getUsername());
        if (password.equals(user.getPassword())) {
            builder = User.newBuilder();
            builder.setUsername(user.getUsername());
            builder.setUserType("RESTAURANT");
            return ProtobufUtil.protobufToJSON(builder.build());
        } else {
            throw new Exception("Authentication failed");
        }
    }
}
