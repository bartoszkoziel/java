package org.example;

import static spark.Spark.*;
import spark.Request;
import spark.Response;
// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        get("/test", (req, res) -> "test");
    }
}