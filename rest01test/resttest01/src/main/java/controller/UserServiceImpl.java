package controller;

import model.User;

import java.util.HashMap;

public class UserServiceImpl implements UserService {
    HashMap<String, User> users = new HashMap<>();
    @Override
    public void addUser(User user) {

    }

    @Override
    public User getUser(String id) {
        return null;
    }

    @Override
    public User editUser(User user) {
        return null;
    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public Boolean userExist(String id) {
        return null;
    }
}
