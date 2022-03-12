package get_http_request.day12;

import base_url.JsonPalaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.JsonPlaceHolderPojo;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class PostRequestPojo01 extends JsonPalaceHolderBaseUrl {
     /*
   https://jsonplaceholder.typicode.com/todos url 'ine bir request gönderildiğinde
Request body{
"userId": 21,
"id": 201,
"title": "Tidy your room",
"completed": false
}
Status kodun 201, response body 'nin ise

{
"userId": 21,
"id": 201,
"title": "Tidy your room",
"completed": false
}
*/
    @Test
    public void test(){
        speq04.pathParam("first","todos");

        //2) Expected Data olustur
        JsonPlaceHolderPojo expectedData = new JsonPlaceHolderPojo(21,201,"Tidy your room",false);
        System.out.println("expectedData = " + expectedData);

        //3)Request ve Response
        Response response = given()
                .spec(speq04)
                .contentType(ContentType.JSON)
                .body(expectedData)
                .when()
                .post("/{first}");
        response.prettyPrint();

        //4) Dogrulama
        //De/Serialization
        JsonPlaceHolderPojo actualData = response.as(JsonPlaceHolderPojo.class);
        Assert.assertEquals(201,response.getStatusCode());
       Assert.assertEquals(expectedData.getId(),actualData.getId());
       Assert.assertEquals(expectedData.getUserId(),actualData.getUserId());
       Assert.assertEquals(expectedData.getTitle(),actualData.getTitle());
       Assert.assertEquals(expectedData.isCompleted(),actualData.isCompleted());
    }
}
