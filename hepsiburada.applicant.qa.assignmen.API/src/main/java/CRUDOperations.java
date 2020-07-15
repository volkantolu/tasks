import com.google.gson.JsonObject;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.parsing.Parser;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class CRUDOperations {

    public static Response doGetRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
                given()
                        .contentType(ContentType.JSON)
                        .body("")
                        .get(endpoint)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }

    public static Response doPostRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",1);
        jsonObject.addProperty("username","userDemo");
        jsonObject.addProperty("firstName","string");
        jsonObject.addProperty("lastName","string");
        jsonObject.addProperty("email","string");
        jsonObject.addProperty("password","string");
        jsonObject.addProperty("phone","string");
        jsonObject.addProperty("userStatus",0);
        //next element
        return
                given()
                        .contentType(ContentType.JSON)
                        .body(jsonObject.toString())
                        .post(endpoint)
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }

    public static Response doPostRequest2(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",1);
        //next element
        return
                given()
                        .contentType(ContentType.JSON)
                        .body(jsonObject.toString())
                        .post(endpoint)
                        .then()
                        .statusCode(201)
                        .extract()
                        .response();
    }

    public static Response doPutRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("id",1);
        jsonObject.addProperty("username","demoUser");
        jsonObject.addProperty("firstName","string");
        jsonObject.addProperty("lastName","string");
        jsonObject.addProperty("email","string");
        jsonObject.addProperty("password","string");
        jsonObject.addProperty("phone","string");
        jsonObject.addProperty("userStatus",0);

        //put at specified order
        return
                given()
                        .contentType(ContentType.JSON)
                        .body(jsonObject.toString())
                        .put(endpoint + "/demoUser")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }

    public static Response doDeleteRequest(String endpoint) {
        RestAssured.defaultParser = Parser.JSON;

        return
                given()
                        .contentType(ContentType.JSON)
                        .delete(endpoint + "/userDemo")
                        .then()
                        .statusCode(200)
                        .extract()
                        .response();
    }

    public static void main(String[] args) {
        String method="";
        Response response;
        Map<Object,Object> responseMap = new HashMap<Object, Object>();
        List<String> jsonResponse;

        String basePath = "https://petstore.swagger.io/v2";

        //API Testing
        //POST
        method="/user";
        response = doPostRequest(  basePath + method);
        responseMap = response.jsonPath().getMap("");
        System.out.println("posts response size POST call: " + responseMap.size());

        //PUT
        method= "/user";
        response = doPutRequest(  basePath + method);
        responseMap = response.jsonPath().getMap("");
        System.out.println("puts response size PUT call: " + responseMap.size());

        //GET
        method = "/user/userDemo";
        response = doGetRequest(  basePath + method);

        responseMap = response.jsonPath().getMap("");
        System.out.println("get response size GET call: " + responseMap.size());

        //DELETE
        method = "/user";
        response = doDeleteRequest(  basePath + method);
        responseMap = response.jsonPath().getMap("");
        System.out.println("delete response size DELETE call: " + responseMap.size());
    }
}