package test_data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class DummyTestData {
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
    public HashMap<String, Object> setUpTestData(){
        List<Integer> ages=new ArrayList<>(Arrays.asList(40,21,19));

        HashMap<String, Object> id10= new HashMap<>();
        id10.put("id",10);
        id10.put("employee_name","Sonya Frost");
        id10.put("employee_salary",103600);
        id10.put("employee_ag",23);
        id10.put("profile_image","");

        HashMap<String,Object> expectedData = new HashMap<>();
        expectedData.put("statusCode",200);
        expectedData.put("14.calisan","Haley Kennedy");
        expectedData.put("calisan sayisi",24);
        expectedData.put("sondanucuncucalisaninmaasi",675000);
        expectedData.put("arananyaslar",ages);
        expectedData.put("onuncucalisan",id10);
        return expectedData;

    }

}
