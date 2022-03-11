package get_http_request.day7;

import base_url.JsonPalaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.hamcrest.Matchers;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JasonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PostRequest03 extends JsonPalaceHolderBaseUrl {
    /*
   https://jsonplaceholder.typicode.com/todos URL ine aşağıdaki body gönderildiğinde,
    {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false
  }
    Dönen response un Status kodunun 201 ve response body nin aşağıdaki gibi olduğunu test edin
  {
    "userId": 55,
    "title": "Tidy your room",
    "completed": false,
    "id": …
   }
*/

    @Test
    public void test03(){
        // 1) URL olustur
        speq04.pathParam("bir","todos");

        //2) Expected Data
        JasonPlaceHolderTestData testObje= new JasonPlaceHolderTestData();
        JSONObject expectedRequest = testObje.setUpPostData();
        System.out.println("expectedRequest = " + expectedRequest);

        //3) Request ve Response
        Response response = given().spec(speq04)
                .contentType(ContentType.JSON)
                .body(expectedRequest.toString())
                .when()
                .post("/{bir}");
        response.prettyPrint();

        //4) Dogrulama
        //1.Matcher Class
        response.then().assertThat().statusCode(201).
                body("userId",equalTo(expectedRequest.get("userId")),
                        "title",equalTo(expectedRequest.get("title")),
                        "completed",equalTo(expectedRequest.get("completed")) ,
                        "id",equalTo(expectedRequest.get("id")));

        //JsonPath ile
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedRequest.get("id"),json.getInt("id"));
        Assert.assertEquals(expectedRequest.get("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedRequest.get("statusCode"),json.getInt("statusCode"));
        Assert.assertEquals(expectedRequest.get("title"),json.getString("title"));
        Assert.assertEquals(expectedRequest.get("completed"),json.getBoolean("completed"));

        //De-Serialization

        HashMap<String, Object> aactualMap = response.as(HashMap.class);

        Assert.assertEquals(expectedRequest.getBoolean("completed"),aactualMap.get("completed"));
        Assert.assertEquals(expectedRequest.getInt("id"),aactualMap.get("id"));
        Assert.assertEquals(expectedRequest.getString("title"),aactualMap.get("title"));
        Assert.assertEquals(expectedRequest.getInt("statusCode"),aactualMap.get("statusCode"));
        Assert.assertEquals(expectedRequest.getInt("userId"),aactualMap.get("userId"));


    }
}
