package org.example;


import static spark.Spark.*;

import com.fasterxml.uuid.Generators;
import com.google.gson.Gson;
import com.itextpdf.text.*;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import spark.Request;
import spark.Response;
import org.example.Car;
import org.example.Airbag;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.UUID;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


public class Main {
    private static ArrayList<Car> carlist = new ArrayList<Car>();
    public static void setCarlist(ArrayList<Car> carlist) {
        Main.carlist = carlist;
    }
    public static ArrayList<Car> getCarlist() {
        return carlist;
    }

    public static void main(String[] args) {

        staticFiles.location("/public");
        String projectDir = System.getProperty("user.dir");
        String staticDir = "/src/main/resources/public";
        staticFiles.externalLocation(projectDir + staticDir);

        System.out.printf("Hello and welcome!");
        get("/json", (req, res) -> handleJson(req, res));
        get("/getinvoice", (req, res) -> handleDownloadInvoice(req, res));

        post("/add", (req, res) -> handleAdd(req, res));
        post("/delete", (req, res) -> handleDelete(req, res));
        post("/update", (req, res) -> handleUpdate(req, res));
        post("/genoneinvoice", (req, res) -> handleGenerateInvoice(req, res));
    }

    static String handleAdd(Request req, Response res) {
        String responsetext = req.body();
        System.out.println("RESPONSETEXT: " + responsetext);
        Gson gson = new Gson();

        Car car = gson.fromJson(responsetext, Car.class);

        String uuid = Generators.randomBasedGenerator().generate().toString();
        car.setUuid(uuid);

        System.out.println(car);

        ArrayList<Car> temp = getCarlist();
        temp.add(car);
        setCarlist(temp);

        res.type("application/json");
        return responsetext;
    }

    static String handleJson(Request req, Response res) {
        Gson gson = new Gson();
        return gson.toJson(getCarlist(), ArrayList.class );
    }

    static String handleDelete(Request req, Response res) {
        String jsonBody = req.body();
        System.out.println("JSON Body: " + jsonBody);

        // Parse JSON to extract the UUID
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonBody).getAsJsonObject();
        String uuidToDelete = jsonObject.get("uuid").getAsString();
        System.out.println("UUID to delete: " + uuidToDelete);

        ArrayList<Car> carList = getCarlist();

        // Delete the car from the carlist based on its UUID
        Iterator<Car> iterator = carList.iterator();
        while (iterator.hasNext()) {
            Car car = iterator.next();
            if (car.getUuid().equals(uuidToDelete)) {
                iterator.remove();
                setCarlist(carList);  // Update the carlist with the modified list
                res.type("application/json");
                return "Car deleted successfully";
            }
        }

        // If the car was not found
        res.status(404);
        return "Car not found";
    }
    static String handleUpdate(Request req, Response res) {
        String jsonBody = req.body();
        System.out.println("JSON Body: " + jsonBody);

        // Parse JSON to extract the UUID and update data
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonBody).getAsJsonObject();

        String uuidToUpdate = jsonObject.get("uuid").getAsString();
        String newModel = jsonObject.get("newModel").getAsString();
        int newRok = jsonObject.get("newRok").getAsInt();

        System.out.println("UUID to update: " + uuidToUpdate);
        System.out.println("New Model: " + newModel);
        System.out.println("New Rok: " + newRok);

        ArrayList<Car> carList = getCarlist();

        // Update the car in the carlist based on its UUID
        for (Car car : carList) {
            if (car.getUuid().equals(uuidToUpdate)) {
                car.setModel(newModel);
                car.setRok(newRok);
                setCarlist(carList);  // Update the carlist with the modified list
                res.type("application/json");
                return "Car updated successfully";
            }
        }

        // If the car was not found
        res.status(404);
        return "Car not found";
    }

    static String handleGenerateInvoice(Request req, Response res) throws IOException, DocumentException {

        String jsonBody = req.body();
        System.out.println("JSON Body: " + jsonBody);

        // Parse JSON to extract the UUID
        JsonParser parser = new JsonParser();
        JsonObject jsonObject = parser.parse(jsonBody).getAsJsonObject();
        String uuid = jsonObject.get("uuid").getAsString();
        String model = jsonObject.get("model").getAsString();
        String rok = jsonObject.get("rok").getAsString();
        String kolor = jsonObject.get("kolor").getAsString();
        String p1 = jsonObject.get("kierowca").getAsString();
        String p2 = jsonObject.get("pasazer").getAsString();
        String p3 = jsonObject.get("kanapa").getAsString();
        String p4 = jsonObject.get("boczne").getAsString();

        System.out.println("CAR: " + uuid + " | " + kolor + " | " + model + " | " + rok);

        Document document = new Document(); // dokument pdf
        String path = "src/main/resources/public/invoices/" + uuid + ".pdf";
        PdfWriter.getInstance(document, new FileOutputStream(path));

        document.open();
        Font font = FontFactory.getFont(FontFactory.COURIER, 16, BaseColor.BLACK);
        Font font2 = FontFactory.getFont(FontFactory.COURIER, 22, BaseColor.BLACK);
        Paragraph p = new Paragraph("Faktura dla: " + uuid, font2);
        document.add(p);

        Chunk chunk = new Chunk("model: " + model + "\n", font2); // akapit
        document.add(chunk);

        chunk = new Chunk("kolor: " + kolor + "\n", font); // akapit
        document.add(chunk);

        chunk = new Chunk("rok: " + rok + "\n", font); // akapit
        document.add(chunk);

        chunk = new Chunk("poduszka: kierowca " + p1 + "\n", font); // akapit
        document.add(chunk);

        chunk = new Chunk("poduszka: pasazer " + p2 + "\n", font); // akapit
        document.add(chunk);

        chunk = new Chunk("poduszka: kanapa " + p3 + "\n", font); // akapit
        document.add(chunk);

        chunk = new Chunk("poduszka: boczne " + p4 + "\n", font); // akapit
        document.add(chunk);

        Image img = Image.getInstance("src/main/resources/public/img/noimages.jpg");
        img.scaleToFit(500,500);
        document.add(img);

        document.close();

        return "";
    }

    static String handleDownloadInvoice(Request req, Response res) throws IOException {

        String uuid = req.queryParams("uuid");

        String path = "src/main/resources/public/invoices/" + uuid + ".pdf";

        File file = new File(path);
        if(file.exists()) {
            res.type("application/octet-stream"); //
            res.header("Content-Disposition", "attachment; filename=" + uuid + ".pdf"); // nagłówek

            OutputStream  outputStream = res.raw().getOutputStream();
            outputStream.write(Files.readAllBytes(Path.of(path))); // response pliku do przeglądarki

            return "OK";
        } else {
            return "NO FILE";
        }
    }
}