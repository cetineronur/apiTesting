package base_url;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JasonPalaceHolderBaseUrl {
    protected RequestSpecification speq04;

    @Before
    public void setUp(){

        speq04 = new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }
}
