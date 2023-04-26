package tests;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertEquals;

public class C02_Post_ExpectedDataVeJsonPathAssertion {

    /*
    https://restful-booker.herokuapp.com/booking url’ine
    asagidaki body'ye sahip bir POST request gonderdigimizde
    donen response’un id disinda asagidaki gibi oldugunu test edin.
                        Request body
                   {
                        "firstname" : "Ahmet",
                        "lastname" : “Bulut",
                        "totalprice" : 500,
                        "depositpaid" : false,
                        "bookingdates" : {
                                 "checkin" : "2021-06-01",
                                 "checkout" : "2021-06-10"
                                          },
                        "additionalneeds" : "wi-fi"
                    }
                        Response Body
                   {
                    "bookingid":24,
                    "booking":{
                        "firstname":"Ahmet",
                        "lastname":"Bulut",
                        "totalprice":500,
                        "depositpaid":false,
                        "bookingdates":{
                            "checkin":"2021-06-01",
                            "checkout":"2021-06-10"
                                        }
                        ,
                        "additionalneeds":"wi-fi"
                             }
                    }
         */

    @Test
    public void post01(){

        //1-url ve request body
        String url= "https://restful-booker.herokuapp.com/booking";

        JSONObject bookJsnObj= new JSONObject();
        bookJsnObj.put("checkin","2021-06-01");
        bookJsnObj.put("checkout","2021-06-10");

        JSONObject reqJsnObj=new JSONObject();
        reqJsnObj.put("firstname" , "Ahmet");
        reqJsnObj.put("lastname", "Bulut");
        reqJsnObj.put("totalprice" , 500);
        reqJsnObj.put("depositpaid" , false);
        reqJsnObj.put("bookingdates" , bookJsnObj);
        reqJsnObj.put("additionalneeds" , "wi-fi");



        //2- expected body olusturulur

        JSONObject expectedBody= new JSONObject();
        expectedBody.put( "bookingid",24);
        expectedBody.put( "booking",reqJsnObj);
        System.out.println(expectedBody);

        //3-

        Response response=given().contentType(ContentType.JSON).when().body(reqJsnObj.toString()).post(url);

        JsonPath resJspath=response.jsonPath();
        response.prettyPrint();

        //4- Assertion

        assertEquals(expectedBody.getJSONObject("booking").get("firstname"),resJspath.get("booking.firstname"));






    }


}