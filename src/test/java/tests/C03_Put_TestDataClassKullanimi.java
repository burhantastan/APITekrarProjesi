package tests;

import baseURL.JsonPlaceHolderBaseUrl;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.json.JSONObject;
import org.junit.Assert;
import org.junit.Test;
import testData.JsonPlaceHolderData;

import static io.restassured.RestAssured.given;

public class C03_Put_TestDataClassKullanimi extends JsonPlaceHolderBaseUrl {

    /*
        https://jsonplaceholder.typicode.com/posts/70 url'ine
        asagidaki body’e sahip bir PUT request yolladigimizda
        donen response’in
            status kodunun 200,
            content type’inin “application/json; charset=utf-8”,
            Connection header degerinin “keep-alive”
            ve response body’sinin asagida verilen ile ayni oldugunu test ediniz
         Request Body
            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }
        Response body (Expected Data) :
            {
            "title":"Ahmet",
            "body":"Merhaba",
            "userId":10,
            "id":70
            }

     */
    @Test

    public void put01(){

        // 1- endpoint hazirlanir ve varsa request body olusturulur

        specJsonPlaceHolder.pathParams("pp1","posts","pp2","70");

        JSONObject requestBody= JsonPlaceHolderData.requestBodyOlustur("Ahmet","Merhaba",10,70);

        // 2- Varsa expected data olustur

        JSONObject expectedBody = JsonPlaceHolderData.expectedBody("Ahmet","Merhaba",10,70);

        // 3- Request gonder donen response i kaydet

        Response response=given().spec(specJsonPlaceHolder)
                            .when().body(requestBody.toString()).contentType(ContentType.JSON)
                            .put("/{pp1}/{pp2}");

        // 4- Assertion

        JsonPath resJsonPath=response.jsonPath();

        Assert.assertEquals(JsonPlaceHolderData.statusCode,response.getStatusCode());
        Assert.assertEquals(JsonPlaceHolderData.contentType,response.getContentType());
        Assert.assertEquals(JsonPlaceHolderData.connectionHeader,response.header("Connection"));

        Assert.assertEquals(expectedBody.getString("title"),resJsonPath.getString("title"));
        Assert.assertEquals(expectedBody.getString("body"),resJsonPath.getString("body"));
        Assert.assertEquals(expectedBody.getInt("userId"),resJsonPath.getInt("userId"));
        Assert.assertEquals(expectedBody.getInt("id"),resJsonPath.getInt("id"));
    }

}
