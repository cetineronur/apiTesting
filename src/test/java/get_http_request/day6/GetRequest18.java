package get_http_request.day6;

import base_url.GMIBankBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest18 extends GMIBankBaseUrl {
    /*
http://www.gmibank.com/api/tp-customers/43703

"firstName": "Alda",
"lastName": "Monahan",
"middleInitial": "Nichelle Hermann Kohler",
"email": "com.github.javafaker.Name@7c011174@gmail.com",
"mobilePhoneNumber": "909-162-8114",
"city": "St Louis",
"ssn": "108-53-6655"

1) MATCHERS CLASS
2) JSON PATH
3) De-Serialization
*/
    @Test
    public void test18(){
        speq03.pathParams("bir","tp-customers","iki",43703);

        Map<String, Object> expectedData = new HashMap<>();
        expectedData.put("firstName","Alda");
        expectedData.put("lastName","Monahan");
        expectedData.put("middleInitial","Nichelle Hermann Kohler");
        expectedData.put("email","com.github.javafaker.Name@7c011174@gmail.com");
        expectedData.put("mobilePhoneNumber","909-162-8114");
        expectedData.put("city","St Louis");
        expectedData.put("ssn","108-53-6655");

        System.out.println("expected Data: "+expectedData);

        //Request Response olustur
        Response response = given().spec(speq03)
                .header("Authorization", "Bearer "+generateToken())
                .when().get("/{bir}/{iki}");
        //response.prettyPrint();

        Map<String, Object> actualData = response.as(HashMap.class);//de-seriazilation
        System.out.println("Actual Data: "+actualData);

        assertEquals(expectedData.get("firstname"),actualData.get("firstname"));
        assertEquals(expectedData.get("lastName"),actualData.get("lastName"));
        assertEquals(expectedData.get("middleInitial"),actualData.get("middleInitial"));
        assertEquals(expectedData.get("email"),actualData.get("email"));
        assertEquals(expectedData.get("mobilePhoneNumber"),actualData.get("mobilePhoneNumber"));
        assertEquals(expectedData.get("city"),actualData.get("city"));
        assertEquals(expectedData.get("ssn"),actualData.get("ssn"));

        //JsonPath Dogrulama
        JsonPath json = response.jsonPath();
        assertEquals("Alda",json.getString("firstName"));
        assertEquals("Monahan",json.getString("lastName"));
        assertEquals("Nichelle Hermann Kohler",json.getString("middleInitial"));
        assertEquals("com.github.javafaker.Name@7c011174@gmail.com",json.getString("email"));
        assertEquals("909-162-8114",json.getString("mobilePhoneNumber"));
        assertEquals("St Louis",json.getString("city"));
        assertEquals("108-53-6655",json.getString("ssn"));

        //MAtcher Class Dogrulama
        response.then().assertThat().body("firstName",equalTo("Alda"));
        response.then().assertThat().body("lastName",equalTo("Monahan"));
        response.then().assertThat().body("middleInitial",equalTo("Nichelle Hermann Kohler"));
        response.then().assertThat().body("email",equalTo("com.github.javafaker.Name@7c011174@gmail.com"));
        response.then().assertThat().body("mobilePhoneNumber",equalTo("909-162-8114"));
        response.then().assertThat().body("city",equalTo("St Louis"));
        response.then().assertThat().body("ssn",equalTo("108-53-6655"));

    }
}
