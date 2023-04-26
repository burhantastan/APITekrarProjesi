package testData;

import com.google.gson.JsonObject;
import org.json.JSONObject;

public class JsonPlaceHolderData {

    public static int statusCode= 200;
    public static String contentType="application/json; charset=utf-8";
    public static String connectionHeader="keep-alive";

   public static JSONObject requestBodyOlustur(String title,String body, int userId,int id){
       JSONObject requestBody = new JSONObject();

       requestBody.put("title",title);
       requestBody.put("body",body);
       requestBody.put("userId",userId);
       requestBody.put("id",id);

       return requestBody;
   }

   public static JSONObject expectedBody(String isim,String deger, int numara,int sirano){
       JSONObject expectedBody = new JSONObject();

       expectedBody.put("title",isim);
       expectedBody.put("body",deger);
       expectedBody.put("userId",numara);
       expectedBody.put("id",sirano);

       return expectedBody;
   }


}
