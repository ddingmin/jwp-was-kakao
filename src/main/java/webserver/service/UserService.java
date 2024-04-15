package webserver.service;

import db.DataBase;
import model.User;
import webserver.controller.request.UserRequest;

public class UserService {
    public void register(UserRequest request) {
        User user = new User(request.getUserId(),
                request.getPassword(),
                request.getName(),
                request.getEmail());

        DataBase.addUser(user);
    }

    public boolean login(UserRequest request) {
        User user = DataBase.findUserById(request.getUserId());
        return user != null && request.getPassword().equals(user.getPassword());
    }
}
