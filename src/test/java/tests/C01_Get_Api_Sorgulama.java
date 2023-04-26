package tests;

import io.restassured.response.Response;

import org.hamcrest.Matchers;

import org.junit.Test;

import static io.restassured.RestAssured.given;

public class C01_Get_Api_Sorgulama {

    /*
    https://restful-booker.herokuapp.com/booking/57 url’ine bir GET request
    gonderdigimizde donen Response’un,
    status code’unun 200,
    ve content type’inin application/json; charset=utf-8,
    ve Server isimli Header’in degerinin Cowboy,
    ve status Line’in HTTP/1.1 200 OK
    ve response suresinin 5 sn’den kisa oldugunu  test ediniz.
    "firstname": "Ahmet"  oldugunu test edin
     */

    @Test
    public void get01(){

        //1- String url ve ihtiyac varsa request body olusturulur

        String  url="https://restful-booker.herokuapp.com/booking/57";

        //-2 expected data varsa expected body olustulur

        //3- bize donen responsu actual data olarak kaydet

        Response response=given().when().get(url);

        //4- assertions yap
        response.then().assertThat().statusCode(200).contentType("application/json; charset=utf-8")
                .header("Server", "Cowboy").statusLine("HTTP/1.1 200 OK")
                .body("firstname",Matchers.equalTo("John"));



    }



}