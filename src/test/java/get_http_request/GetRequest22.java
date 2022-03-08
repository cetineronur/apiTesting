package get_http_request;

import base_url.HerokuAppBaseUrl;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.junit.Test;
import test_data.HErOkuAppTestData;

import java.util.HashMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;


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
        spec05.pathParams("bir","booking","iki",33);

        //Expected Data Olustur
        HErOkuAppTestData expectedObje = new HErOkuAppTestData();
        HashMap<String, Object> expectedTestDataMap = expectedObje.setUpTestData();
        System.out.println(expectedTestDataMap);

        //Request ve Dogrulama
        Response response = given().spec(spec05).when().get("/{bir}/{iki}");
        response.prettyPrint();



    }
}