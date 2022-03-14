package get_http_request.day13;

import base_url.HerokuAppBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import pojos.BookingDatesPojo;
import pojos.BookingPojo;
import pojos.BookingResponsePojo;

import static io.restassured.RestAssured.given;

public class PostRequestPojo02 extends HerokuAppBaseUrl {

    /*
 https://restful-booker.herokuapp.com/booking
 request body
 { "firstname": "Ali",
            "lastname": "Can",
            "totalprice": 500,
            "depositpaid": true,
            "bookingdates": {
                "checkin": "2022-03-01",
                "checkout": "2022-03-11"
             }
 }}
Status code is 200
 response body
 {
    "bookingid": 11,
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
  */

    @Test
    public void test(){
        //1) Url olustur
        spec05.pathParams("1","booking");

        BookingDatesPojo bookingdates = new BookingDatesPojo("2022-03-01","2022-03-11");

        BookingPojo bookingpojo = new BookingPojo("Onur","Can",500,true,bookingdates);

        // BookingResponsePojo bookingResponse= new BookingResponsePojo(11, booking);

        Response rs = given().contentType(ContentType.JSON).spec(spec05).auth().basic("admin","password123")
                .body(bookingpojo.toString()).when().post("/{1}");

        rs.prettyPrint();

        //4) Dogrulama
        BookingResponsePojo actualData = rs.as(BookingResponsePojo.class);
        System.out.println("actualData = " + actualData);

        Assert.assertEquals(200,rs.getStatusCode());
        Assert.assertEquals(bookingpojo.getFirstname(),actualData.getBookingPojo().getFirstname());
        Assert.assertEquals(bookingpojo.getLastname(),actualData.getBookingPojo().getLastname());
        Assert.assertEquals(bookingpojo.getTotalprice(),actualData.getBookingPojo().getTotalprice());
        Assert.assertEquals(bookingpojo.isDepositpaid(),actualData.getBookingPojo().isDepositpaid());
        Assert.assertEquals(bookingdates.getCheckin(),actualData.getBookingPojo().getBookingDatesPojo().getCheckin());
        Assert.assertEquals(bookingdates.getCheckout(),actualData.getBookingPojo().getBookingDatesPojo().getCheckout());


    }
}
