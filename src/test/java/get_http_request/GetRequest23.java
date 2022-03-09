package get_http_request;

import base_url.DummyBaseUrl;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;
import test_data.DummyTestData;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class GetRequest23 extends DummyBaseUrl {

    /*
http://dummy.restapiexample.com/api/v1/employees url ine bir istek gönderildiğinde
Status kodun 200 olduğunu,
14. Çalışan isminin "Haley Kennedy" olduğunu,
Çalışan sayısının 24 olduğunu,
Sondan 3. çalışanın maaşının 675000 olduğunu
40,21 ve 19 yaslarında çalışanlar olup olmadığını
10. Çalışan bilgilerinin bilgilerinin aşağıdaki gibi

{
        "id": 10,
        "employee_name": "Sonya Frost",
        "employee_salary": 103600,
        "employee_age": 23,
        "profile_image": ""
 }

  olduğunu test edin.
*/
    @Test
    public void test23(){

        //Url olusturma
        spec02.pathParams("1","api","2","v1","3","employees");

        //Expected Olusturma
        DummyTestData expectedObje = new DummyTestData();
        HashMap<String ,Object> expectedTestDataMap = expectedObje.setUpTestData();
        System.out.println("Expected Test Data"+expectedTestDataMap);

        //Request ve Response olustur
        Response response = given().spec(spec02).when().get("/{1}/{2}/{3}");
        response.prettyPrint();

        //4)dogrulama
        //1.Yol De-serilialization
        HashMap<String, Object> actualDataMap = response.as(HashMap.class);

        //Status kodun 200 olduğunu,
        assertEquals(expectedTestDataMap.get("14.calisan.statusCode"),actualDataMap.get("statusCode"));

        //14. Çalışan isminin "Haley Kennedy" olduğunu,
        assertEquals(expectedTestDataMap.get("14.calisan"),
                ((Map)((List)actualDataMap.get("data")).get(13)).get("employee_name"));

        //Çalışan sayısının 24 olduğunu,
        assertEquals(expectedTestDataMap.get("calisan sayisi"),
                ((List<?>) actualDataMap.get("data")).size());

        //Sondan 3. çalışanın maaşının 675000 olduğunu
        Assert.assertEquals(expectedTestDataMap.get("sondanucuncucalisaninmaasi"),
                ((Map)((List)actualDataMap.get("data")).get(((List)actualDataMap.get("data")).size()-3)).get("employee_salary"));


        //40,21 ve 19 yaslarında çalışanlar olup olmadığını
    }
}
