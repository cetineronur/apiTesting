package test_data;

import java.util.HashMap;
import java.util.Map;

public  class JasonPlaceHolderTestData {
    public static Map<String, Object> setUpTestData(){

      HashMap<String, Object> expectedData = new HashMap<>();
        expectedData.put("statusCode", 200);
        expectedData.put("completed", false);
        expectedData.put("userId", 1);
        expectedData.put("id", 2);
        expectedData.put("via", "1.1 vegur");
        expectedData.put("title", "quis ut nam facilis et officia qui");
        expectedData.put("Server", "cloudflare");

        return expectedData;

    }

}
