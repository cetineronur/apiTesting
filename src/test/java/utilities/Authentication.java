package utilities;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class Authentication {

    public static void main(String[] args) {
        String guncelToken = generateToken();
        System.out.println(guncelToken);
    }

    public static String generateToken(){

        Map<String, Object> map = new HashMap<>();
        map.put("username", "Batch44Api");
        map.put("password", "Batch44+");

        String endPoint="https://www.gmibank.com/api/authenticate";
        Response response = given().contentType(ContentType.JSON).body(map).when().post(endPoint);
        JsonPath token = response.jsonPath();

        return token.getString("id_token"); // id_token
    }
}
