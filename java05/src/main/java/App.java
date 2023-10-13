import com.google.gson.Gson;
import spark.Request;
import spark.Response;
import java.util.ArrayList;

import static spark.Spark.*;

public class App {
    public static void main(String[] args) {

        staticFiles.location("/public");
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources/public";
        staticFiles.externalLocation(projectDir + staticDir);


        get("/test", (req, res) -> testFun(req, res));
        get("/gson", (req, res) -> {
            ArrayList<Integer> list1 = new ArrayList<>();
            list1.add(1);
            list1.add(2);
            list1.add(3);
            list1.add(7);

            Gson gson = new Gson();

            return gson.toJson(list1, ArrayList.class);
        });
    }
    static String testFun(Request req, Response res){
        String test = "response from server";
        res.type("text/plain");

        System.out.println(res.status());
        System.out.println(req.requestMethod());
        System.out.println(req.attributes());
        System.out.println(req.cookies());
        System.out.println("REQ.PARAMS(): " + req.params()); // potrzebne dziś
        System.out.println(req.uri());
        System.out.println(req.url());
        System.out.println("REQ.QUERYPARAMS(): " + req.queryParams()); // potrzebne dziś
        System.out.println("REQ.QUERYPARAMS(x): " + req.queryParams("x")); // potrzebne dziś
        System.out.println(req.pathInfo());
        System.out.println(req.contentLength());
        System.out.println(req.contentType());
        System.out.println(req.protocol());
        System.out.println(req.headers());

        return test;
    }
}
