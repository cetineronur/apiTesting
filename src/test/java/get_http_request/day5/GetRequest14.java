package get_http_request.day5;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest14 extends GMIBankBaseUrl {
/*
http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın

"firstName": "Melva",
"lastName": "Bernhard",
"email": "chas.kuhlman@yahoo.com"
"zipCode": "40207"

"country" "name": "San
"login": "delilah.metz"
 */

    @Test
    public void test14(){
        speq03.pathParams("bir", "tp-customers", "iki", "110472");

        Response response = given().spec(speq03).
                header("Authorization","Bearer "+generateToken())
                .when().get("/{bir}/{iki}");

        //response.prettyPrint();

        //Mathcer class ile

        response.then().assertThat().body("firstName",equalTo("Melva"),
                "lastName",equalTo("Bernhard"),
                "email",equalTo("chas.kuhlman@yahoo.com"),
                "zipCode",equalTo("40207"),
                "country.name",equalTo("San Jose"),
               "user.login",equalTo("delilah.metz") );

        //JsonPath ile

        JsonPath json = response.jsonPath();
        assertEquals("Melva",json.getString("firstName"));
        assertEquals("Bernhard",json.getString("lastName"));
        assertEquals("chas.kuhlman@yahoo.com",json.getString("email"));
        assertEquals("40207",json.getString("zipCode"));
        assertEquals("San Jose",json.getString("country.name"));
        assertEquals("delilah.metz",json.getString("user.login"));

    }
}