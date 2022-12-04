package apitest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class GetAPITests {

	@Test
	public void getallComments() {
		RestAssured.baseURI = "http://localhost:3000";

		Response res = RestAssured.given()
				.contentType(ContentType.JSON)
				.when()
				.get("/comments");

		System.out.println(res.asPrettyString());
	}

}
