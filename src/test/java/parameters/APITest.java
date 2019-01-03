package parameters;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.hamcrest.Matchers;
import org.json.JSONException;
import org.json.JSONObject;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class APITest {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in/";
    }


    @Test(description = "GET")
    public void getRequestTest() throws JSONException {
        Response response = get("api/users/2");
        JSONObject jsonResponse = new JSONObject(response.asString());
        String first_name = jsonResponse.getJSONObject("data").getString("first_name");
        Assert.assertEquals(first_name, "Janet");
    }

    @Test(description = "POST")
    public void postRequestTest() {
        JSONObject requestBody = new JSONObject();
        requestBody.put("email", "sydney@fife");
        requestBody.put("password", "pistol");

        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        request.body(requestBody.toString());
        Response response = request.post("api/register");

        int statusCode = response.getStatusCode();
        Assert.assertEquals(statusCode, 201);

        JSONObject jsonResponse = new JSONObject(response.asString());
        String token = jsonResponse.getString("token");
        Assert.assertEquals(token, "QpwL5tke4Pnpja7X");
        System.out.println(response.getBody().asString());
    }

    @Test
    public void deleteRequestTest() {
        Response response = delete("api/users/2");
        System.out.println(response.asString());
        System.out.println(response.getStatusCode());
    }

    @Test
    public void testDelete() {
        given()
                .when()
                .delete("api/users/2")
                .then().statusCode(204)
                .header("server", Matchers.equalTo("cloudflare"));
    }
}
