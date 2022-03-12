package get_http_request.day5;

import base_url.JsonPalaceHolderBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;

public class GetRequest16 extends JsonPalaceHolderBaseUrl {
    /*
   https://jsonplaceholder.typicode.com/todos/7

   {
   "userId": 1,
   "id": 7,
   "title": "illo expedita consequatur quia in",
   "completed": false
}
    */
    @Test
    public void test16(){

        // 1. Url olusturma
        speq04.pathParams("bir","todos","iki",7);

        //2. Expected Data olustur
        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("userId", 1);
        expectedData.put("id", 7);
        expectedData.put("title","illo expedita consequatur quia in");
        expectedData.put("completed", false);
        System.out.println("Expected Data: "+expectedData);

        //3.Request ve Response
      Response response = given().spec(speq04).when().get("/{bir}/{iki}");
      response.prettyPrint();

      // Datayi Json'dan --> java'ya De-Serialization
        // Datayi Javadan --->Json'a Serializaion

        Map<String, Object> actualData = response.as(HashMap.class);  // De-Serizialtion
        System.out.println("Actual Data: "+actualData);

        Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
        Assert.assertEquals(expectedData.get("id"), actualData.get("id"));
        Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
        Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));

    }
}
