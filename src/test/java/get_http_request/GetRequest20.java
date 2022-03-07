package get_http_request;

import base_url.JasonPalaceHolderBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest20 extends JasonPalaceHolderBaseUrl {

    /*
https://jsonplaceholder.typicode.com/todos/2
1) Status kodunun 200,
2) respose body'de,
        "completed": değerinin false
        "title": değerinin "quis ut nam facilis et officia qui"
        "userId" sinin 1 ve
   header değerlerinden
        "via" değerinin "1.1 vegur" ve
        "Server" değerinin "cloudflare" olduğunu test edin…
*/
    @Test
    public void test20(){

        speq04.pathParams("bir","todos","iki",2);

        //2) Expected data olustur
        HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("completed", false);
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("via", "1.1 vegur");
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("Server", "cloudflare");

        //Request ve Response

        Response response = given().spec(speq04).when().get("/{bir}/{iki}");
        response.prettyPrint();

        //4) Dogrulama
        //1.YOl MAtcher Class
        response.then().assertThat()
                .statusCode((Integer)expectedData.get("statusCode"))
                .headers("via", equalTo(expectedData.get("via"))
                        ,"Server",equalTo(expectedData.get("Server")))
                .body("userId",equalTo(expectedData.get("userId"))
                        , "id",equalTo(expectedData.get("id"))
                        ,"title",equalTo(expectedData.get("title"))
                        ,"completed",equalTo(expectedData.get("completed")));

        //2.YOl Json PAth
        JsonPath json = response.jsonPath();
        assertEquals(expectedData.get("statusCode"), response.statusCode());
        assertEquals(expectedData.get("via"), response.getHeader("via"));
        assertEquals(expectedData.get("Server"), response.getHeader("Server"));

        assertEquals(expectedData.get("userId"), json.getInt("userId"));
        assertEquals(expectedData.get("id"), json.getInt("id"));
        assertEquals(expectedData.get("title"), json.getString("title"));

        //3.Yol De-Serialiazation
        HashMap<String, Object> actualData = response.as(HashMap.class);
        assertEquals(expectedData.get("userId"), actualData.get("userId"));
        assertEquals(expectedData.get("title"), actualData.get("title"));
        assertEquals(expectedData.get("completed"), actualData.get("completed"));


    }
}
