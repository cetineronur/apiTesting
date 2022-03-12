package get_http_request.day2;

import base_url.RegresinBaseUrl;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest07 extends RegresinBaseUrl {

    /*
https://reqres.in/api/users URL request olustur.
body icerisindeki idsi 5 olan datayi
1) Matcher CLASS ile
2) JsonPath ile dogrulayin.
    */
    @Test
    public void test07(){

        spec01.pathParams("parametre1", "api","parametre2", "user2");
        Response response = given().spec(spec01).when().get("/{parametre1}/{parametre2}"); //---> /api/users
        response.prettyPrint();

        // Matcher equal class
        response.then().assertThat().body("data[4].color", equalTo("#E2583E")
                ,"data[4].name",equalTo("tigerlily")
                ,"data[4].year",equalTo(2004));

        //JsonPath
        JsonPath json = response.jsonPath();

        System.out.println(json.getList("data.color"));
        System.out.println(json.getString("data.name"));

        Assert.assertEquals("#E2583E",json.getString("data[4].color"));
        Assert.assertEquals("tigerlily", json.getString("data[4].name"));
    }
}
