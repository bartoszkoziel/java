package controller;

import model.User;

public interface UserService {

    void addUser(User user);
    User getUser(String id);
    User editUser(User user);
    void deleteUser(String id);
    Boolean userExist(String id);
}
