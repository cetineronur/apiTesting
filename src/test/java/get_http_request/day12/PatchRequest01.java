package get_http_request.day12;

import base_url.JsonPalaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.JasonPlaceHolderTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class PatchRequest01 extends JsonPalaceHolderBaseUrl {

      /*
   https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönderdiğimde
  {
     "title": "Batch44"
    }
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"userId": 10,
"title": "Batch44"
"completed": true,
"id": 198
}
    */

@Test
    public void test(){
    speq04.pathParams("1","todos","2",198);

    JasonPlaceHolderTestData testObje = new JasonPlaceHolderTestData();
    JSONObject requestData = testObje.setUpPatchRequestData();
    System.out.println("expectedRequest = " + requestData);

    JSONObject expectedData = testObje.setUpPatchExpectedData();
    System.out.println("expectedData = " + expectedData);


    //Request ve REsponse
    Response response = given().contentType(ContentType.JSON).spec(speq04).body(requestData.toString()).when().patch("/{1}/{2}");
    response.prettyPrint();

    //JsonPath Dogrulama
    JsonPath json = response.jsonPath();
    Assert.assertEquals(expectedData.get("userId"), json.getInt("userId"));
    Assert.assertEquals(expectedData.get("title"), json.getString("title"));
    Assert.assertEquals(expectedData.get("completed"), json.getBoolean("completed"));
    Assert.assertEquals(expectedData.get("id"), json.getInt("id"));

    //MAtcher
    //Matcher
    response.then().statusCode(200);
    response.then().body("title",equalTo(expectedData.getString("title"))
            ,"completed",equalTo(expectedData.getBoolean("completed"))
            ,"userId",equalTo(expectedData.getInt("userId"))
            ,"id",equalTo(expectedData.getInt("id")));

    // 3) De-Serialization
    HashMap<String, Object> actualData = response.as(HashMap.class);
    Assert.assertEquals(expectedData.get("userId"), actualData.get("userId"));
    Assert.assertEquals(expectedData.get("title"), actualData.get("title"));
    Assert.assertEquals(expectedData.get("completed"), actualData.get("completed"));
    Assert.assertEquals(expectedData.get("id"), actualData.get("id"));
}
}
