package org.example;

import com.google.gson.Gson;
import controller.UserServiceImpl;
import model.User;
import spark.Request;
import spark.Response;

import static spark.Spark.*;

class App {
    private static UserServiceImpl userServiceImpl = new UserServiceImpl();
    public static void main(String[] args) {
        post("/users", (req, res) -> add(req, res));
    }

    static String add(Request req, Response res) {
        Gson gson = new Gson();
        System.out.println(req.body());

        User newUser = gson.fromJson(req.body(), User.class);
        System.out.println(newUser.getId());
        System.out.println(newUser.getFirstName() + " " + newUser.getLastName());
        System.out.println(newUser.getEmail());
        System.out.println(newUser.getClass());
        return "";
    }
}