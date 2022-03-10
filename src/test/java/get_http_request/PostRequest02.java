package get_http_request;

import base_url.DummyBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;


public class PostRequest02 extends DummyBaseUrl {

    /*
http://dummy.restapiexample.com/api/v1/create url ine, Request Body olarak
{
    "name":"Ali Can",
    "salary":"2000",
    "age":"40",
}
gönderildiğinde,Status kodun 200 olduğunu ve dönen response body nin,

{
    "status": "success",
    "data": {
    "id":…
},
    "message": "Successfully! Record has been added."
}

olduğunu test edin
 */
    @Test
    public void test02(){

        //1) Url Olustur
        spec02.pathParams("bir","api","iki","v1","uc","create");

        //2) Expected Data
        DummyTestData obje = new DummyTestData();
        HashMap<String, Object> requestBodyMap = obje.setUpRequestBody();   //Metod dan donen requestbody mapi burada bir mape attik
        HashMap<String, Object> expectedDataMap = obje.setUpexpectedData(); //Metod dan donen expected mapi burada bir mape attik

        //3) REquest ve Response
        Response response = given()
                .spec(spec02)
                .body(requestBodyMap) // Post isleminde MAp kullandigimiz icin toString kullnamadik
                .when().post("/{bir}/{iki}/{uc}");
        response.prettyPrint();

        //4)DOgrulama
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);
        System.out.println("actualDataMap = " + actualDataMap);

        assertEquals(expectedDataMap.get("statusCode"), response.getStatusCode());
        assertEquals(expectedDataMap.get("status"), actualDataMap.get("status"));
        assertEquals(expectedDataMap.get("message"), actualDataMap.get("message"));
        assertEquals(expectedDataMap.get("data.id"), actualDataMap.get("data.id"));

        //2.yol
        //JsonPath
        JsonPath json = response.jsonPath();
        assertEquals(expectedDataMap.get("statusCode"),response.statusCode());
        assertEquals(expectedDataMap.get("status"),json.getString("status"));
        assertEquals(expectedDataMap.get("message"),json.getString("message"));


    }

}
