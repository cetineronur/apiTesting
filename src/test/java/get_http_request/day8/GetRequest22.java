package get_http_request.day8;

import base_url.HerokuAppBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


public class GetRequest22 extends HerokuAppBaseUrl {
    /*
https://restful-booker.herokuapp.com/booking/33
       {
           "firstname": "Ali",
           "lastname": "Can",
           "totalprice": 500,
           "depositpaid": true,
           "bookingdates": {
               "checkin": "2022-02-01",
               "checkout": "2022-02-11"
          }
       }
1) JsonPhat
2) De-Serialization
*/
    @Test
    public void test22(){

        //Url Olusturma
        spec05.pathParams("bir","booking","iki",17);

        //Expected Data Olustur
        HerOkuAppTestData expectedObje = new HerOkuAppTestData();
        HashMap<String, Object> expectedTestDataMap = expectedObje.setUpTestData();
        System.out.println(expectedTestDataMap);

        //Request ve Dogrulama
        Response response = given().spec(spec05).when().get("/{bir}/{iki}");
        response.prettyPrint();

        // dogrulama
        //1.yol De-Serialization
        HashMap<String, Object> actualData = response.as(HashMap.class);
        System.out.println(actualData);

        Assert.assertEquals(expectedTestDataMap.get("firstname"), actualData.get("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"), actualData.get("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"), actualData.get("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"), actualData.get("depositpaid"));

        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkin")
                ,((Map)actualData.get("bookingdates")).get("checkin"));
        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkout")
                ,((Map)actualData.get("bookingdates")).get("checkout"));

        //2.YOl Json Path
        JsonPath json = response.jsonPath();
        Assert.assertEquals(expectedTestDataMap.get("firstname"),json.getString("firstname"));
        Assert.assertEquals(expectedTestDataMap.get("lastname"),json.getString("lastname"));
        Assert.assertEquals(expectedTestDataMap.get("totalprice"),json.getInt("totalprice"));
        Assert.assertEquals(expectedTestDataMap.get("depositpaid"),json.getBoolean("depositpaid"));

        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkin"),
                json.getString("bookingdates.checkin"));
        Assert.assertEquals(((Map)expectedTestDataMap.get("bookingdates")).get("checkout"),
                json.getString("bookingdates.checkout"));
    }
}