package baseURL;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class JsonPlaceHolderBaseUrl {

    protected RequestSpecification specJsonPlaceHolder;

    @Before
    public void setup() {

        specJsonPlaceHolder=new RequestSpecBuilder().setBaseUri("https://jsonplaceholder.typicode.com").build();
    }

    }


