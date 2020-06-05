package com.restassured;
import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class JuiceShopTest {
    @BeforeTest
    public void initialization(){
        RestAssured.baseURI=System.getenv("JUICE_SHOP_URL");
//        RestAssured.baseURI="http://juice-shop:3000";
    }
    @Test
    public void newUser(){
        Response resp= RestAssured
                .given()
                .header("Content-Type","application/json; charset=utf-8")
                .when()
                .body("{\n" +
                        "   \"email\":\"t12@gmail.com\",\n" +
                        "   \"password\":\"qwerty123\",\n" +
                        "   \"passwordRepeat\":\"qwerty123\",\n" +
                        "   \"securityQuestion\":{\n" +
                        "      \"id\":1,\n" +
                        "      \"question\":\"Your eldest siblings middle name?\",\n" +
                        "      \"createdAt\":\"2020-06-03T17:51:01.325Z\",\n" +
                        "      \"updatedAt\":\"2020-06-03T17:51:01.325Z\"\n" +
                        "   },\n" +
                        "   \"securityAnswer\":\"test\"\n" +
                        "}").log().all()
                .post("/api/Users")
                .then().assertThat().statusCode(201).log().all()
                .extract().response();

        String stringResp=resp.asString();
//        System.out.println(stringResp);
        JsonPath jsonResp=new JsonPath(stringResp);
        System.out.println("Role in the response: " + jsonResp.get("data.role"));
    }
    @Test(priority = 2)
    public void productsHomePage(){
        Response res=RestAssured
                .given()
                .header("Content-Type","application/json; charset=utf-8")
                .get("/#/search?q=apple")
                .then().assertThat().statusCode(200).log().all()
                .extract().response();
        String productData=res.asString();
        System.out.println(productData);

    }
}
