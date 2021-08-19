package com.example.fevo.implementation;

import com.example.fevo.cameraservice.CameraService;
import com.example.fevo.model.Camera;
import com.example.fevo.model.Photo;
import com.example.fevo.model.Rover;
import com.example.fevo.photoservice.PhotoService;
import com.example.fevo.roverservice.RoverService;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class PhotoServiceApiImplementation {

    @Autowired
    private PhotoService photoService;

    private static final String API_KEY = "ETQtKwL9zdOgsd3Z1CpqpclDGdCfNZM9QBrIRxvd";
    private static final String BASE_KEY = "&api_key=";
    private static final String BASE_URL = "https://api.nasa.gov/mars-photos/api/v1/rovers/curiosity/photos?earth_date=";
    private static String API_DATE = LocalDate.now().toString();
    private static String API_URL = BASE_URL + API_DATE + BASE_KEY + API_KEY;


    public void apiRunner() {

        for (int i = 0; i < 10; i++) {

            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder().uri(URI.create(API_URL)).build();
            httpClient.sendAsync(httpRequest, HttpResponse.BodyHandlers.ofString())
                    .thenApply(HttpResponse::body)
                    .thenApply(this::parseMainObject)
                    .join();

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.parse(API_DATE, formatter);
            localDate = localDate.minusDays(1);
            API_DATE = localDate.toString();
            API_URL = BASE_URL + API_DATE + BASE_KEY + API_KEY;
        }
    }

    private String parseMainObject(String responseBody) {

        Photo photo;
        Camera camera;
        Rover rover;

        int index = 0;

        List<Photo> photoList = new ArrayList<>();
        System.out.println("API_DATE--> " + API_DATE);

        JsonElement fileElement = JsonParser.parseString(responseBody);
        JsonObject fileObject = fileElement.getAsJsonObject();
        JsonArray jsonArrayOfPhotos = fileObject.get("photos").getAsJsonArray();

        for (JsonElement element : jsonArrayOfPhotos) {

            photo = parsePhotoObject(element);

            camera = parseCameraObject(jsonArrayOfPhotos, index);

            rover = parseRoverObject(jsonArrayOfPhotos, index);

            photo.setCamera(camera);
            photo.setRover(rover);




            if (photo.getCamera().getName().equalsIgnoreCase("NAVCAM") &&
                    photo.getRover().getName().equalsIgnoreCase("Curiosity")) {
                photoList.add(photo);
            }
            index++;
        }

        printResult(photoList);

        photoService.saveAll(photoList);

        return null;
    }

    private Photo parsePhotoObject(JsonElement element) {

        Photo photo;

        JsonObject photoJsonObject = element.getAsJsonObject();

        photo = Photo.builder()
                .nasaId(photoJsonObject.get("id").getAsInt())
                .sol(photoJsonObject.get("sol").getAsInt())
                .img_src(photoJsonObject.get("img_src").getAsString())
                .earth_date(photoJsonObject.get("earth_date").getAsString())
                .build();
        return photo;
    }

    private Camera parseCameraObject(JsonArray jsonArrayOfPhotos, int index) {

        Camera camera;

        JsonElement cameraElement = jsonArrayOfPhotos.get(index).getAsJsonObject();
        JsonObject cameraObject = cameraElement.getAsJsonObject();
        JsonObject cameraJson = cameraObject.get("camera").getAsJsonObject();

        camera = Camera.builder()
                .nasaId(cameraJson.get("id").getAsInt())
                .name(cameraJson.get("name").getAsString())
                .rover_id(cameraJson.get("rover_id").getAsInt())
                .full_name(cameraJson.get("full_name").getAsString())
                .build();

        return camera;
    }


    private Rover parseRoverObject(JsonArray jsonArrayOfPhotos, int index) {

        Rover rover;

        JsonElement roverElement = jsonArrayOfPhotos.get(index).getAsJsonObject();
        JsonObject roverObject = roverElement.getAsJsonObject();
        JsonObject roverJson = roverObject.get("rover").getAsJsonObject();

        rover = Rover.builder()
                .nasaId(roverJson.get("id").getAsInt())
                .name(roverJson.get("name").getAsString())
                .landing_date(roverJson.get("landing_date").getAsString())
                .launch_date(roverJson.get("launch_date").getAsString())
                .status(roverJson.get("status").getAsString())
                .build();

        return rover;
    }

    private void printResult(List<Photo> photoList) {
        for (int i = 0; i < photoList.size(); i++) {
            System.out.println(
                    " NASA id: " + photoList.get(i).getNasaId() +
                            " Earth Date: " + photoList.get(i).getEarth_date() +
                            " Rover Name: " + photoList.get(i).getRover().getName() +
                            " Camera name: " + photoList.get(i).getCamera().getName() +
                            " image " + photoList.get(i).getImg_src());
            if (i == 2) {
                break;
            }
        }
    }

}

