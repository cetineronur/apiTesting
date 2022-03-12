package get_http_request.day12;

import base_url.DummyBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;

public class DeleteRequest extends DummyBaseUrl {
     /*
  http://dummy.restapiexample.com/api/v1/delete/2 bir DELETE request gönderdiğimde

Dönen response un status kodunun 200 ve body kısmının aşağıdaki gibi olduğunu test edin
{
"status": "success",
"data": "2",
"message": "Successfully! Record has been deleted"
}
   */
    @Test
    public void test(){

        spec02.pathParams("1","api","2","v1","3","delete","4",2);
        DummyTestData testObje = new DummyTestData();
        JSONObject expectedData = testObje.setUpDeleteExpectedData();
        System.out.println("requestData = " + expectedData);

        Response response = given()
                .contentType(ContentType.JSON)
                .spec(spec02)
                .when().delete("/{1}/{2}/{3}/{4}");
        response.prettyPrint();

        //Dogrulama
        //JsonPath
        JsonPath json = response.jsonPath();
        Assert.assertEquals(200,response.getStatusCode());
        Assert.assertEquals(expectedData.get("status"),json.get("status"));
        Assert.assertEquals(expectedData.get("data"),json.get("data"));
        Assert.assertEquals(expectedData.get("message"),json.get("message"));


        //3)De-Serialization

        HashMap<String,Object> actualData=response.as(HashMap.class);

        Assert.assertEquals(expectedData.get("status"),actualData.get("status"));
        Assert.assertEquals(expectedData.get("data"),actualData.get("data"));
        Assert.assertEquals(expectedData.get("message"),actualData.get("message"));
    }
}
