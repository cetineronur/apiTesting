package get_http_request.day7;

import base_url.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import test_data.HerOkuAppTestData;

import static io.restassured.RestAssured.given;

public class PostRequest01 extends HerokuAppBaseUrl {
    /*
   https://restful-booker.herokuapp.com/booking
   { "firstname": "Ali",
              "lastname": "Can",
              "totalprice": 500,
              "depositpaid": true,
              "bookingdates": {
                  "checkin": "2022-03-01",
                  "checkout": "2022-03-11"
               }
}
gönderildiğinde, Status kodun 200 olduğunu ve dönen response body nin ,
}
   "booking": {
       "firstname": "Ali",
       "lastname": "Can",
       "totalprice": 500,
       "depositpaid": true,
       "bookingdates": {
                           "checkin": "2022-03-01",
                            "checkout": "2022-03-11"
       }
   }
}
olduğunu test edin
    */

    @Test
    public void test01(){

        // 1 Url Olustur
        spec05.pathParam("1","booking");

        //2. Expected Data
        HerOkuAppTestData testData = new HerOkuAppTestData();
        JSONObject expectedRequestData= testData.setUpTestAndRequestData();

        //3.Request ve Response
        Response response = given()
                .contentType(ContentType.JSON)
                .auth()
                .basic("Admin","password123")
                .spec(spec05)
                .body(expectedRequestData.toString()) //Jsonpath de toString() kullanilir.
                .when()
                .post("/{1}");
        response.prettyPrint();

        //4)Dogrulama
        //1.yol JSON PATH

        JsonPath json = response.jsonPath();
        response.then().assertThat().statusCode(200);
        Assert.assertEquals(expectedRequestData.getString("firstname"), json.getString("booking.firstname"));
        Assert.assertEquals(expectedRequestData.getString("lastname"), json.getString("booking.lastname"));
        Assert.assertEquals(expectedRequestData.getInt("totalprice"), json.getInt("booking.totalprice"));
        Assert.assertEquals(expectedRequestData.getBoolean("depositpaid"), json.getBoolean("booking.depositpaid"));

        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").get("checkin"),
                json.getString("booking.bookingdates.checkin"));
        Assert.assertEquals(expectedRequestData.getJSONObject("bookingdates").get("checkout"),
                json.getString("booking.bookingdates.checkout"));
    }
}
