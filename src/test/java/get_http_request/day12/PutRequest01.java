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

public class PutRequest01 extends JsonPalaceHolderBaseUrl {

    /*
https://jsonplaceholder.typicode.com/todos/198 URL ine aşağıdaki body gönerdiğimde

{
"userId": 21,
"title": "Wash the dishes",
"completed": false
}
Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"userId": 21,
"title": "Wash the dishes",
"completed": false,
"id": 198
}
*/
    @Test
    public void test(){
        //1) Url Olustur
        speq04.pathParams("1","todos","2",198);

        //expected Data
        JasonPlaceHolderTestData testObje = new JasonPlaceHolderTestData();
        JSONObject expectedRequest = testObje.setUpPutData();
        System.out.println("expectedRequest = " + expectedRequest);

        //Request ve Response

        Response response = given().contentType(ContentType.JSON)
                .spec(speq04)
                .body(expectedRequest.toString()).when().put("/{1}/{2}");
        response.prettyPrint();

        //) Dogrulama
        //JsonPath ile
        JsonPath json = response.jsonPath();
        Assert.assertEquals(200,response.statusCode());
        Assert.assertEquals(expectedRequest.get("title"),json.getString("title"));
        Assert.assertEquals(expectedRequest.get("userId"),json.getInt("userId"));
        Assert.assertEquals(expectedRequest.get("completed"),json.getBoolean("completed"));

        //Matcher Class ile
        response.then().assertThat().statusCode(200)
                .body("title",equalTo(expectedRequest.get("title"))
                ,"userId",equalTo(expectedRequest.get("userId"))
                        ,"completed", equalTo(expectedRequest.get("completed")));

        //De-Serialization

        HashMap<String, Object> actualData  = response.as(HashMap.class);
        Assert.assertEquals(expectedRequest.get("title"),actualData.get("title"));
        Assert.assertEquals(expectedRequest.get("userId"),actualData.get("userId"));
        Assert.assertEquals(expectedRequest.get("completed"),actualData.get("completed"));

    }
}
